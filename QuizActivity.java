package com.example.studentquizapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    TextView tvQuestion, tvScore, tvProgress, tvFeedback;
    RadioGroup optionsGroup;
    RadioButton option1, option2, option3, option4;
    Button btnSubmit, btnNext;
    ProgressBar progressBar;

    String[] questions, answers;
    String[][] options;
    int currentIndex = 0, score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion   = findViewById(R.id.tvQuestion);
        tvScore      = findViewById(R.id.tvScore);
        tvProgress   = findViewById(R.id.tvProgress);
        tvFeedback   = findViewById(R.id.tvFeedback);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1      = findViewById(R.id.option1);
        option2      = findViewById(R.id.option2);
        option3      = findViewById(R.id.option3);
        option4      = findViewById(R.id.option4);
        btnSubmit    = findViewById(R.id.btnSubmit);
        btnNext      = findViewById(R.id.btnNext);
        progressBar  = findViewById(R.id.progressBar);

        String category = getIntent().getStringExtra("category");

        if (category != null) {
            if (category.equals("computer")) {
                questions = QuestionBank.computerQuestions;
                answers   = QuestionBank.computerAnswers;
                options   = QuestionBank.computerOptions;
            } else if (category.equals("cars")) {
                questions = QuestionBank.carQuestions;
                answers   = QuestionBank.carAnswers;
                options   = QuestionBank.carOptions;
            } else {
                questions = QuestionBank.animeQuestions;
                answers   = QuestionBank.animeAnswers;
                options   = QuestionBank.animeOptions;
            }
        }

        if (questions != null) {
            progressBar.setMax(questions.length);
            loadQuestion();
        }

        btnSubmit.setOnClickListener(v -> checkAnswer());
        btnNext.setOnClickListener(v -> nextQuestion());
    }

    void loadQuestion() {
        optionsGroup.clearCheck();
        tvQuestion.setText(questions[currentIndex]);
        tvProgress.setText("Question " + (currentIndex+1) + " of " + questions.length);
        tvScore.setText("Score: " + score);
        progressBar.setProgress(currentIndex + 1);
        tvFeedback.setText("");
        option1.setText(options[currentIndex][0]);
        option2.setText(options[currentIndex][1]);
        option3.setText(options[currentIndex][2]);
        option4.setText(options[currentIndex][3]);
        btnSubmit.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.GONE);
    }

    void checkAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton selected = findViewById(selectedId);
        String userAnswer = selected.getText().toString();

        if (userAnswer.equals(answers[currentIndex])) {
            score++;
            tvFeedback.setText("✅ Correct!");
            tvFeedback.setTextColor(Color.parseColor("#4CAF50"));
        } else {
            tvFeedback.setText("❌ Wrong! Answer: " + answers[currentIndex]);
            tvFeedback.setTextColor(Color.parseColor("#F44336"));
        }

        tvScore.setText("Score: " + score);
        btnSubmit.setVisibility(View.GONE);
        btnNext.setVisibility(View.VISIBLE);
    }

    void nextQuestion() {
        currentIndex++;
        if (currentIndex < questions.length) {
            loadQuestion();
        } else {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("total", questions.length);
            startActivity(intent);
            finish();
        }
    }
}
