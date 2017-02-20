package com.example.bukola_omotoso.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private Button trueButton;
    private Button falseButton;
    private Button previousButton;
    private Button nextButton;
    private TextView questionText;
    private int questionIndex = 0 ;
    private int messageResId;
    boolean answer;
    private Question[] questionBank = {
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_oceans,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);
        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        previousButton = (Button) findViewById(R.id.previous_button);
        nextButton = (Button) findViewById(R.id.next_button);
        questionText = (TextView) findViewById(R.id.question_text);
        if (savedInstanceState != null)
        {
            questionIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }
        updateQuestion();
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAnswerFeedback(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAnswerFeedback(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionIndex = (questionIndex+1) % questionBank.length;
                updateQuestion();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionIndex > 0) {
                    questionIndex = (questionIndex - 1) % questionBank.length;
                }   else    {
                    questionIndex = (questionIndex - 1) + questionBank.length;
                }
                updateQuestion();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstantState)   {
        super.onSaveInstanceState(savedInstantState);
        savedInstantState.putInt(KEY_INDEX,questionIndex);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void displayAnswerFeedback(boolean answerPressed)    {
        answer = questionBank[questionIndex].isAnswerTrue();
        if (answer == answerPressed) {
            messageResId = R.string.correct_toast;
        }
        else{
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_LONG).show();
    }

    private void updateQuestion()  {
        questionText.setText(questionBank[questionIndex].getTextResId());
    }
}
