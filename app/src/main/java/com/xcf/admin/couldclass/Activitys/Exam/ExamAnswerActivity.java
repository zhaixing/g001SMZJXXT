package com.xcf.admin.couldclass.Activitys.Exam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xcf.admin.couldclass.Dao.ExamServiceyhs;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MyApp;
import com.xcf.admin.couldclass.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExamAnswerActivity extends AppCompatActivity implements View.OnClickListener {


    CheckBox radioButtonA;
    CheckBox radioButtonB;
    CheckBox radioButtonC;
    CheckBox radioButtonD;
    TextView examtitle;
    TextView questionnum;
    TextView questionsum;
    TextView ques;
    Button last;
    Button next;
    int quesnumber = 1;
    String userid; //用户id
    Long q_id;//题号
    HashMap<Integer, List<String>> hashMap = new HashMap<>();
    ExamServiceyhs setanswer = HttpHelper.getInstance().getRetrofitStr().create(ExamServiceyhs.class);
    List<String> list = new ArrayList<>();
    List<CheckBox> checkBoxes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_answer);
        SharedPreferences sp = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        userid = sp.getString("userid", null);
        setListener();
        getdata();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exam_answer_radioButtonA: {
                SPadd("A", radioButtonA);
                getselected();
                break;
            }
            case R.id.exam_answer_radioButtonB: {
                SPadd("B", radioButtonB);
                getselected();
                break;
            }
            case R.id.exam_answer_radioButtonC: {
                SPadd("C", radioButtonC);
                getselected();
                break;
            }
            case R.id.exam_answer_radioButtonD: {
                SPadd("D", radioButtonD);
                getselected();
                break;
            }
            case R.id.exam_answer_last: {
                if (quesnumber != 1) {
                    if (radioButtonA.isChecked()) {
                        list.add("A");
                    }
                    if (radioButtonB.isChecked()) {
                        list.add("B");
                    }
                    if (radioButtonC.isChecked()) {
                        list.add("C");
                    }
                    if (radioButtonD.isChecked()) {
                        list.add("D");
                    }
                    Gson gson = new Gson();
                    hashMap.put(quesnumber, list);
                    Call call = setanswer.setselected(userid, MyApp.roomid, gson.toJson(list), q_id.toString());
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.e("这次", "onResponse: 成功了");
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                        }
                    });
                    quesnumber -= 1;
                    list = new ArrayList<>();
                    getdata();
                    getselected();
                }
                break;
            }
            case R.id.exam_answer_next: {
                if (radioButtonA.isChecked()) {
                    list.add("A");
                }
                if (radioButtonB.isChecked()) {
                    list.add("B");
                }
                if (radioButtonC.isChecked()) {
                    list.add("C");
                }
                if (radioButtonD.isChecked()) {
                    list.add("D");
                }
                Gson gson = new Gson();
                hashMap.put(quesnumber, list);
                Call call = setanswer.setselected(userid, MyApp.roomid, gson.toJson(list), q_id.toString());
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Log.e("这次", "onResponse: 成功了");
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                    }
                });
                if (quesnumber != MyApp.questionsum) {
                    list = new ArrayList<>();
                    quesnumber += 1;
                    getdata();
                    getselected();
                } else {
                    Intent intent = new Intent(this, ExamEndActivity.class);
                    startActivity(intent);
                }
                break;
            }
        }
    }

    public void setListener() {
        examtitle = findViewById(R.id.exam_answer_title);
        questionnum = findViewById(R.id.exam_answer_quesnum);
        questionsum = findViewById(R.id.exam_answer_quesnum1);
        ques = findViewById(R.id.exam_answer_ques);
        radioButtonA = findViewById(R.id.exam_answer_radioButtonA);
        radioButtonB = findViewById(R.id.exam_answer_radioButtonB);
        radioButtonC = findViewById(R.id.exam_answer_radioButtonC);
        radioButtonD = findViewById(R.id.exam_answer_radioButtonD);
        checkBoxes.add(radioButtonA);
        checkBoxes.add(radioButtonB);
        checkBoxes.add(radioButtonC);
        checkBoxes.add(radioButtonD);
        last = findViewById(R.id.exam_answer_last);
        next = findViewById(R.id.exam_answer_next);
        radioButtonA.setOnClickListener(this);
        radioButtonB.setOnClickListener(this);
        radioButtonC.setOnClickListener(this);
        radioButtonD.setOnClickListener(this);
        last.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    public void getdata() {
        next.setText("下一题");
        examtitle.setText(MyApp.papername);//试卷名称
        questionnum.setText("" + quesnumber);//第几题
        questionsum.setText("/" + MyApp.questionsum);//总共多少题
        radioButtonA.setChecked(false);
        radioButtonB.setChecked(false);
        radioButtonC.setChecked(false);
        radioButtonD.setChecked(false);
        if (quesnumber <= MyApp.appquesmain.getS().size()) {
            int i = quesnumber - 1;
            q_id = MyApp.appquesmain.getS().get(i).getQuesyhsid();
            ques.setText(MyApp.appquesmain.getS().get(i).getQuesyhsname());
            radioButtonA.setText("A、" + MyApp.appquesmain.getS().get(i).getQuesyhsAname());
            radioButtonB.setText("B、" + MyApp.appquesmain.getS().get(i).getQuesyhsBname());
            radioButtonC.setVisibility(View.VISIBLE);
            radioButtonD.setVisibility(View.VISIBLE);
            radioButtonC.setText("C、" + MyApp.appquesmain.getS().get(i).getQuesyhsCname());
            radioButtonD.setText("D、" + MyApp.appquesmain.getS().get(i).getQuesyhsDname());
        } else if (quesnumber <= (MyApp.appquesmain.getS().size() + MyApp.appquesmain.getD().size())) {
            int i = quesnumber - MyApp.appquesmain.getS().size() - 1;
            q_id = MyApp.appquesmain.getD().get(i).getQuesyhsid();
            ques.setText(MyApp.appquesmain.getD().get(i).getQuesyhsname());
            radioButtonA.setText("A、" + MyApp.appquesmain.getD().get(i).getQuesyhsAname());
            radioButtonB.setText("B、" + MyApp.appquesmain.getD().get(i).getQuesyhsBname());
            radioButtonC.setVisibility(View.VISIBLE);
            radioButtonD.setVisibility(View.VISIBLE);
            radioButtonC.setText("C、" + MyApp.appquesmain.getD().get(i).getQuesyhsCname());
            radioButtonD.setText("D、" + MyApp.appquesmain.getD().get(i).getQuesyhsDname());
        } else if (quesnumber <= MyApp.questionsum) {
            if (quesnumber == MyApp.questionsum) {
                next.setText("交卷");
            }
            int i = quesnumber - MyApp.appquesmain.getS().size() - MyApp.appquesmain.getD().size() - 1;
            q_id = MyApp.appquesmain.getP().get(i).getQuesyhsid();
            ques.setText(MyApp.appquesmain.getP().get(i).getQuesyhsname());
            radioButtonA.setText("A、" + MyApp.appquesmain.getP().get(i).getQuesyhsAname());
            radioButtonB.setText("B、" + MyApp.appquesmain.getP().get(i).getQuesyhsBname());
            radioButtonC.setVisibility(View.GONE);
            radioButtonD.setVisibility(View.GONE);
        }
    }

    public String gettype(int num) {   //判断类型
        String a = null;
        if (num <= MyApp.appquesmain.getS().size()) {
            a = "S";
        } else if (quesnumber <= (MyApp.appquesmain.getS().size() + MyApp.appquesmain.getD().size())) {
            a = "D";
        } else if (quesnumber <= MyApp.questionsum) {
            a = "P";
        }
        return a;
    }

    public void getselected() {
        try {
            System.out.println(quesnumber);
            hashMap.get(quesnumber);
            for (String a : hashMap.get(quesnumber)
                    ) {
                System.out.println(a);
                if (a.equals("A")) {
                    radioButtonA.setChecked(true);
                } else if (a.equals("B")) {
                    radioButtonB.setChecked(true);
                } else if (a.equals("C")) {
                    radioButtonC.setChecked(true);
                } else if (a.equals("D")) {
                    radioButtonD.setChecked(true);
                }
            }
        } catch (Exception e) {
        }
    }

    public void SPadd(String ABCD, CheckBox button) {
        if ((!gettype(quesnumber).equals("D")) && (quesnumber != MyApp.questionsum)) {
            Gson gson = new Gson();
            System.out.println("j");
            list.add(ABCD);
            hashMap.put(quesnumber, list);
            Call<String> call = setanswer.setselected(userid, MyApp.roomid, gson.toJson(list), q_id.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.e("dfdadf", "onResponse: 成功了没");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
            list = new ArrayList<>();
            quesnumber += 1;
            getdata();
        } else if ((!gettype(quesnumber).equals("D")) && (quesnumber == MyApp.questionsum)) {
            //不是多选  的最后一题
            for (CheckBox button1 : checkBoxes
                    ) {
                if (button1 != button) {
                    button1.setChecked(false);
                } else {
                    button1.setChecked(true);
                }
            }
        }
    }
}
