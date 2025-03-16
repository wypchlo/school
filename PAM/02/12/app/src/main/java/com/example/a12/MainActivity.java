package com.example.a12;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private int counter;
    private EditText name_input;
    private EditText email_input;
    private TextView txt1;
    private TextView txt2;

    protected void setTxt2()  {
        txt2.setText(getString(R.string.txt2).replace("0", String.valueOf(counter)));
    }

    protected void setTxt1() {
        if(!name_input.getText().toString().isEmpty() && !email_input.getText().toString().isEmpty())
            txt1.setText(getString(R.string.welcome, name_input.getText(), email_input.getText()));
        else txt1.setText("");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", name_input.getText().toString());
        outState.putString("email", email_input.getText().toString());
        outState.putInt("counter", counter);
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt("counter");
        name_input.setText(savedInstanceState.getString("name"));
        email_input.setText(savedInstanceState.getString("email"));
        setTxt1();
        setTxt2();
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

        name_input = findViewById(R.id.name_input);
        email_input = findViewById(R.id.email_input);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);

        name_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                setTxt1();
            }
        });

        email_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                setTxt1();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name_input.getText().toString().isEmpty() && !email_input.getText().toString().isEmpty()) {
                    counter++;
                    txt2.setText(getString(R.string.txt2).replace("0", String.valueOf(counter)));
                }
                else Toast.makeText(MainActivity.this, getString(R.string.missing_data), Toast.LENGTH_SHORT).show();
            }
        });
    }
}