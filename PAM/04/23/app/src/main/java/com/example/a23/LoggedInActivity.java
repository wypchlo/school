package com.example.a23;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoggedInActivity extends AppCompatActivity {

    final String CHANNEL_ID = "2137";
    final String CHANNEL_NAME = "Zadanie podsumowywujące";
    String activeFragment;
    FragmentManager fragmentManager;

    public void openDialog() {
        AppDialogFragment dialogFragment = new AppDialogFragment();
        dialogFragment.setCancelable(true);
        dialogFragment.show(fragmentManager, "dialog");
    }

    private void changeFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(activeFragment.equals("first")) {
            activeFragment = "second";
            transaction.replace(R.id.fragmentHolder, new SecondFragment()).commit();
            return;
        }
        activeFragment = "first";
        transaction.replace(R.id.fragmentHolder, new FirstFragment()).commit();
    }

    private void sendNotification() {
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationChannel channel = new NotificationChannel(this.CHANNEL_ID, this.CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        Notification notification = new NotificationCompat.Builder(this, this.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_content))
                .setContentIntent(pIntent)
                .build();

        notificationManager.notify(1, notification);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logged_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fragmentManager = getSupportFragmentManager();
        activeFragment = "first";

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentHolder, new FirstFragment()).commit();

        Button changeFragmentButton = findViewById(R.id.changeFragmentButton);
        changeFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment();
            }
        });

        Button showNotificationButton = findViewById(R.id.showNotificationButton);

        showNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
    }
}