package com.example.rzutkoscmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

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

        ImageView[] dices = new ImageView[5];
        Random random = new Random();

        dices[0] = findViewById(R.id.dice1);
        dices[1] = findViewById(R.id.dice2);
        dices[2] = findViewById(R.id.dice3);
        dices[3] = findViewById(R.id.dice4);
        dices[4] = findViewById(R.id.dice5);

        TextView rollScoreText = findViewById(R.id.rollScore);
        TextView gameScoreText = findViewById(R.id.gameScore);

        final int[] totalScore = {0};
        Button rollButton = findViewById(R.id.roll);
        Button resetButton = findViewById(R.id.reset);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> rolled = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    int value = random.nextInt(6) + 1;
                    rolled.add(value);
                    dices[i].setImageResource(getResources().getIdentifier("k" + value, "drawable", getPackageName()));
                }

                int score = 0;
                while (!rolled.isEmpty()) {
                    int value = rolled.get(0);
                    int score2 = value;
                    rolled.remove(0);
                    for (int i = 0; i < rolled.size();) {
                        if (rolled.get(i) == value) {
                            score2 += value;
                            rolled.remove(i);
                            continue;
                        }
                        i++;
                    }
                    if (score2 != value) score += score2;
                }
                totalScore[0] += score;
                rollScoreText.setText(getString(R.string.current_result) + score);
                gameScoreText.setText(getString(R.string.result) + totalScore[0]);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalScore[0] = 0;
                rollScoreText.setText(getString(R.string.current_result));
                gameScoreText.setText(getString(R.string.result));

                for (int i = 0; i < dices.length; i++) {
                    dices[i].setImageResource(R.drawable.question);
                }
            }
        });
    }

    private void roll() {

    }

    private void reset() {

    }
}