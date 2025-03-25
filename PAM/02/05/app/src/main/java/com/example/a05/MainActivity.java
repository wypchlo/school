package com.example.a05;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
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

        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String CHANNEL_ID = "high_priority";

                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "activity1_notifications", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(notificationChannel);

                Intent activity2 = new Intent(MainActivity.this, MainActivity2.class);
                PendingIntent pending = PendingIntent.getActivity(MainActivity.this, 0, activity2, PendingIntent.FLAG_IMMUTABLE);

                Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setContentTitle("UWAGA UWAGA WAŻNE OGŁOSZENIE")
                        .setContentText("nastąpiła zmiana rozkładu mszy trydenckich")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentIntent(pending)
                        .build();

                notificationManager.notify(1, notification);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String CHANNEL_ID = "low_priority";

                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "avtivity1_notifications_low_priority", NotificationManager.IMPORTANCE_MIN);
                notificationManager.createNotificationChannel(notificationChannel);

                Intent activity3 = new Intent(MainActivity.this, MainActivity3.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, activity3, PendingIntent.FLAG_IMMUTABLE);

                Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setContentTitle("ej tak jakby co btw")
                        .setContentText("zapomniałen")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentIntent(pendingIntent)
                        .build();

                notificationManager.notify(2, notification);
            }
        });
    }
}