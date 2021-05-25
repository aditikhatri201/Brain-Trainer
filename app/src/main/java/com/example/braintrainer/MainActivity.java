package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    TextView sumTextView;
    int loctionOfCorrectAnswer;
    TextView resultTextView;
    int score=0;
    int numberOfQuestion;
    TextView scoreTextView;
    TextView timerTextView;
    ArrayList<Integer>answer=new ArrayList<Integer>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view){
        score=0;
        numberOfQuestion=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        newQuestion();

        new CountDownTimer(30100,1000){


            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();


    }

    public void go(View view){
       goButton.setVisibility(View.INVISIBLE);
       gameLayout.setVisibility(View.VISIBLE);
    }
    public void chooseAnswer(View view){
       if(Integer.toString(loctionOfCorrectAnswer).equals(view.getTag().toString())) {
           resultTextView.setText("Correct!");
           score++;
       }else {
           resultTextView.setText("Wrong :(");
       }
       numberOfQuestion++;
       scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
       newQuestion();
    }
    public void newQuestion(){

        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);


        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        loctionOfCorrectAnswer=rand.nextInt(4);

        answer.clear();

        for (int i=0;i<4;i++){
            if(loctionOfCorrectAnswer==i){
                answer.add(a+b);
            }else {
                int wrongAnswer=rand.nextInt(41);
                while (wrongAnswer==a+b){
                    wrongAnswer=rand.nextInt(41);
                }
                answer.add(wrongAnswer);
            }

        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton=findViewById(R.id.goButton);
        sumTextView=findViewById(R.id.questionTextView);
        resultTextView=findViewById(R.id.resultTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);
        playAgainButton=findViewById(R.id.playAgainButton);
        gameLayout=findViewById(R.id.gameLayout);

        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

        playAgain(findViewById(R.id.playAgainButton));
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);



    }
}