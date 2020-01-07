package com.example.sdl.basicmathtrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    LinearLayout messageLayout;
    TextView numberAB, tv0, tv1, tv2, tv3, seconds, scoreAB, playAgain;
    Random randomNumber = new Random();
    CountDownTimer playTime;
    boolean playing = false;
    int a;
    int b;
    String sum;
    int correctAnswerPosition;
    long maxPlayTime = 30000;
    int score;
    int tryNumber;
    ArrayList<Integer> answer = new ArrayList<>();


                                                                            //BEGINNING EACH GAME
    public void init(View n){

        playing = true;
        score = 0;
        tryNumber = 0;

        tv0.setClickable(true);
        tv1.setClickable(true);
        tv2.setClickable(true);
        tv3.setClickable(true);

        values();//calling "values" method

        messageLayout = findViewById(R.id.lBegin);
        messageLayout.setVisibility(View.INVISIBLE);

                                                                            // THE COUNTDOWN TIMER!!!
        playTime = new CountDownTimer(maxPlayTime +100 , 1000) {
            @Override
            public void onTick(long l) {
                int secondsLeft = (int)l/1000;

                if(secondsLeft < 10){
                    seconds.setText("0" + Integer.toString(secondsLeft) + "s");
                } else {
                    seconds.setText(Integer.toString(secondsLeft) + "s");
                }//if end

                Log.i("seconds left: ", String.valueOf((int) l / 1000));
            }

            @Override
            public void onFinish() {
                playing = false;
                tv0.setClickable(false);
                tv1.setClickable(false);
                tv2.setClickable(false);
                tv3.setClickable(false);
                seconds.setText("00s");
                playAgain.setText("Great! "+ score + " POINTS!! play again!!");
                messageLayout.setVisibility(View.VISIBLE);
            }
        }.start();

    } //init end




                                            //CHECKING EVERY SELECTED ANSWER AND HANDLING THE SCORE
    public void answer(View n){

        if(playing){

            tryNumber ++;//adding number of tries



            scoreAB.setText(String.valueOf(score) + "/" + String.valueOf(tryNumber));

            int answerTag = Integer.parseInt(n.getTag().toString());

            if(answerTag == correctAnswerPosition){
                score++;
                scoreAB.setText(String.valueOf(score) + "/" + String.valueOf(tryNumber));

                values();

        }//plying if ends


        }//if end

    }// method answer ends


                        //THIS METHOD APPLY THE NUMBERS TO SUM, THE DUMMIES, THE CORRECT ANSWER,
            // CHECK THAT THE CORRECT ANSWER DOES NOT DUPLICATE AND DISPLAY THE NUMBERS ON THE TextViews
    public void values(){

        a = randomNumber.nextInt(49) + 1;
        b = randomNumber.nextInt(48) + 1;
        sum = String.valueOf(a) + " + " + String.valueOf(b);
        numberAB.setText(sum);

        correctAnswerPosition = randomNumber.nextInt(3);

// FILLING ARRAY ANSWERS
        for (int i = 0; i < 4; i++){

            if (i == correctAnswerPosition){
                answer.add(i,a + b);

            } else{

                int incorrect = randomNumber.nextInt(98) + 1;

                while(incorrect == a + b){
                    incorrect = randomNumber.nextInt(98) +1 ;
                }//while ends

                answer.add(i, incorrect);

            }//if end

        }//array for end

//GETTING ARRAY NUMBERS TO DISPLAY
        tv0.setText(Integer.toString(answer.get(0)));
        tv1.setText(Integer.toString(answer.get(1)));
        tv2.setText(Integer.toString(answer.get(2)));
        tv3.setText(Integer.toString(answer.get(3)));

        // con answer.clear se puede borrar la info del array

    }//values method end






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//DECLARING UI ELEMENTS
        numberAB = findViewById(R.id.tvOperation);
        seconds = findViewById(R.id.tvTime);
        scoreAB = findViewById(R.id.tvScore);
        playAgain = findViewById(R.id.tvinfo);

        tv0 = findViewById(R.id.tv0);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv0.setClickable(false);
        tv1.setClickable(false);
        tv2.setClickable(false);
        tv3.setClickable(false);

    }//onCreate end
}//Main end
