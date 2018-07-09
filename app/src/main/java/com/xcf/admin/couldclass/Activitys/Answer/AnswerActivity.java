package com.xcf.admin.couldclass.Activitys.Answer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xcf.admin.couldclass.Dao.ExamService;
import com.xcf.admin.couldclass.Entity.ques.ResultTemp;
import com.xcf.admin.couldclass.Entity.ques.Selection;
import com.xcf.admin.couldclass.Entity.result.ResultQues;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswerActivity extends AppCompatActivity implements View.OnClickListener {


    ExamService examService = HttpHelper.getInstance().getRetrofit().create(ExamService.class);

    int quesindex = 0;

    Long examroomid = 0l;

    List<ResultQues> quesEntityList = new ArrayList<>();


    Button btn_sub;
    Button btn_prv;
    Button btn_next;


    RadioGroup radioGroup;
    RadioButton r_SA;
    RadioButton r_SB;
    RadioButton r_SC;
    RadioButton r_SD;

    LinearLayout ch_linear;
    CheckBox ch_A;
    CheckBox ch_B;
    CheckBox ch_C;
    CheckBox ch_D;

    TextView txt_Ques;

    List<CheckBox> checkBoxes = new ArrayList<>();
    List<RadioButton> radioButtons = new ArrayList<>();

    ResultTemp resultTemp = new ResultTemp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Intent intent = getIntent();
        examroomid = intent.getLongExtra("examroomid", 0l);


        SetListener();
    }

    private void SetListener() {
        txt_Ques = findViewById(R.id.txt_Ques);

        r_SA = findViewById(R.id.r_A);
        r_SB = findViewById(R.id.r_B);
        r_SC = findViewById(R.id.r_C);
        r_SD = findViewById(R.id.r_D);
        radioButtons.add(r_SA);
        radioButtons.add(r_SB);
        radioButtons.add(r_SC);
        radioButtons.add(r_SD);

        ch_A = findViewById(R.id.ch_A);
        ch_B = findViewById(R.id.ch_B);
        ch_C = findViewById(R.id.ch_C);
        ch_D = findViewById(R.id.ch_D);
        checkBoxes.add(ch_A);
        checkBoxes.add(ch_B);
        checkBoxes.add(ch_C);
        checkBoxes.add(ch_D);

        btn_prv = findViewById(R.id.btn_prv);
        btn_next = findViewById(R.id.btn_next);
        btn_sub = findViewById(R.id.btn_ques_sub);

        radioGroup = findViewById(R.id.r_linear);
        ch_linear = findViewById(R.id.ch_linear);
        ch_linear.setVisibility(View.GONE);


        btn_prv.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_sub.setOnClickListener(this);

        r_SA.setTag("A");
        r_SB.setTag("B");
        r_SC.setTag("C");
        r_SD.setTag("D");


        ch_A.setTag("A");
        ch_B.setTag("B");
        ch_C.setTag("C");
        ch_D.setTag("D");

//        r_SA.setOnCheckedChangeListener(this);
//        r_SB.setOnCheckedChangeListener(this);
//        r_SC.setOnCheckedChangeListener(this);
//        r_SD.setOnCheckedChangeListener(this);
//        ch_A.setOnCheckedChangeListener(this);
//        ch_B.setOnCheckedChangeListener(this);
//        ch_C.setOnCheckedChangeListener(this);
//        ch_D.setOnCheckedChangeListener(this);


        r_SA.setOnClickListener(this);
        r_SB.setOnClickListener(this);
        r_SC.setOnClickListener(this);
        r_SD.setOnClickListener(this);

        ch_A.setOnClickListener(this);
        ch_B.setOnClickListener(this);
        ch_C.setOnClickListener(this);
        ch_D.setOnClickListener(this);
    }

    private void GetQues(Long examroomid) {
        Call<List<ResultQues>> call = examService.StartExam(examroomid.toString());
        call.enqueue(new Callback<List<ResultQues>>() {
            @Override
            public void onResponse(Call<List<ResultQues>> call, Response<List<ResultQues>> response) {
                Log.d("成功", "onResponse: " + response.body().size());
                try {
                    quesEntityList = response.body();
                    resultTemp.setEpuid(quesEntityList.get(0).getEpu_id());
                    ShowQues(0);
                } catch (Exception ex) {
                    Toast.makeText(AnswerActivity.this, "信息请求失败！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResultQues>> call, Throwable t) {
                Log.d("失败", "onResponse: ");
                Toast.makeText(AnswerActivity.this, "信息请求失败！", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        GetQues(examroomid);
    }


    private void ShowQues(int index) {
        ResultQues resultQues = quesEntityList.get(index);
        txt_Ques.setText("\u3000\u3000" + resultQues.getQues().getStem());
        if (resultQues.getQues().getQ_Type() != 2) {
            radioGroup.setVisibility(View.VISIBLE);
            ch_linear.setVisibility(View.GONE);
            SetChoose(1, resultQues);
        } else {
            radioGroup.setVisibility(View.GONE);
            ch_linear.setVisibility(View.VISIBLE);
            SetChoose(2, resultQues);
        }

    }

    public void SetChoose(int type, ResultQues resultQues) {
        if (type != 2) {
            for (int i = 0; i < radioButtons.size(); i++) {
                radioButtons.get(i).setVisibility(View.GONE);
//                radioButtons.get(i).setChecked(false);
            }
            radioGroup.check(0);
            int count = 0;

            for (Selection selection : resultQues.getQues().getEmp()) {
                radioButtons.get(count).setVisibility(View.VISIBLE);
                radioButtons.get(count).setText(selection.getS_Key() + "." + selection.getS_Name());
                if (resultQues.getUesr_Selection() != null && resultQues.getUesr_Selection().contains(selection.getS_Key())) {
                    radioButtons.get(count).setChecked(true);
                }
                count++;
            }

        } else {
            for (int i = 0; i < checkBoxes.size(); i++) {
                checkBoxes.get(i).setVisibility(View.GONE);
                checkBoxes.get(i).setChecked(false);
            }
            int count = 0;
            for (Selection selection : resultQues.getQues().getEmp()) {
                checkBoxes.get(count).setVisibility(View.VISIBLE);
                checkBoxes.get(count).setText(selection.getS_Key() + "." + selection.getS_Name());
                if (resultQues.getUesr_Selection() != null && resultQues.getUesr_Selection().contains(selection.getS_Key())) {
                    checkBoxes.get(count).setChecked(true);
                }
                count++;
            }

        }

    }

    public void SetAnswe() {
        if (quesEntityList.get(quesindex).getQues().getQ_Type() != 2) {
            for (RadioButton r_temp : radioButtons) {
                if (r_temp.isChecked()) {
                    quesEntityList.get(quesindex).setUesr_Selection("," + r_temp.getTag());
                }
            }
        } else {
            String keys = "";
            for (CheckBox c_temp : checkBoxes) {
                if (c_temp.isChecked()) {
                    keys += "," + c_temp.getTag();
                }
            }

            quesEntityList.get(quesindex).setUesr_Selection(keys);
        }


        Call<String> call = examService.SaveSingle(quesEntityList.get(quesindex).getEqu_Id() + ":" + quesEntityList.get(quesindex).getUesr_Selection());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null)
                    Log.d("答案提交成功", response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("答案提交失败", "");
            }
        });
//        Log.d(quesEntityList.get(quesindex).getEqu_Id() + "答题结果", quesEntityList.get(quesindex).getUesr_Selection() == null ? "" : quesEntityList.get(quesindex).getUesr_Selection());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_prv: {
                quesindex = quesindex - 1;
                if (quesindex < 0) {
                    quesindex = 0;
                } else {
                    ShowQues(quesindex);
                }
                break;
            }
            case R.id.btn_next: {
                quesindex = quesindex + 1;
                if (quesindex >= quesEntityList.size()) {
                    quesindex = quesEntityList.size() - 1;
                } else {
                    ShowQues(quesindex);
                }
                break;
            }
            case R.id.btn_ques_sub: {
                resultTemp.getResultarray().clear();
                for (ResultQues re : quesEntityList) {
                    resultTemp.getResultarray().add(re.getEqu_Id() + ":" + re.getUesr_Selection());
                }
                Gson gson = new Gson();
                String json = gson.toJson(resultTemp);
                Log.d("json:", "onClick: " + json);

                Call<String> call = examService.SaveALL(json);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body() != null) {
                            Log.d("所有答案提交成功", response.body());
                            Toast.makeText(AnswerActivity.this, "您的得分：" + response.body() + "分", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("所有答案提交失败", "");
                        finish();
                    }
                });


                break;
            }
            case R.id.r_A: {
                SetAnswe();
                break;
            }
            case R.id.r_B: {
                SetAnswe();
                break;
            }
            case R.id.r_C: {
                SetAnswe();
                break;
            }
            case R.id.r_D: {
                SetAnswe();
                break;
            }
            case R.id.ch_A: {
                SetAnswe();
                break;
            }
            case R.id.ch_B: {
                SetAnswe();
                break;
            }
            case R.id.ch_C: {
                SetAnswe();
                break;
            }
            case R.id.ch_D: {
                SetAnswe();
                break;
            }
        }
    }
}
