package com.example.a08;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String pet = "";
    private static final String CHANNEL_ID = "test";

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

        ListView listView = findViewById(R.id.listView);

        HashMap<String, Integer> elements = new HashMap<String, Integer>();

        elements.put("Pies", 18);
        elements.put("Kot", 20);
        elements.put("Świnka morska", 9);

        String[] pets = elements.keySet().toArray(new String[0]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pets);

        listView.setAdapter(adapter);

        SeekBar slider = findViewById(R.id.seekBar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                String item = (String) adapter.getItemAtPosition(position);
                pet = item;
                slider.setMax(elements.get(item));
            }
        });

        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean a) {
                TextView age = findViewById(R.id.age);
                age.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar a) {}
            @Override
            public void onStopTrackingTouch(SeekBar a) {}
        });

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameSurname = findViewById(R.id.nameSurname);
                EditText cause = findViewById(R.id.cause);
                EditText time = findViewById(R.id.time);

                TextView message = findViewById(R.id.message);
                String combinedMessage = nameSurname.getText().toString() + ", " + pet + ", " + slider.getProgress() + ", " + cause.getText().toString() + ", " + time.getText().toString();
                message.setText(combinedMessage);

                CharSequence name = "Test";
                String description = "Testing";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);

                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                if(notificationManager != null) notificationManager.createNotificationChannel(channel);

                Notification notification = new Notification.Builder(MainActivity.this, CHANNEL_ID)
                        .setContentTitle("Uwaga uwaga")
                        .setContentText(combinedMessage)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .build();

                notificationManager.notify(1, notification);
            }
        });
    }
}