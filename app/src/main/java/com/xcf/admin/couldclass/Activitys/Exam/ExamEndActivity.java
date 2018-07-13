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

public class ExamEndActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    Button button2;
    ListView listView;

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
        //context可能有错误
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.activity_exam_startitem,
                new String[]{"text1", "text2"},
                new int[]{R.id.exam_startitem1, R.id.exam_startitem2});
        listView.setAdapter(adapter);
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
