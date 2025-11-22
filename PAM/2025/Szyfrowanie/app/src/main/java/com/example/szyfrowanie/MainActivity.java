package com.example.szyfrowanie;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    public int mod(int a, int n)
    {
        return ((a % n) + n) % n;
    }

    public int Key = 0;

    public EditText KeyValueInput;
    public TextView EncodedTextView;
    public EditText InputText;
    public Button EncodeButton;


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

        KeyValueInput = findViewById(R.id.keyValueInput);
        EncodedTextView = findViewById(R.id.encodedTextView);
        InputText = findViewById(R.id.inputText);
        EncodeButton = findViewById(R.id.encodeButton);

        InputText.setSingleLine(false);

        EncodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Character> encodedText = new ArrayList<Character>();

                try {
                    Key = Integer.parseInt(KeyValueInput.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Key = 0;
                }

                for (int i = 0; i < InputText.getText().length(); i++)
                {
                    char letter = InputText.getText().toString().charAt(i);
                    int letterIndex = letter - (int) 'a';
                    if (letterIndex > 25 || letterIndex < 0) {
                        encodedText.add(letter);
                        continue;
                    }
                    encodedText.add((char) (mod(letterIndex + Key, 26) + (int) 'a'));
                }

                EncodedTextView.setText(encodedText.stream().map(Object::toString).collect(Collectors.joining("")));
            }
        });
    }
}