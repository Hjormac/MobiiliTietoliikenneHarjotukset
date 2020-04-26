package com.example.stockbroker;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class StockReader extends AsyncTask<String, String, String> {

    private resultArray rArray;
    private boolean success = false;

    public StockReader(resultArray r) {
        rArray = r;
    }

    public interface resultArray {
        void resultA(ArrayList<Stock> list, boolean s);
    }


    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
                Log.d("Response: ", "> " + line);

            }

            return buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ArrayList<Stock> sList = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(result);
            Iterator i = obj.keys();

            while(i.hasNext()) {
                String key = (String) i.next();
                JSONObject stock = obj.getJSONObject(key);
                double price = stock.getDouble("price");

                sList.add(new Stock(key,price));


            }
            success = true;
            rArray.resultA(sList,success);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }


    }
}
