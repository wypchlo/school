package com.example.weterynarz;

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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekbar;
    private TextView age_number;

    private Integer species_id = 1;

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

        seekbar = findViewById(R.id.seekBar);
        age_number = findViewById(R.id.age_number);

        ListView list = findViewById(R.id.species_list);

        String[] species = { getString(R.string.dog), getString(R.string.cat), getString(R.string.seeschwein) };
        Integer[] age = { 18, 20, 9 };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, species);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seekbar.setMax(age[position]);
                species_id = position;
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                age_number.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        Button ok_button = findViewById(R.id.ok_poznan);

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name_surname = findViewById(R.id.name_surname);
                EditText appointment_goal = findViewById(R.id.appointment_goal);
                EditText edit_time = findViewById(R.id.editTime);

                TextView textView = findViewById(R.id.textViewBanana);

                textView.setText(String.join(
                        ", ",
                        name_surname.getText(),
                        species[species_id],
                        age_number.getText(),
                        appointment_goal.getText(),
                        edit_time.getText()
                ));
            }
        });
    }
}