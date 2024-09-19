package com.technical.coding.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setAlarm();

            }
        });

    }

    private void setAlarm() {

        Intent intent = new Intent(this, MyBroadcastReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 123, intent, PendingIntent.FLAG_IMMUTABLE
        );


        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (30*1000), pendingIntent
        );


        //To Cancel the alarm
        //alarmManager.cancel(pendingIntent);

    }
}




