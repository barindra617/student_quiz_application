package com.example.studentquizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnComputer, btnCars, btnAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnComputer = findViewById(R.id.btnComputer);
        btnCars     = findViewById(R.id.btnCars);
        btnAnime    = findViewById(R.id.btnAnime);

        btnComputer.setOnClickListener(v -> startQuiz("computer"));
        btnCars.setOnClickListener(v -> startQuiz("cars"));
        btnAnime.setOnClickListener(v -> startQuiz("anime"));
    }

    void startQuiz(String category) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}