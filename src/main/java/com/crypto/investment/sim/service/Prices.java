package com.crypto.investment.sim.service;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class Prices {

    @Scheduled(fixedRate = 300000)
    public void getPrices(){
        JSONObject prices = getPrice("d2f70ed0-47cf-11ec-980b-4f59414803c4", "GBP");
        Double USD = prices.getJSONObject("data").getDouble("USD");
        Double EUR = prices.getJSONObject("data").getDouble("EUR");
        Double ETH = prices.getJSONObject("data").getDouble("ETH");
        Double BTC = prices.getJSONObject("data").getDouble("BTC");
        System.out.println("prices: " + prices.toString());
    }

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
