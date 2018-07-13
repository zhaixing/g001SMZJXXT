package com.xcf.admin.couldclass.Activitys.Exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.xcf.admin.couldclass.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamStartActivity extends AppCompatActivity implements View.OnClickListener {


    ListView listView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_start);
        setListener();
    }


    public void setListener() {
        listView = findViewById(R.id.exam_start_list);
        button = findViewById(R.id.exam_start_button);
        //context可能有错误
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
        map1.put("text2", "30分");
        data.add(map1);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("text1", "多项选择题");
        map2.put("text2", "40分");
        data.add(map2);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("text1", "判断题");
        map3.put("text2", "30分");
        data.add(map3);
        return data;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exam_start_button: {
            }
        }

    }
}
