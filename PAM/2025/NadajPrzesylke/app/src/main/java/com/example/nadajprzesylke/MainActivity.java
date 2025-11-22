package com.example.nadajprzesylke;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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

        Button checkPriceButton = findViewById(R.id.checkPriceButton);

        RadioButton postcard = findViewById(R.id.postcardCheck);
        RadioButton letter = findViewById(R.id.letterCheck);
        RadioButton parcel = findViewById(R.id.parcelCheck);

        ImageView thumbnailImage = findViewById(R.id.thumbnailImage);
        TextView priceView = findViewById(R.id.priceView);

        checkPriceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price;
                if (postcard.isChecked()) {
                    thumbnailImage.setImageDrawable(getDrawable(R.drawable.pocztowka));
                    price = "1";
                }
                else if (letter.isChecked()) {
                    thumbnailImage.setImageDrawable(getDrawable(R.drawable.list));
                    price = "1.5";
                }
                else {
                    thumbnailImage.setImageDrawable(getDrawable(R.drawable.paczka));
                    price = "10";
                }
                priceView.setText(String.format("Cena: %s zł", price));
            }
        });

        Button confirmButtom = findViewById(R.id.confirmButton);

        EditText streetInput = findViewById(R.id.streetInput);
        EditText postalCodeInput = findViewById(R.id.postalCodeInput);
        EditText cityInput = findViewById(R.id.cityInput);

        confirmButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this).setPositiveButton("OK", null);

                String postalCode = postalCodeInput.getText().toString();

                if (postalCode.length() != 5) {
                    dialogBuilder.setMessage(getString(R.string.confirm_incorrect_length)).show();
                    return;
                }

                try {
                    int a = Integer.parseInt(postalCode);
                } catch (Exception e) {
                    dialogBuilder.setMessage(getString(R.string.confirm_not_all_digits)).show();
                    return;
                }

                dialogBuilder.setMessage(getString(R.string.confirm_success)).show();
            }
        });
    }
}