package com.example.wzornikkolorowrgb;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public Button probeButton;
    public TextView rgbValuesView;
    public SeekBar RSeekBar;
    public SeekBar GSeekBar;
    public SeekBar BSeekBar;
    public TextView RCurrentValue;
    public TextView GCurrentValue;
    public TextView BCurrentValue;
    public LinearLayout colorPreviewRect;
    public int[] RGB = { 255, 255, 255 };

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

        colorPreviewRect = findViewById(R.id.colorPreviewRect);
        probeButton = findViewById(R.id.probeButton);
        rgbValuesView = findViewById(R.id.rgbValuesView);
        RSeekBar = findViewById(R.id.RSeekBar);
        GSeekBar = findViewById(R.id.GSeekBar);
        BSeekBar = findViewById(R.id.BSeekBar);
        RCurrentValue = findViewById(R.id.RCurrentValue);
        GCurrentValue = findViewById(R.id.GCurrentValue);
        BCurrentValue = findViewById(R.id.BCurrentValue);



        probeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgbValuesView.setText(String.format("%d, %d, %d",
                    RSeekBar.getProgress(),
                    GSeekBar.getProgress(),
                    BSeekBar.getProgress()
                ));

                rgbValuesView.setBackgroundColor(Color.rgb(
                        RSeekBar.getProgress(),
                        GSeekBar.getProgress(),
                        BSeekBar.getProgress()
                ));
            }
        });

        RSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                RCurrentValue.setText(String.valueOf(progress));
                updateColorPreviewRect();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        GSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                GCurrentValue.setText(String.valueOf(progress));
                updateColorPreviewRect();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        BSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BCurrentValue.setText(String.valueOf(progress));
                updateColorPreviewRect();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void updateColorPreviewRect() {
        colorPreviewRect.setBackgroundColor(Color.rgb(
                RSeekBar.getProgress(),
                GSeekBar.getProgress(),
                BSeekBar.getProgress()
        ));
    }
}