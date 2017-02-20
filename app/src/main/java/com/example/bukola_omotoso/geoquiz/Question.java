package com.example.bukola_omotoso.geoquiz;

/**
 * Created by bukola_omotoso on 20/02/2017.
 */

public class Question {
    private int textResId;
    private boolean answerTrue;

    public Question(int textResId, boolean answerTrue)  {
        this.textResId = textResId;
        this.answerTrue = answerTrue;
    }

    public int getTextResId() {
        return textResId;
    }

    public void setTextResId(int textResId) {
        this.textResId = textResId;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }
}
