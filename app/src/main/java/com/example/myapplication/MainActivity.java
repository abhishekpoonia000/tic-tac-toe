package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private int currentPlayer;
    private String player1Name, player2Name;
    private int scorePlayer1, scorePlayer2;
    private int count;
    private TextView player1NameTextView, player2NameTextView, player1ScoreTextView, player2ScoreTextView, turnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            player1NameTextView = findViewById(R.id.player1Name);
            player2NameTextView = findViewById(R.id.player2Name);
            player1ScoreTextView = findViewById(R.id.player1Score);
            player2ScoreTextView = findViewById(R.id.player2Score);
            turnTextView = findViewById(R.id.turnTextView);

            Intent intent = getIntent();
            player1Name = intent.getStringExtra("PLAYER1_NAME");
            player2Name = intent.getStringExtra("PLAYER2_NAME");

            player1NameTextView.setText(player1Name);
            player2NameTextView.setText(player2Name);
            player1ScoreTextView.setText("Score: 0");
            player2ScoreTextView.setText("Score: 0");

            currentPlayer = 1;
            turnTextView.setText(player1Name + "'s turn");

            btn1 = findViewById(R.id.btn1);
            btn2 = findViewById(R.id.btn2);
            btn3 = findViewById(R.id.btn3);
            btn4 = findViewById(R.id.btn4);
            btn5 = findViewById(R.id.btn5);
            btn6 = findViewById(R.id.btn6);
            btn7 = findViewById(R.id.btn7);
            btn8 = findViewById(R.id.btn8);
            btn9 = findViewById(R.id.btn9);

            btn1.setTag(0);
            btn2.setTag(1);
            btn3.setTag(2);
            btn4.setTag(3);
            btn5.setTag(4);
            btn6.setTag(5);
            btn7.setTag(6);
            btn8.setTag(7);
            btn9.setTag(8);

            // Initialize the game board
            resetBoard();
        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
        }
    }

    // Reset the board and variables
    private void resetBoard() {
        count = 0;
        currentPlayer = 1;
        for (int i = 0; i < 9; i++) {
            setButtonText(i, "");
        }
        turnTextView.setText(player1Name + "'s turn");
    }

    // Handle button clicks for the game buttons
    public void check(View v) {
        Button button = (Button) v;
        int tag = (int) button.getTag();

        if (button.getText().toString().equals("")) {
            if (currentPlayer == 1) {
                button.setText("X");
                currentPlayer = 2;
                turnTextView.setText(player2Name + "'s turn");
            } else {
                button.setText("O");
                currentPlayer = 1;
                turnTextView.setText(player1Name + "'s turn");
            }
            count++;
            checkWinner();
        }
    }


    private void checkWinner() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = getButtonText(i);
        }

        int[][] winningPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] positions : winningPositions) {
            if (!board[positions[0]].isEmpty() &&
                    board[positions[0]].equals(board[positions[1]]) &&
                    board[positions[1]].equals(board[positions[2]])) {
                highlightWinningButtons(positions);
                updateScores(board[positions[0]]);
                return;
            }
        }

        // If all buttons are filled with no winner
        if (count == 9) {
            Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
            resetBoard();
        }
    }

    // Highlight winning buttons and update scores
    private void highlightWinningButtons(int[] positions) {
        Button[] buttons = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        animationSet.addAnimation(alphaAnimation);
        for (int i = 0; i < positions.length; i++) {
            buttons[positions[i]].startAnimation(animationSet);
        }
        resetBoard();
    }

    // Update scores based on the winner
    private void updateScores(String winner) {
        if (winner.equals("X")) {
            scorePlayer1++;
            player1ScoreTextView.setText("Score: " + scorePlayer1);
            Toast.makeText(this, player1Name + " wins!", Toast.LENGTH_SHORT).show();
        } else {
            scorePlayer2++;
            player2ScoreTextView.setText("Score: " + scorePlayer2);
            Toast.makeText(this, player2Name + " wins!", Toast.LENGTH_SHORT).show();
        }
    }

    // Utility methods to get and set button text
    private void setButtonText(int index, String text) {
        switch (index) {
            case 0:
                btn1.setText(text);
                break;
            case 1:
                btn2.setText(text);
                break;
            case 2:
                btn3.setText(text);
                break;
            case 3:
                btn4.setText(text);
                break;
            case 4:
                btn5.setText(text);
                break;
            case 5:
                btn6.setText(text);
                break;
            case 6:
                btn7.setText(text);
                break;
            case 7:
                btn8.setText(text);
                break;
            case 8:
                btn9.setText(text);
                break;
        }
    }

    private String getButtonText(int index) {
        switch (index) {
            case 0:
                return btn1.getText().toString();
            case 1:
                return btn2.getText().toString();
            case 2:
                return btn3.getText().toString();
            case 3:
                return btn4.getText().toString();
            case 4:
                return btn5.getText().toString();
            case 5:
                return btn6.getText().toString();
            case 6:
                return btn7.getText().toString();
            case 7:
                return btn8.getText().toString();
            case 8:
                return btn9.getText().toString();
            default:
                return "";
        }
    }

    // Handle Play Again button click
    public void playagainbuttonclick(View view) {
        resetBoard();
    }

    // Handle Home button click
    public void homebuttonclick(View view) {
        finish();
    }

}
