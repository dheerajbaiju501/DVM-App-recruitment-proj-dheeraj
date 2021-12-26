package com.example.trialwork;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();



    public static final String TAG = "MainActivity";

    TextView userSelectionTextView, compSelectionTextView, wonLostTieTextView, scoreTextView;

    int userScore = 0, compScore = 0;
    Random random;


    Dialog dialog;









    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        userSelectionTextView = findViewById(R.id.userSelectionTextView);
        compSelectionTextView = findViewById(R.id.compSelectionTextView);
        wonLostTieTextView = findViewById(R.id.wonLostTieTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        dia();











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

    public void dia(){
        Button exitbt;
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
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
        if(userScore==5||compScore==5){
            if(userScore>compScore){
                wonLostTieTextView.setText("Yay..... you win!!!");
            }else{
                wonLostTieTextView.setText("Oops... you lose");
            }
            openActivity3();
        }

        setScoreTextView(userScore, compScore);


    }
    public void openActivity3(){
        Intent intent = new Intent(this, MainActivity3.class);

        startActivity(intent);
    }

    private void setScoreTextView(int userScore, int compScore){
        scoreTextView.setText(String.valueOf(userScore) + ":" + String.valueOf(compScore));
    }
}