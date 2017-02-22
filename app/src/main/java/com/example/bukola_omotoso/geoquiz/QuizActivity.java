package com.example.bukola_omotoso.geoquiz;

import android.app.Activity;
import android.content.Intent;
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
    private static final String KEY_CHEATED = "cheated";
    private static final String CHEATED_INDEX = "cheated_index";
    private Button trueButton;
    private Button falseButton;
    private Button previousButton;
    private Button nextButton;
    private Button cheatButton;
    private TextView questionText;
    private int questionIndex = 0 ;
    private int messageResId;
    private boolean answer;
    private boolean isCheater;
    private static final int REQUEST_CODE_CHEAT = 0;
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
        cheatButton = (Button) findViewById(R.id.cheat_button);
        questionText = (TextView) findViewById(R.id.question_text);
        if (savedInstanceState != null)
        {
            questionIndex = savedInstanceState.getInt(KEY_INDEX,0);
            isCheater = savedInstanceState.getBoolean(KEY_CHEATED);
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
                isCheater = false;
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

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = questionBank[questionIndex].isAnswerTrue();
                Log.d("ANSWER",answer+"");
                Intent intent = CheatActivity.newIntent(QuizActivity.this,answer);
                //startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE_CHEAT);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstantState)   {
        super.onSaveInstanceState(savedInstantState);
        savedInstantState.putInt(KEY_INDEX,questionIndex);
        savedInstantState.putBoolean(KEY_CHEATED,isCheater);
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
        if (isCheater || questionIndex == getIntent().getIntExtra(CHEATED_INDEX,0))  {
            messageResId = R.string.judgment_toast;
        }
        else

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)   {
        if (resultCode != Activity.RESULT_OK)   {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT)  {
            if (intent == null) {
                return;
            }
            isCheater = CheatActivity.wasAnswerShown(intent);
            intent.putExtra(CHEATED_INDEX,questionIndex);
        }
    }
}
