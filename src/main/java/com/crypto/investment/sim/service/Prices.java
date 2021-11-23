package com.crypto.investment.sim.service;

import com.crypto.investment.sim.repos.CoinRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        JSONObject prices = getPrice("d2f70ed0-47cf-11ec-980b-4f59414803c4", "GBP");
        if (prices != null){
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
     * API Call to FreeCurrencyAPI returns a JSON Object
     * @param key API Key
     * @param base_currency Currency to compare to
     * @return JSONObject
     */
    public JSONObject getPrice(String key, String base_currency){
        // latest rates
        try {
            String url = "https://freecurrencyapi.net/api/v2/latest?apikey="+key+"&base_currency="+base_currency;
            URL urlForGetRequest = new URL(url);
            String readLine;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
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
