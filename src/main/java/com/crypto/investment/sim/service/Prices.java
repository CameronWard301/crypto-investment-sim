package com.crypto.investment.sim.service;

import com.crypto.investment.sim.repos.CoinRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static com.crypto.investment.sim.CryptoInvestmentSimApplication.*;

@Service
public class Prices {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public CoinRepository coinRepo;

    /**
     * Get the Latest prices and store in database for USD, EUR, ETH and BTC Coins
     * Runs every 5 minutes
     */
    @Scheduled(fixedRate = 300000)
    public void get_USD_EUR_ETH_BTC_Prices(){
        JSONObject prices = sendGetRequest("https://freecurrencyapi.net/api/v2/latest?apikey=d2f70ed0-47cf-11ec-980b-4f59414803c4&base_currency=GBP", Optional.empty());
        if (prices != null){
            //Get each coin from JSON response data
            double latestUSD = prices.getJSONObject("data").getDouble("USD");
            double latestEUR = prices.getJSONObject("data").getDouble("EUR");
            double latestETH = prices.getJSONObject("data").getDouble("ETH");
            double latestBTC = prices.getJSONObject("data").getDouble("BTC");

            //Update Objects
            USD.setCurrentPrice(latestUSD);
            EUR.setCurrentPrice(latestEUR);
            ETH.setCurrentPrice(latestETH);
            BTC.setCurrentPrice(latestBTC);

            //Save to database and update objects
            USD = coinRepo.save(USD);
            EUR = coinRepo.save(EUR);
            ETH = coinRepo.save(ETH);
            BTC = coinRepo.save(BTC);

            System.out.println("prices: " + prices);
        }

    }

    /**
     * Get the latest price for Cardano (ADA) and store in DB
     * Runs every 4.5 minutes
     */
    @Scheduled (fixedRate = 270000)
    public void getCardanoPrice(){
        List<List<String>> headers = List.of(List.of("X-CMC_PRO_API_KEY", "fa5c7c88-f345-46a6-8254-1e278c5ac404"));
        JSONObject cardano = sendGetRequest("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=ADA&convert=GBP", Optional.of(headers));

        //Get price from JSON response
        double latestADA = cardano.getJSONObject("data").getJSONObject("ADA").getJSONObject("quote").getJSONObject("GBP").getDouble("price");

        //Round to 6 DP
        BigDecimal bd = new BigDecimal(latestADA);
        double roundedValue = bd.setScale( 6, RoundingMode.HALF_UP ).doubleValue();
        ADA.setCurrentPrice(roundedValue);

        //Store in DB and get the latest ADA object
        ADA = coinRepo.save(ADA);

        System.out.println("CARDANO: "+ cardano);
    }

    /**
     * Send a GET request to a specified URL
     * @param url The url to send as a GET Request
     * @param headers Headers to pass to the GET request
     * @return JSONObject - response from the GET Request
     */
    public JSONObject sendGetRequest(String url, Optional<List<List<String>>> headers){
        // latest rates
        try {
            URL urlForGetRequest = new URL(url);
            String readLine;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            if (headers.isPresent()){
                for (List<String> header: headers.get()){
                    connection.setRequestProperty(header.get(0), header.get(1));
                }
            }
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                System.out.println("string: " + response);
                return new JSONObject(response.toString());
            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
