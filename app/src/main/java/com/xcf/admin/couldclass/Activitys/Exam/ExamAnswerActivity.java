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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xcf.admin.couldclass.Dao.ExamServiceyhs;
import com.xcf.admin.couldclass.Entity.examroom.Appques;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MessageContext;
import com.xcf.admin.couldclass.MyContext.MyApp;
import com.xcf.admin.couldclass.R;

import java.util.ArrayList;
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
    TextView textViewtrue;
    Button last;
    Button next;
    Button jiaojuan;
    LinearLayout linearLayouttrue;
    int quesnumber = 1;
    String userid; //用户id
    Long q_id;//题号
    ExamServiceyhs setanswer = HttpHelper.getInstance().getRetrofitStr().create(ExamServiceyhs.class);
    List<String> list = new ArrayList<>();
    List<CheckBox> checkBoxes = new ArrayList<>();
    Appques.quesyhs quesyhs = MyApp.appquesmain.new quesyhs();
    int falsei = 0;//第几个错题
    String yesorno = null;//用于判断是错题解析还是全部解析或者不是解析

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
                List<String> list1 = new ArrayList<>();
                list1.add("A");
                SPadd(list1, radioButtonA);
                break;
            }
            case R.id.exam_answer_radioButtonB: {
                List<String> list1 = new ArrayList<>();
                list1.add("B");
                SPadd(list1, radioButtonB);
                break;
            }
            case R.id.exam_answer_radioButtonC: {
                List<String> list1 = new ArrayList<>();
                list1.add("C");
                SPadd(list1, radioButtonC);
                break;
            }
            case R.id.exam_answer_radioButtonD: {
                List<String> list1 = new ArrayList<>();
                list1.add("D");
                SPadd(list1, radioButtonD);
                break;
            }
            case R.id.exam_answer_last: {
                lastnext("last");
                break;
            }
            case R.id.exam_answer_next: {
                lastnext("next");
                break;
            }
            case R.id.exam_answer_jj: {
                lastnext("jiaojuan");
            }
        }
    }

    public void setListener() {
        examtitle = findViewById(R.id.exam_answer_title);
        questionnum = findViewById(R.id.exam_answer_quesnum);
        questionsum = findViewById(R.id.exam_answer_quesnum1);
        ques = findViewById(R.id.exam_answer_ques);
        textViewtrue = findViewById(R.id.exam_answer_true);
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
        jiaojuan = findViewById(R.id.exam_answer_jj);
        linearLayouttrue = findViewById(R.id.and_right);
        Intent intent = getIntent();
        yesorno = intent.getStringExtra("goit");
            if (yesorno.equals("no1")) {
                linearLayouttrue.setVisibility(View.VISIBLE);
                radioButtonA.setEnabled(false);
                radioButtonB.setEnabled(false);
                radioButtonC.setEnabled(false);
                radioButtonD.setEnabled(false);
                jiaojuan.setEnabled(false);
            } else if (yesorno.equals("no2")) {
                linearLayouttrue.setVisibility(View.VISIBLE);
                radioButtonA.setEnabled(false);
                radioButtonB.setEnabled(false);
                radioButtonC.setEnabled(false);
                radioButtonD.setEnabled(false);
                jiaojuan.setEnabled(false);
                if (falsei < MyApp.falselist.size()) {
                    quesnumber = MyApp.falselist.get(falsei);
                }
            }

        radioButtonA.setOnClickListener(this);
        radioButtonB.setOnClickListener(this);
        radioButtonC.setOnClickListener(this);
        radioButtonD.setOnClickListener(this);
        last.setOnClickListener(this);
        next.setOnClickListener(this);
        jiaojuan.setOnClickListener(this);

        String position = intent.getStringExtra("position1");
        if (position != null)
        {
            quesnumber=Integer.parseInt(position);
        }

    }

    public void getdata() {
        examtitle.setText(MyApp.papername);//试卷名称
        questionnum.setText("" + quesnumber);//第几题
        questionsum.setText("/" + MyApp.questionsum);//总共多少题
        radioButtonA.setChecked(false);
        radioButtonB.setChecked(false);
        radioButtonC.setChecked(false);
        radioButtonD.setChecked(false);
        if (quesnumber <= MyApp.appquesmain.getS().size()) {
            int i = quesnumber - 1;
            quesyhs = MyApp.appquesmain.getS().get(i);
            q_id = quesyhs.getQuesyhsid();
            ques.setText(quesyhs.getQuesyhsname());
            getselected(quesyhs.getQuesyhsselected());
            radioButtonA.setText("A、" + quesyhs.getQuesyhsAname());
            radioButtonB.setText("B、" + quesyhs.getQuesyhsBname());
            radioButtonC.setVisibility(View.VISIBLE);
            radioButtonD.setVisibility(View.VISIBLE);
            radioButtonC.setText("C、" + quesyhs.getQuesyhsCname());
            radioButtonD.setText("D、" + quesyhs.getQuesyhsDname());
            String trueselect = "";
            for (Object object : quesyhs.getQuesyhstrue()
                    ) {
                trueselect += object.toString();
            }
            textViewtrue.setText(trueselect);
        } else if (quesnumber <= (MyApp.appquesmain.getS().size() + MyApp.appquesmain.getD().size())) {
            int i = quesnumber - MyApp.appquesmain.getS().size() - 1;
            quesyhs = MyApp.appquesmain.getD().get(i);
            q_id = quesyhs.getQuesyhsid();
            ques.setText(quesyhs.getQuesyhsname());
            getselected(quesyhs.getQuesyhsselected());
            radioButtonA.setText("A、" + quesyhs.getQuesyhsAname());
            radioButtonB.setText("B、" + quesyhs.getQuesyhsBname());
            radioButtonC.setVisibility(View.VISIBLE);
            radioButtonD.setVisibility(View.VISIBLE);
            radioButtonC.setText("C、" + quesyhs.getQuesyhsCname());
            radioButtonD.setText("D、" + quesyhs.getQuesyhsDname());
            String trueselect = "";
            for (Object object : quesyhs.getQuesyhstrue()
                    ) {
                trueselect += object.toString();
            }
            textViewtrue.setText(trueselect);
        } else if (quesnumber <= MyApp.questionsum) {
            int i = quesnumber - MyApp.appquesmain.getS().size() - MyApp.appquesmain.getD().size() - 1;
            quesyhs = MyApp.appquesmain.getP().get(i);
            q_id = quesyhs.getQuesyhsid();
            ques.setText(quesyhs.getQuesyhsname());
            getselected(quesyhs.getQuesyhsselected());
            radioButtonA.setText("A、" + quesyhs.getQuesyhsAname());
            radioButtonB.setText("B、" + quesyhs.getQuesyhsBname());
            radioButtonC.setVisibility(View.GONE);
            radioButtonD.setVisibility(View.GONE);
            String trueselect = "";
            for (Object object : quesyhs.getQuesyhstrue()
                    ) {
                trueselect += object.toString();
            }
            textViewtrue.setText(trueselect);
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

    public void getselected(List<String> list1) {
        try {
            for (String a : list1
                    ) {
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

    public void SPadd(List<String> ABCD, CheckBox button) {
        if ((!gettype(quesnumber).equals("D")) && (quesnumber != MyApp.questionsum)) {
            Gson gson = new Gson();
            list.addAll(ABCD);
            quesyhs.setQuesyhsselected(list);
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

    public void lastnext(final String string) {
        List<String> list1 = new ArrayList<>();
        if (radioButtonA.isChecked()) {
            list1.add("A");
        }
        if (radioButtonB.isChecked()) {
            list1.add("B");
        }
        if (radioButtonC.isChecked()) {
            list1.add("C");
        }
        if (radioButtonD.isChecked()) {
            list1.add("D");
        }
        list.addAll(list1);
        quesyhs.setQuesyhsselected(list);
        Gson gson = new Gson();
        if ((!yesorno.equals("no1")) && (!yesorno.equals("no2"))) {
            Call<String> call = setanswer.setselected(userid, MyApp.roomid, gson.toJson(list), q_id.toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.e("dfdadf", "onResponse: 成功了没");
                    if (string.equals("jiaojuan")) {
                        Call<String> call1 = setanswer.changecomplete(userid, MyApp.roomid);
                        call1.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Intent intent = new Intent(ExamAnswerActivity.this, ExamEndActivity.class);
                                ExamAnswerActivity.this.finish();
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(ExamAnswerActivity.this, MessageContext.INTNET_ERROR, Toast.LENGTH_SHORT).show();
                            }
                        });//保存本页内容
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
            });//保存本页内容
        }
        list = new ArrayList<>();
        if (string.equals("last") && quesnumber != 1) {
                if (yesorno.equals("no2")) {
                    if (falsei < MyApp.falselist.size() && falsei > 0) {
                        falsei -= 1;
                        quesnumber = MyApp.falselist.get(falsei);
                    }
                }
            else {
                quesnumber -= 1;
            }
        }
        if (string.equals("next") && quesnumber != MyApp.questionsum) {
                if (yesorno.equals("no2")) {
                    if (falsei < MyApp.falselist.size() - 1) {
                        Log.e("", "lastnext: " + falsei + "size" + MyApp.falselist.size());
                        falsei += 1;
                        quesnumber = MyApp.falselist.get(falsei);
                    }
                }
            else {
                quesnumber += 1;
            }
        }
        getdata();
    }
}
