package com.example.a23;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NotificationActivity extends AppCompatActivity {
    /*final String[] LIST_ITEMS = new String[] {
            getString(R.string.list_item_1),
            getString(R.string.list_item_2),
            getString(R.string.list_item_3),
            getString(R.string.list_item_4),
            getString(R.string.list_item_5)
    };*/

    final String[] LIST_ITEMS = new String[] {
        "Programowanie Obiektowe",
        "Programowanie Aplikacji Mobilnych",
        "Programowanie Aplikacji Internetowych",
        "Programowanie Aplikacji Webowych",
        "Programowanie Aplikacji Desktopowych"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, LIST_ITEMS);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);
    }
}