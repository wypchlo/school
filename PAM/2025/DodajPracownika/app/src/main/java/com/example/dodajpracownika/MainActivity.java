package com.example.dodajpracownika;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private final String[] Positions = { "Kierownik", "Starszy programista", "Młodszy programista", "Tester", "" };
    private List<Character> Password = new ArrayList<>();

    private Spinner PositionComboBox;
    private EditText CharacterCountInput, NameInput, SurnameInput;
    private CheckBox VaryingSizesCheck, DigitsCheck, SpecialCharactersCheck;

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

        PositionComboBox = findViewById(R.id.positionComboBox);
        setupPositionComboBox();

        CharacterCountInput = findViewById(R.id.characterCountInput);

        VaryingSizesCheck = findViewById(R.id.varyingSizesCheck);
        DigitsCheck = findViewById(R.id.digitsCheck);
        SpecialCharactersCheck = findViewById(R.id.specialCharactersCheck);

        NameInput = findViewById(R.id.nameInput);
        SurnameInput = findViewById(R.id.surnameInput);

        Button confirmButton = findViewById(R.id.confirmButton);
        Button generatePasswordButton = findViewById(R.id.generatePasswordButton);

        generatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePassword();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this).setPositiveButton("Ok", null);

                if (SurnameInput.getText().length() == 0 || NameInput.getText().length() == 0 || PositionComboBox.getSelectedItem() == "") {
                    dialogBuilder.setMessage("Proszę wypełnić wszystkie pola").show();
                    return;
                }

                if (Password.isEmpty()) {
                    dialogBuilder.setMessage("Proszę wygenerować hasło").show();
                    return;
                }

                dialogBuilder.setMessage(String.format("Dane pracownika: %s %s %s Hasło: %s",
                    NameInput.getText().toString(),
                    SurnameInput.getText().toString(),
                    PositionComboBox.getSelectedItem(),
                    getPasswordString()
                )).show();
            }
        });
    }

   private String getPasswordString() {
       StringBuilder builder = new StringBuilder();
       for (char character : Password) builder.append(character);
       return builder.toString();
   }

    private void generatePassword() {
        CheckBox[] boxes = {
            VaryingSizesCheck,
            DigitsCheck,
            SpecialCharactersCheck
        };

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this).setPositiveButton("Ok", null);
        Random random = new Random();
        String specialChars = "!@#$%^&*()_+-=";
        int characterCount;

        try {
            characterCount = Integer.parseInt(CharacterCountInput.getText().toString());
        } catch (Exception e) {
            dialogBuilder.setMessage("Błędne dane").show();
            return;
        }

        CheckBox[] checkedBoxes = Arrays.stream(boxes).filter(box -> box.isChecked()).toArray(CheckBox[]::new);

        if (checkedBoxes.length > characterCount) {
            dialogBuilder.setMessage("Za mało znaków").show();
            return;
        }

        for (int i = 0; i < characterCount; i++) Password.add(' ');

        List<Integer> availableIndices = new ArrayList<Integer>();
        for (int i = 0; i < characterCount; i++) availableIndices.add(i);

        Collections.shuffle(availableIndices);

        for (CheckBox box : checkedBoxes) {
            int index = availableIndices.get(0);
            availableIndices.remove(0);

            if (box == VaryingSizesCheck) Password.set(index, (char)('A' + random.nextInt(26)));
            else if (box == DigitsCheck) Password.set(index, (char)('0' + random.nextInt(10)));
            else if (box == SpecialCharactersCheck) Password.set(index, specialChars.charAt(random.nextInt(specialChars.length())));
        }

        for (int index : availableIndices) {
            Password.set(index, (char)('a' + random.nextInt(26)));
        }

        dialogBuilder.setMessage(getPasswordString()).show();
    }
    private void setupPositionComboBox() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Positions){
            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };

        PositionComboBox.setAdapter(adapter);
        PositionComboBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getColor(R.color.black));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        PositionComboBox.setSelection((int) Arrays.stream(Positions).count() - 1);
    }

}