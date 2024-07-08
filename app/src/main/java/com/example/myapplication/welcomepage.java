package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class welcomepage extends AppCompatActivity {
    private EditText player1EditText, player2EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);

        player1EditText = findViewById(R.id.editTextTextPersonName);
        player2EditText = findViewById(R.id.editTextTextPersonName2);
    }

    public void playbuttonClick(View view) {
        String player1Name = player1EditText.getText().toString();
        String player2Name = player2EditText.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("PLAYER1_NAME", player1Name);
        intent.putExtra("PLAYER2_NAME", player2Name);
        startActivity(intent);
    }
}
