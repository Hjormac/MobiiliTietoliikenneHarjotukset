package com.example.stockbroker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StockReader.resultArray{

    private ArrayList<Stock> stockList;
    private ListView listView;
    private StockAdapter sAdapter;
    private boolean intitalDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        stockList = new ArrayList<>();
        listView = findViewById(R.id.lista);
        sAdapter = new StockAdapter(this,stockList);
        listView.setAdapter(sAdapter);

        Button button =  findViewById(R.id.button1);

        StockReader initalR;
        initalR = new StockReader(MainActivity.this);
        initalR.execute("https://financialmodelingprep.com/api/company/price/AAPL,GOOGL,FB,NOK?datatype=json");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StockReader sReader;
                sReader = new StockReader(MainActivity.this);

                EditText teksti = findViewById(R.id.tunnuskentta);
             String haeOsake = "https://financialmodelingprep.com/api/company/price/";
             haeOsake = haeOsake+teksti.getText().toString()+"?datatype=json";
            sReader.execute(haeOsake);

            }
        });

    }

    @Override
    public void resultA(ArrayList<Stock> list, boolean success) {

        EditText nimiK = findViewById(R.id.nimikentta);
        if(intitalDone == false) {

                sAdapter.add(new Stock("Apple", list.get(0).getPrice()));
                sAdapter.add(new Stock("Google", list.get(1).getPrice()));
                sAdapter.add(new Stock("Facebook", list.get(2).getPrice()));
                sAdapter.add(new Stock("Nokia", list.get(3).getPrice()));

            intitalDone = true;
        }


        else if (intitalDone == true) {



            if (success == true) {

                for (Stock s : list) {
                sAdapter.add(new Stock(nimiK.getText().toString(), s.getPrice()));
                }

            }
        }
    }
}
