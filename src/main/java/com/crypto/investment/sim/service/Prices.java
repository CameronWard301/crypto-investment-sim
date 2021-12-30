package com.crypto.investment.sim.service;

import com.crypto.investment.sim.components.API_KEYS;
import com.crypto.investment.sim.components.CoinRequestSettings;
import com.crypto.investment.sim.exceptions.GET_Exception;
import com.crypto.investment.sim.repos.CoinRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final Logger logger = LoggerFactory.getLogger(Prices.class);

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    public CoinRepository coinRepo;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private CoinRequestSettings coinProperties;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private API_KEYS api_keys;

    /**
     * Get the Latest prices and store in database for USD, EUR, ETH and BTC Coins
     * Runs every 5 minutes
     */
    @Scheduled(fixedRateString = "${coin.frequency.USD_EUR_ETH:600000}")
    public void get_USD_EUR_ETH_Prices(){
        if (coinProperties.isRequests()) {
            logger.info("Did not fetch USD_EUR_ETH_Prices | Reason: Disabled in config");
            return;
        }

        String url = "https://freecurrencyapi.net/api/v2/latest?apikey="+api_keys.getFreecurrencyAPI()+"&base_currency=GBP";
        JSONObject prices = sendGetRequest(url, Optional.empty());

        if (prices != null){
            //Get each coin from JSON response data
            float latestUSD = (float) prices.getJSONObject("data").getDouble("USD");
            float latestEUR = (float) prices.getJSONObject("data").getDouble("EUR");
            float latestETH = (float) prices.getJSONObject("data").getDouble("ETH");

            //Update Objects
            USD.setCurrentPrice(latestUSD);
            EUR.setCurrentPrice(latestEUR);
            ETH.setCurrentPrice(latestETH);

            //Save to database and update objects
            USD = coinRepo.save(USD);
            EUR = coinRepo.save(EUR);
            ETH = coinRepo.save(ETH);

            logger.info("Fetched latest prices for: ETH, USD, EUR");

        } else{
            logger.error("freeCurrency API failed! Returned NULL, could not update the database with the latest values. Previous values kept");
        }

    }

    /**
     * Get the latest price for Cardano (ADA) and store in DB
     * Runs every 4.5 minutes
     */
    @Scheduled (fixedRateString = "${coin.frequency.ADA_BTC:600000}")
    public void get_ADA_BTC_Prices(){
        if (coinProperties.isRequests()) {
            logger.info("Did not fetch ADA_BTC_Prices | Reason: Disabled in config");
            return;
        }
        List<List<String>> headers = List.of(List.of("X-CMC_PRO_API_KEY", api_keys.getCoinmarketcapAPI()));
        JSONObject cardano = sendGetRequest("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=ADA&convert=GBP", Optional.of(headers));
        JSONObject bitcoin = sendGetRequest("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=BTC&convert=GBP", Optional.of(headers));

        //Don't update price if there is an API error:
        if (cardano == null){
            logger.error("Cardano API failed! Returned NULL, could not update the database with the latest value. Previous value kept");
        }

        if (bitcoin == null){
            logger.error("Bitcoin API failed! Returned NULL, could not update the database with the latest value. Previous value kept");
        }

        if (bitcoin == null || cardano == null){
            return;
        }

        //Get price from JSON response
        double latestADA = cardano.getJSONObject("data").getJSONObject("ADA").getJSONObject("quote").getJSONObject("GBP").getDouble("price");
        double latestBTC = bitcoin.getJSONObject("data").getJSONObject("BTC").getJSONObject("quote").getJSONObject("GBP").getDouble("price");

        //Round to 6 DP
        BigDecimal bdADA = new BigDecimal(1/latestADA);
        double roundedADA = bdADA.setScale(6, RoundingMode.HALF_UP).doubleValue();

        BigDecimal bdBTC = new BigDecimal(1/latestBTC);
        double roundedBTC = bdBTC.setScale(10, RoundingMode.HALF_UP).doubleValue();

        //Store in DB and get the latest coin object
        ADA.setCurrentPrice((float) roundedADA);
        BTC.setCurrentPrice((float) roundedBTC);
        ADA = coinRepo.save(ADA);
        BTC = coinRepo.save(BTC);

        logger.info("Fetched latest prices for: ADA, BTC");
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
                return new JSONObject(response.toString());
            } else {
                throw new GET_Exception(connection.getResponseMessage(), connection.getURL(), connection.getResponseCode());
            }
        } catch (GET_Exception ex) {
            logger.error("Invalid GET Request, response: "+ ex.getMessage() + ". With error code: " + ex.getError_code() + ". For url: " + ex.getUrl());
            return null;
        } catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
}
