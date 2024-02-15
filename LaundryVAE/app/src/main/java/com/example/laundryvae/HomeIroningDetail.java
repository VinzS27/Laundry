package com.example.laundryvae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HomeIroningDetail extends AppCompatActivity {

    String[] timer = {"10 minutes", "20 minutes", "30 minutes"};
    AutoCompleteTextView autoCompleteText;
    ArrayAdapter<String> adapterItems;
    ArrayList<String> list = new ArrayList<>();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_ironing_setting);

        Button button = findViewById(R.id.ironinfo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfoActivity();
            }
        });

        Button button1 = findViewById(R.id.startbtn);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"LaundryVAE")
                .setSmallIcon(R.drawable.ic_baseline_local_laundry_service_24)
                .setContentTitle("LaundryVAE services")
                .setContentText("Operation successful")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView = findViewById(R.id.timer1);
                long duration=0;

                if(list.contains("10 minutes"))
                    duration = TimeUnit.MINUTES.toMillis(10);
                else if(list.contains("20 minutes"))
                    duration = TimeUnit.MINUTES.toMillis(20);
                else if(list.contains("30 minutes"))
                    duration = TimeUnit.MINUTES.toMillis(30);

                new CountDownTimer(duration, 1000) {
                    @Override
                    public void onTick(long l) {
                        String d=String.format(Locale.ENGLISH,"%02d : %02d"
                                ,TimeUnit.MILLISECONDS.toMinutes(l)
                                ,TimeUnit.MILLISECONDS.toSeconds(l) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                        textView.setText(d);
                    }

                    @Override
                    public void onFinish() {
                        finish();
                        notificationManagerCompat.notify(100,builder.build());
                    }
                }.start();

            }
        });

        autoCompleteText = findViewById(R.id.toolbarSize);
        adapterItems = new ArrayAdapter<String>(this, R.layout.string_list, timer);
        autoCompleteText.setAdapter(adapterItems);
        autoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Added: " + item, Toast.LENGTH_SHORT).show();
                button1.setVisibility(View.VISIBLE);
                list.add(item);
            }
        });
    }

    private void openInfoActivity() {
        Intent intent = new Intent(this,InfoActivity.class);
        startActivity(intent);
    }

    private void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name="LaundryVAE services";
            String description = "Operation successful";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("LaundryVAE",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}