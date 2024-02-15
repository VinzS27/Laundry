package com.example.laundryvae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
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

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HomeWashDetail extends AppCompatActivity {

    String[] numbers={"Machine n°1","Machine n°2","Machine n°3","Machine n°4","Machine n°5"};
    String[] colors={"mixed", "black", "white", "color"};
    String[] cycles={"flushing - 3m", "cotton - 40m", "wool - 50m", "sport - 1h"};
    AutoCompleteTextView autoCompleteText,autoCompleteText3, autoCompleteText4;
    ArrayAdapter<String> adapterItems;
    ArrayList<String> list = new ArrayList<>();
    TextView textView, textView2;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_wash_setting);

        createNotificationChannel();


        Button button = findViewById(R.id.washinfo);
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

                textView2 = CheckStatus();


                if(textView2.getCurrentTextColor() == Color.WHITE)
                    Toast.makeText(getApplicationContext(),"Washing machine busy..",Toast.LENGTH_SHORT).show();
                else
                    textView2.setTextColor(Color.WHITE);


                textView = findViewById(R.id.timer1);
                long duration=0;

                if(list.contains("flushing - 3m"))
                    duration = TimeUnit.MINUTES.toMillis(3);
                else if(list.contains("cotton - 40m"))
                    duration = TimeUnit.MINUTES.toMillis(40);
                else if(list.contains("wool - 50m"))
                    duration = TimeUnit.MINUTES.toMillis(50);
                else if(list.contains("sport - 1h"))
                    duration = TimeUnit.MINUTES.toMillis(60);

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
                        textView2.setTextColor(Color.rgb(3,218, 197));

                    }
                }.start();

            }
        });

        autoCompleteText=findViewById(R.id.toolbarNumber);
        adapterItems=new ArrayAdapter<String>(this,R.layout.string_list,numbers);
        autoCompleteText.setAdapter(adapterItems);
        autoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Added: "+item,Toast.LENGTH_SHORT).show();

                list.add(item);
            }
        });


        autoCompleteText3=findViewById(R.id.toolbarColor);
        adapterItems=new ArrayAdapter<String>(this,R.layout.string_list,colors);
        autoCompleteText3.setAdapter(adapterItems);
        autoCompleteText3.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Added: "+item,Toast.LENGTH_SHORT).show();

                list.add(item);
            }
        });

        autoCompleteText4=findViewById(R.id.toolbarCycle);
        adapterItems=new ArrayAdapter<String>(this,R.layout.string_list,cycles);
        autoCompleteText4.setAdapter(adapterItems);
        autoCompleteText4.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Added: "+item,Toast.LENGTH_SHORT).show();

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

    private TextView CheckStatus ()
    {
        if(list.contains("Machine n°1"))
            textView2 = findViewById(R.id.n1);
        else if(list.contains("Machine n°2"))
            textView2 = findViewById(R.id.n2);
        if(list.contains("Machine n°3"))
            textView2 = findViewById(R.id.n3);
        if(list.contains("Machine n°4"))
            textView2 = findViewById(R.id.n4);
        if(list.contains("Machine n°5"))
            textView2 = findViewById(R.id.n5);

        return textView2;
    }
}