package com.example.football;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AreaActivity extends AppCompatActivity implements View.OnClickListener {


    private ListView lView;
    private ArrayList<League> lList;
    private ArrayAdapter<League> lAdapter;
    private String urlBase = "https://api.football-data.org/v2/competitions?areas=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        Intent i = getIntent();
        String s = i.getStringExtra("Areacode");
        String u = urlBase + s;

        lList = new ArrayList<>();
        lAdapter = new LeagueAdapter(AreaActivity.this, lList);

        lView = findViewById(R.id.leagueView);
        lView.setAdapter(lAdapter);
        Log.d("getUrlString", u);
        getAndParseJson(u);


        findViewById(R.id.backButton).setOnClickListener(this);


    }


    public void getAndParseJson(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("competitions");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                String name = jsonArray.getJSONObject(i).get("name").toString();
                                String id = jsonArray.getJSONObject(i).get("id").toString();
                                League l = new League(name, id);
                                lAdapter.add(l);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(lList.isEmpty()) {
                            lAdapter.add(new League("No leagues for this Area","noid"));
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });
        Volley.newRequestQueue(AreaActivity.this).add(jsonObjectRequest);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.backButton) {


            finish();
        }

    }
}
