package com.example.trialwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    TextView userSelectionTextView, compSelectionTextView, wonLostTieTextView, scoreTextView;

    int userScore = 0, compScore = 0;
    Random random;
    Button exitbt;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        userSelectionTextView = findViewById(R.id.userSelectionTextView);
        compSelectionTextView = findViewById(R.id.compSelectionTextView);
        wonLostTieTextView = findViewById(R.id.wonLostTieTextView);
        scoreTextView = findViewById(R.id.scoreTextView);




    exitbt = (Button)findViewById(R.id.exitbt);
    exitbt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);

        }
    });



        userSelectionTextView.setText("");
        compSelectionTextView.setText("");
        wonLostTieTextView.setText("");

        random = new Random();

    }

    public void rpsButtonSelected(View view){
        int userSelection = Integer.parseInt(view.getTag().toString());
        Log.i(TAG, "rpsButtonSelected: " + userSelection);
        matchGame(userSelection);
        

    }
    public void resetButton(View view){
        wonLostTieTextView.setText("");
        userSelectionTextView.setText("");
        compSelectionTextView.setText("");
        userScore = 0;
        compScore = 0;
        setScoreTextView(userScore,compScore);
    }



    private void matchGame(int userSelection)
    {
        int low = 1, high = 3;
        int compSelection = random.nextInt(high)+low;

        if(userSelection == compSelection){
            //Tie
            wonLostTieTextView.setText("Tie");
        }else if(Math.abs((userSelection-compSelection))%3==1){
            //Comp win
            compScore++;
            wonLostTieTextView.setText("You lose");
        }else {
            //user win
            userScore++;
            wonLostTieTextView.setText("You win");
        }

        switch(userSelection){
            case 1: userSelectionTextView.setText("Rock");
            break;

            case 2: userSelectionTextView.setText("Paper");
            break;

            case 3: userSelectionTextView.setText("Scissors");
            break;

        }

        switch(compSelection){
            case 1: compSelectionTextView.setText("Rock");
                break;

            case 2: compSelectionTextView.setText("Paper");
                break;

            case 3: compSelectionTextView.setText("Scissors");
                break;

        }

        setScoreTextView(userScore, compScore);


    }

    private void setScoreTextView(int userScore, int compScore){
        scoreTextView.setText(String.valueOf(userScore) + ":" + String.valueOf(compScore));
    }
}