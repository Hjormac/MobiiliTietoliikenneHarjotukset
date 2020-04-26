package com.example.football;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private ListView aView;
    private ArrayList<Area> aList;
    private ArrayAdapter<Area> aAdapter;
    private String url = "https://api.football-data.org/v2/areas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aList = new ArrayList<>();
        aAdapter = new AreaAdapter(MainActivity.this,aList);

        aView = findViewById(R.id.leagueView);
        aView.setAdapter(aAdapter);


        getAndParseJson();


        aView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent i = new Intent(MainActivity.this,AreaActivity.class);
                i.putExtra("Areacode",aAdapter.getItem(position).getId());
                startActivity(i);
            }
        });

    }



    public void getAndParseJson() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("areas");
                            for(int i = 0 ; i<jsonArray.length(); i++){
                                String name = jsonArray.getJSONObject(i).get("name").toString();
                                String id = jsonArray.getJSONObject(i).get("id").toString();
                                Area ar = new Area(id,name);
                                aAdapter.add(ar);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });
        Volley.newRequestQueue(MainActivity.this).add(jsonObjectRequest);
    }
}
