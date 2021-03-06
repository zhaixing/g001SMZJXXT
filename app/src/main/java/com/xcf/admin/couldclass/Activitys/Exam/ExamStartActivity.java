package com.xcf.admin.couldclass.Activitys.Exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.xcf.admin.couldclass.MyContext.MyApp;
import com.xcf.admin.couldclass.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExamStartActivity extends AppCompatActivity implements View.OnClickListener {


    ListView listView;
    Button button;
    TextView title;
    TextView title2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_start);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setListener();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setListener() {
        listView = findViewById(R.id.exam_start_list);
        button = findViewById(R.id.exam_start_button);
        title = findViewById(R.id.exam_start_title);
        title2 = findViewById(R.id.exam_start_title2);
        //context可能有错误
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        String nandu = null;
        if (type.equals("0")) {
            nandu = "易";
        } else if (type.equals("1")) {
            nandu = "中";
        } else if (type.equals("2")) {
            nandu = "难";
        }
        title2.setText("试卷难度:" + nandu + ",   满分" + MyApp.questionsum + "分");
        title.setText(MyApp.papername);
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.activity_exam_startitem,
                new String[]{"text1", "text2"},
                new int[]{R.id.exam_startitem1, R.id.exam_startitem2});
        listView.setAdapter(adapter);
        button.setOnClickListener(this);
    }

    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("text1", "共分为3部分");
        map.put("text2", "");
        data.add(map);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("text1", "单项选择题");
        System.out.println(MyApp.appquesmain.getS().size());
        map1.put("text2", MyApp.appquesmain.getS().size() + "分");
        data.add(map1);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("text1", "多项选择题");
        map2.put("text2", MyApp.appquesmain.getD().size() + "分");
        data.add(map2);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("text1", "判断题");
        map3.put("text2", MyApp.appquesmain.getP().size() + "分");
        data.add(map3);
        return data;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exam_start_button: {
                Intent intent = new Intent(this, ExamAnswerActivity.class);
                intent.putExtra("goit", "yes");
                startActivity(intent);
                finish();
            }
        }
    }
}
