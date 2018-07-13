package com.xcf.admin.couldclass.Activitys.Exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.xcf.admin.couldclass.R;

public class ExamAnswerActivity extends AppCompatActivity implements View.OnClickListener {


    RadioButton radioButtonA;
    RadioButton radioButtonB;
    RadioButton radioButtonC;
    RadioButton radioButtonD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_answer);
        setListener();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exam_answer_radioButtonA: {

            }
            case R.id.exam_answer_radioButtonB: {

            }
            case R.id.exam_answer_radioButtonC: {

            }
            case R.id.exam_answer_radioButtonD: {
                Intent intent = new Intent(this, ExamEndActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }


    public void setListener() {
        radioButtonA = findViewById(R.id.exam_answer_radioButtonA);
        radioButtonB = findViewById(R.id.exam_answer_radioButtonB);
        radioButtonC = findViewById(R.id.exam_answer_radioButtonC);
        radioButtonD = findViewById(R.id.exam_answer_radioButtonD);
        radioButtonA.setOnClickListener(this);
        radioButtonB.setOnClickListener(this);
        radioButtonC.setOnClickListener(this);
        radioButtonD.setOnClickListener(this);
    }
}
