package com.example.htmlreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements BackgroundReader.resultHtml{

    private BackgroundReader htmlreader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    public void resulthtml(String s) {
        TextView txt = findViewById(R.id.tekstikentta);
        txt.setText(s);
    }

    public void startSearch(View view) {
        htmlreader = new BackgroundReader(this);
        EditText teksti = findViewById(R.id.syotto);
        String s = teksti.getText().toString();
        htmlreader.execute(s);
    }
}
