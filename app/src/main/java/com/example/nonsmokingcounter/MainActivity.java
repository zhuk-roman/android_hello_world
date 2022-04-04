package com.example.nonsmokingcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText userNameText;
    Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String SPdateKey = getResources().getString(R.string.SPdateKey);
        String SPuserNameKey = getResources().getString(R.string.SPuserNameKey);

        userNameText = findViewById(R.id.userNameText);
        startButton = findViewById(R.id.startButton);

        Intent intent = new Intent(MainActivity.this, TimerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);


        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        String timerIsInitialized = sharedPref.getString(SPuserNameKey, "");
        if (!timerIsInitialized.isEmpty()){
            Long startDate = sharedPref.getLong(SPdateKey, -1);
            intent.putExtra("startDate", startDate);
            startActivity(intent);
            finish();
        }

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userNameText.getText().toString().isEmpty()){
                    userNameText.setError("Name required");
                }
                else {
                    Long startDate = new Date(System.currentTimeMillis()).getTime();
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(SPuserNameKey, userNameText.getText().toString());
                    editor.putLong(SPdateKey, startDate);
                    editor.apply();

                    intent.putExtra("startDate", startDate);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}