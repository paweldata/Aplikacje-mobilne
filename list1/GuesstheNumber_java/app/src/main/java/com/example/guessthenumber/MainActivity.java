package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int number;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRandomNumber();
    }

    private void setRandomNumber() {
        Random rand = new Random();
        this.number = rand.nextInt(100) + 1;
    }

    public void guess(View view) {
        EditText input = (EditText)findViewById(R.id.input);

        try {
            int guessNumber = Integer.parseInt(input.getText().toString());
            this.score++;
            analyzeInputNumber(guessNumber);
        } catch (NumberFormatException e) {
            showToast("Give number");
        }
    }

    private void analyzeInputNumber(int guessNumber) {
        if (guessNumber > this.number) {
            UpdateScoreText(Typeface.DEFAULT);
            showToast("Too high");
        } else if (guessNumber < this.number) {
            UpdateScoreText(Typeface.DEFAULT);
            showToast("Too low");
        } else {
            UpdateScoreText(Typeface.DEFAULT_BOLD);
            showToast("You guessed!");
            setGuessButtonListener(null);
        }
    }

    private void showToast(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    private void setGuessButtonListener(View.OnClickListener fun) {
        Button guessButton = findViewById(R.id.guessButton);
        guessButton.setOnClickListener(fun);
    }

    private void UpdateScoreText(Typeface type) {
        TextView textScore = (TextView) findViewById(R.id.textScore);
        textScore.setText("Score : " + Integer.toString(this.score));
        textScore.setTypeface(type);
    }

    public void restart(View view) {
        this.score = 0;
        UpdateScoreText(Typeface.DEFAULT);
        cleanInput();
        setGuessButtonListener(this::guess);
        setRandomNumber();
    }

    private void cleanInput() {
        EditText input = (EditText)findViewById(R.id.input);
        input.setText("");
    }
}
