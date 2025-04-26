package com.example.a23;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private void initDb() {
        SQLiteDatabase db = openOrCreateDatabase("sprawdzian", Context.MODE_PRIVATE, null);

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS users" +
                "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "email TEXT," +
                    "password TEXT" +
                ")"
        );

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM users", null);
        cursor.moveToFirst();
        int rowCount = cursor.getInt(0);
        cursor.close();

        if(rowCount != 0) return;

        db.execSQL("INSERT INTO users (email, password) VALUES ('admin@example.com', 'admin')");
        db.execSQL("INSERT INTO users (email, password) VALUES ('user1@example.com', 'user1')");
        db.execSQL("INSERT INTO users (email, password) VALUES ('user2@exmaple.com', 'user2')");
        db.execSQL("INSERT INTO users (email, password) VALUES ('user2@exmaple.com', 'user3')");
    }

    private boolean checkCredentials(String email, String password) {
        SQLiteDatabase db = openOrCreateDatabase("sprawdzian", Context.MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery(
        "SELECT * FROM users WHERE email = ?",
            new String[] {email}
        );

        if(!cursor.moveToFirst()) return false;
        cursor.close();

        Cursor cursor2 = db.rawQuery(
            "SELECT * FROM users WHERE email = ? AND password = ?",
                new String[] {email, password}
        );

        boolean valid = cursor2.moveToFirst();
        cursor2.close();

        return valid;
    }

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

        initDb();

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailInput = findViewById(R.id.emailInput);
                EditText passwordInput = findViewById(R.id.passwordInput);

                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if(email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, getString(R.string.fill_out_every_field), Toast.LENGTH_LONG).show();
                    return;
                }

                if(!checkCredentials(email, password)) {
                    Toast.makeText(MainActivity.this, getString(R.string.invalid_login_data), Toast.LENGTH_LONG).show();
                    passwordInput.setText("");
                    return;
                }

                Toast.makeText(MainActivity.this, getString(R.string.logged_in), Toast.LENGTH_LONG).show();

                startActivity(new Intent(MainActivity.this, LoggedInActivity.class));
            }
        });
    }
}