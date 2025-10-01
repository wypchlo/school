package com.example.internetrzeczy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

        Button buttonPralka = findViewById(R.id.button_pralka);
        EditText inputPralka = findViewById(R.id.input_pralka);
        TextView infoPralka = findViewById(R.id.info_pralka);

        buttonPralka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int number = Integer.parseInt(inputPralka.getText().toString());

                    if (number >= 1 && number <= 12) {
                        infoPralka.setText(getString(R.string.info_pralk) + Integer.toString(number));
                    }
                } catch (NumberFormatException e) {
                    return;
                }
            }
        });

        Button buttonOdkurzacz = findViewById(R.id.button_odkurzacz);
        TextView infoOdkurzacz = findViewById(R.id.info_odkurzacz);

        buttonOdkurzacz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonOdkurzacz.getText().toString().equals(getString(R.string.button_odkurzacz))) {
                    buttonOdkurzacz.setText(R.string.button_odkurzacz_fire);
                    infoOdkurzacz.setText(R.string.info_odkurzacz_burburala);
                    return;
                }
                buttonOdkurzacz.setText(R.string.button_odkurzacz);
                infoOdkurzacz.setText(R.string.info_odkurzacz);
            }
        });
    }
}