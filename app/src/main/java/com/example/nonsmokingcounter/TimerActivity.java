package com.example.nonsmokingcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import java.util.Date;

public class TimerActivity extends AppCompatActivity {
    static void timerTick (Long nowT, Long startDate, TextView timerDays, TextView timerHours,
                           TextView timerMinutes, TextView timerSeconds){
        long diff = nowT - startDate;

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        timerDays.setText(Long.toString(diffDays));
        timerHours.setText(Long.toString(diffHours));
        timerMinutes.setText(Long.toString(diffMinutes));
        timerSeconds.setText(Long.toString(diffSeconds));

    }

    Date now = new Date(System.currentTimeMillis());

    TextView timerDays;
    TextView timerHours;
    TextView timerMinutes;
    TextView timerSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerDays = findViewById(R.id.timerDays);
        timerHours = findViewById(R.id.timerHours);
        timerMinutes = findViewById(R.id.timerMinutes);
        timerSeconds = findViewById(R.id.timerSeconds);

        Bundle extras = getIntent().getExtras();
        Long startDate = extras.getLong("startDate");
        Log.i("TimerActivity", String.valueOf(startDate));

        final Handler ha=new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                //call function
                timerTick(new Date(System.currentTimeMillis()).getTime(), startDate,
                        timerDays,timerHours,timerMinutes,timerSeconds);
                ha.postDelayed(this, 1000);
            }
        }, 1000);


        timerTick(new Date(System.currentTimeMillis()).getTime(), startDate,
                timerDays,timerHours,timerMinutes,timerSeconds);

    }

}
