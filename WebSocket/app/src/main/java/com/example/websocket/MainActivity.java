package com.example.websocket;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import android.view.View;

import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;


import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class MainActivity extends Activity {

    private TextView t;
    private WebSocketClient mWebSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectWebSocket();
    }


    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://obscure-waters-98157.herokuapp.com");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                mWebSocketClient.send( "How are you gentlemen !!");
                mWebSocketClient.send( "All your base are belong to us.");
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = (TextView)findViewById(R.id.textview1);
                        textView.setText(textView.getText() + "\n" + message);
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }

    public void sendMessage(View view) {
        EditText editText = findViewById(R.id.writeField);
        mWebSocketClient.send(editText.getText().toString());
        editText.setText("");
    }
}

