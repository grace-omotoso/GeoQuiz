package com.example.bukola_omotoso.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_QUIZ_ANSWER = "com.example.bukola_omotoso.geoquiz.extra_quiz_answer";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.bukola_omotoso.geoquiz.answer_shown";
    private static final String KEY_ANSWER = "answer";
    private boolean answer;
    private Button cheatButton;
    private TextView answerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        cheatButton = (Button) findViewById(R.id.show_answer_button);
        answerText = (TextView) findViewById(R.id.answer_text);
        if (savedInstanceState != null) {
            answer = savedInstanceState.getBoolean(EXTRA_QUIZ_ANSWER,false);
            displayAnswer();
        }
        else {
            answer = getIntent().getBooleanExtra(EXTRA_QUIZ_ANSWER, false);
        }
        Log.d("ANSWER_CHEAT",answer+"");
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAnswer();
                setAnswerShownResult(true);
            }
        });
    }

    public static Intent newIntent(Context context, boolean answerIsTrue)   {
        Intent intent = new Intent(context,CheatActivity.class);
        intent.putExtra(EXTRA_QUIZ_ANSWER,answerIsTrue);
        return intent;
    }

    private void setAnswerShownResult(boolean isAnswerShown)    {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,intent);
    }

    public static boolean wasAnswerShown(Intent intent) {
        return intent.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstantState)  {
        super.onSaveInstanceState(savedInstantState);
        savedInstantState.putBoolean(EXTRA_QUIZ_ANSWER,answer);
    }

    private void displayAnswer() {
        if (answer == true)
            answerText.setText(R.string.true_button);
        else
            answerText.setText(R.string.false_button);
    }

}
