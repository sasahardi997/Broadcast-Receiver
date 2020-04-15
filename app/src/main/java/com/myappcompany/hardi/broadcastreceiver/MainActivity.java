package com.myappcompany.hardi.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView message;
    private Button sendBroadcastBtn, sendAnotherBroadcast;

    private ExampleBroadcastReceiver exampleBroadcastReceiver;
    public static final String BROADCAST_RECEIVED = "Broadcast Received";
    public static final String BROADCAST_RECEIVED_SECOND = "Broadcast Received Second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.message);
        sendBroadcastBtn = findViewById(R.id.send_broadcast);
        sendAnotherBroadcast = findViewById(R.id.send_another_broadcast);

        exampleBroadcastReceiver = new ExampleBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_RECEIVED);
        intentFilter.addAction(BROADCAST_RECEIVED_SECOND);
        registerReceiver(exampleBroadcastReceiver, intentFilter);

        sendBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BROADCAST_RECEIVED);
                intent.putExtra("first", BROADCAST_RECEIVED);
                sendBroadcast(intent);
            }
        });

        sendAnotherBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BROADCAST_RECEIVED_SECOND);
                intent.putExtra("second",BROADCAST_RECEIVED_SECOND);
                sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exampleBroadcastReceiver);
    }

    private void setFirsText(String firstText){
        message.setText(firstText);
    }

    private void setSecondText(String secondText){
        message.setText(secondText);
    }

    private class ExampleBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(BROADCAST_RECEIVED)){
                String receivedText = intent.getStringExtra("first");
                setFirsText(receivedText);
            } else if (intent.getAction().equals(BROADCAST_RECEIVED_SECOND)){
                String receivedText = intent.getStringExtra("second");
                setSecondText(receivedText);
            }
        }
    }
}
