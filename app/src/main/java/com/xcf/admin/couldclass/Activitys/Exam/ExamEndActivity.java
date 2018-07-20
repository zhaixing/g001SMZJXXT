package com.xcf.admin.couldclass.Activitys.Exam;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xcf.admin.couldclass.Dao.ExamServiceyhs;
import com.xcf.admin.couldclass.Entity.result.ResultQues;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MyApp;
import com.xcf.admin.couldclass.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        setContentView(R.layout.activity_exam_end);
        setListener();
    }
    @Override
    public void onClick(View view) {

    }

    public void setListener() {
        listView = findViewById(R.id.exam_end_list);
        button1 = findViewById(R.id.exam_end_button1);//全部解析
        button2 = findViewById(R.id.exam_end_button2);//错题解析
        title = findViewById(R.id.exam_end_title);
        enddate = findViewById(R.id.exam_end_date);
        score = findViewById(R.id.exam_end_score);
        title.setText(MyApp.papername);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        enddate.setText("交卷时间：" + format.format(date));
        //context可能有错误
        ExamServiceyhs u = HttpHelper.getInstance().getRetrofitStr().create(ExamServiceyhs.class);
        SharedPreferences sp = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", null);
        Call<List<ResultQues>> call = u.examend(userid, MyApp.roomid);
        call.enqueue(new Callback<List<ResultQues>>() {
            @Override
            public void onResponse(Call<List<ResultQues>> call, Response<List<ResultQues>> response) {
                List<Map<String, String>> data = new ArrayList<Map<String, String>>();
                for (int i = 0; i <= MyApp.appquesmain.getS().size(); i++) {
                    Map<String, String> map = new HashMap<String, String>();
                    if (i == 0) {
                        map.put("text1", "选择题");
                        map.put("text2", "");
                    } else {
                        map.put("text1", "i." + MyApp.appquesmain.getS().get(i - 1).getQuesyhsname());
                        for (ResultQues resultQues : response.body()
                                ) {
                            if (resultQues.getQues().getQ_Id().toString().equals(MyApp.appquesmain.getS().get(i - 1).getQuesyhsid())) {
                                map.put("text2", resultQues.getReal_Score() + "");
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
                        map.put("text1", "i." + MyApp.appquesmain.getD().get(i - 1).getQuesyhsname());
                        for (ResultQues resultQues : response.body()
                                ) {
                            if (resultQues.getQues().getQ_Id().toString().equals(MyApp.appquesmain.getD().get(i - 1).getQuesyhsid())) {
                                map.put("text2", resultQues.getReal_Score() + "");
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
                        map.put("text1", "i." + MyApp.appquesmain.getP().get(i - 1).getQuesyhsname());
                        for (ResultQues resultQues : response.body()
                                ) {
                            if (resultQues.getQues().getQ_Id().toString().equals(MyApp.appquesmain.getP().get(i - 1).getQuesyhsid())) {
                                map.put("text2", resultQues.getReal_Score() + "");
                            }
                        }
                    }
                    data.add(map);
                }
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.activity_exam_startitem,
                        new String[]{"text1", "text2"},
                        new int[]{R.id.exam_startitem1, R.id.exam_startitem2});
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<ResultQues>> call, Throwable t) {

            }
        });


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    public List<Map<String, String>> getData() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("text1", "选择题");
        map.put("text2", "");
        data.add(map);
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("text1", "1. 铁鞋的组成");
        map1.put("text2", "2分");
        data.add(map1);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("text1", "2. 进路信号机的组成");
        map2.put("text2", "3分");
        data.add(map2);
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("text1", "3. 路基的危害类型");
        map3.put("text2", "3分");
        data.add(map3);
        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("text1", "判断题");
        map4.put("text2", "");
        data.add(map4);
        Map<String, String> map5 = new HashMap<String, String>();
        map5.put("text1", "1. 铁鞋的组成");
        map5.put("text2", "2分");
        data.add(map5);
        Map<String, String> map6 = new HashMap<String, String>();
        map6.put("text1", "2. 进路信号机的组成");
        map6.put("text2", "3分");
        data.add(map6);
        Map<String, String> map7 = new HashMap<String, String>();
        map7.put("text1", "3. 路基的危害类型");
        map7.put("text2", "3分");
        data.add(map7);
        return data;
    }
}
