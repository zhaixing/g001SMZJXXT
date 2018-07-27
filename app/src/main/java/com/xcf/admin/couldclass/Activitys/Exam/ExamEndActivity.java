package com.xcf.admin.couldclass.Activitys.Exam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.xcf.admin.couldclass.Dao.ExamServiceyhs;
import com.xcf.admin.couldclass.Entity.examroom.Examend;
import com.xcf.admin.couldclass.Entity.result.ResultQues;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MessageContext;
import com.xcf.admin.couldclass.MyContext.MyApp;
import com.xcf.admin.couldclass.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamEndActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    Button button2;
    ListView listView;
    TextView title;
    TextView enddate;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.falselist.clear();
        setContentView(R.layout.activity_exam_end);
        setListener();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exam_end_button1: {
                Intent intent = new Intent(this, ExamAnswerActivity.class);
                intent.putExtra("goit", "no1");
                this.finish();
                startActivity(intent);
                break;
            }
            case R.id.exam_end_button2: {
                Intent intent = new Intent(this, ExamAnswerActivity.class);
                intent.putExtra("goit", "no2");
                this.finish();
                startActivity(intent);
                break;
            }
        }

    }
    public void setListener() {
        listView = findViewById(R.id.exam_end_list);
        button1 = findViewById(R.id.exam_end_button1);//全部解析
        button2 = findViewById(R.id.exam_end_button2);//错题解析
        title = findViewById(R.id.exam_end_title);
        enddate = findViewById(R.id.exam_end_date);
        score = findViewById(R.id.exam_end_score);
        title.setText(MyApp.papername);
        final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        //context可能有错误
        ExamServiceyhs u = HttpHelper.getInstance().getRetrofitStr().create(ExamServiceyhs.class);
        SharedPreferences sp = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", null);
        System.out.println("userid=" + userid);
        System.out.println("roomid=" + MyApp.roomid);
        Call<Examend> call = u.examend(userid, MyApp.roomid);
        call.enqueue(new Callback<Examend>() {
            @Override
            public void onResponse(Call<Examend> call, Response<Examend> response) {
                enddate.setText("交卷时间：" + format.format(response.body().getDate()));
                System.out.println("交卷时间" + response.body().getDate());
                List<Map<String, String>> data = new ArrayList<Map<String, String>>();
                for (int i = 0; i <= MyApp.appquesmain.getS().size(); i++) {
                    Map<String, String> map = new HashMap<String, String>();
                    if (i == 0) {
                        map.put("text1", "选择题");
                        map.put("text2", "");
                    } else {
                        map.put("text1", i + "." + MyApp.appquesmain.getS().get(i - 1).getQuesyhsname());
                        for (ResultQues resultQues : response.body().getList()
                                ) {
                            if (resultQues.getQues().getQ_Id().equals(MyApp.appquesmain.getS().get(i - 1).getQuesyhsid())) {
                                map.put("text2", resultQues.getReal_Score() + "分");
                                Log.e("sddfd ", "onResponse: 测试成功");
                                if (resultQues.getReal_Score() == 0) {
                                    MyApp.falselist.add(i);
                                }
                                break;
                            }
                        }
                    }
                    data.add(map);
                }
                for (int i = 0; i <= MyApp.appquesmain.getD().size(); i++) {
                    Map<String, String> map = new HashMap<String, String>();
                    if (i == 0) {
                        map.put("text1", "多选题");
                        map.put("text2", "");
                    } else {
                        map.put("text1", i + "." + MyApp.appquesmain.getD().get(i - 1).getQuesyhsname());
                        for (ResultQues resultQues : response.body().getList()
                                ) {
                            if (resultQues.getQues().getQ_Id().equals(MyApp.appquesmain.getD().get(i - 1).getQuesyhsid())) {
                                map.put("text2", resultQues.getReal_Score() + "分");
                                if (resultQues.getReal_Score() == 0) {
                                    MyApp.falselist.add(MyApp.appquesmain.getS().size() + i);
                                }
                                break;
                            }
                        }
                    }
                    data.add(map);
                }
                for (int i = 0; i <= MyApp.appquesmain.getP().size(); i++) {
                    Map<String, String> map = new HashMap<String, String>();
                    if (i == 0) {
                        map.put("text1", "判断题");
                        map.put("text2", "");
                    } else {
                        map.put("text1", i + "." + MyApp.appquesmain.getP().get(i - 1).getQuesyhsname());
                        for (ResultQues resultQues : response.body().getList()
                                ) {
                            if (resultQues.getQues().getQ_Id().equals(MyApp.appquesmain.getP().get(i - 1).getQuesyhsid())) {
                                map.put("text2", resultQues.getReal_Score() + "分");
                                if (resultQues.getReal_Score() == 0) {
                                    MyApp.falselist.add(MyApp.appquesmain.getS().size() + MyApp.appquesmain.getD().size() + i);
                                }
                                break;
                            }
                        }
                    }
                    data.add(map);
                }
                int realscore = 0;
                for (ResultQues resultQues : response.body().getList()
                        ) {
                    realscore += resultQues.getReal_Score();
                }
                score.setText(realscore + "分");
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.activity_exam_startitem,
                        new String[]{"text1", "text2"},
                        new int[]{R.id.exam_startitem1, R.id.exam_startitem2});
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Examend> call, Throwable t) {
                Toast.makeText(ExamEndActivity.this, MessageContext.INTNET_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0&&position!=(MyApp.appquesmain.getS().size()+1)&&position!=(MyApp.appquesmain.getS().size()+MyApp.appquesmain.getD().size()+2)){
                    Intent it = new Intent(ExamEndActivity.this,ExamAnswerActivity.class);
                    if (position<=MyApp.appquesmain.getS().size()){
                        it.putExtra("position1",String.valueOf(position));
                        it.putExtra("goit","yes");
                    }else if (position<=(MyApp.appquesmain.getS().size()+MyApp.appquesmain.getD().size()+1)){
                        it.putExtra("position1",String.valueOf(position-1));
                        it.putExtra("goit","yes");
                    }else {
                        it.putExtra("position1",String.valueOf(position-2));
                        it.putExtra("goit","yes");
                    }
                    startActivity(it);
                }


            }
        });
    }
}
