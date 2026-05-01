package com.example.studentquizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 20);

        TextView tvResult  = findViewById(R.id.tvResult);
        TextView tvMessage = findViewById(R.id.tvMessage);
        Button   btnRetry  = findViewById(R.id.btnRetry);

        tvResult.setText(score + " / " + total);

        if (score >= 18)      tvMessage.setText("🏆 Outstanding!");
        else if (score >= 14) tvMessage.setText("🎉 Great job!");
        else if (score >= 10) tvMessage.setText("👍 Not bad!");
        else                  tvMessage.setText("📚 Keep studying!");

        btnRetry.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
