package com.xcf.admin.couldclass.Activitys.Main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xcf.admin.couldclass.Activitys.Exam.ExamAddActivity;
import com.xcf.admin.couldclass.Adapter.ListViewAdapter;
import com.xcf.admin.couldclass.Entity.examroom.ExamRoom;
import com.xcf.admin.couldclass.Entity.user.User;
import com.xcf.admin.couldclass.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.vov.vitamio.utils.Log;

public class FragmentExam extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private View rootView;
    private View view;

    private Spinner provinceSpinner = null;  //省级（省、直辖市）
    private Spinner citySpinner = null;     //地级市
    private Spinner countySpinner = null;    //县级（区、县、县级市）

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_exam, container, false);
        }

        // fragment获取控件的方法
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_exam, null);

        provinceSpinner = (Spinner)view.findViewById(R.id.spin_province);
        citySpinner = (Spinner)view.findViewById(R.id.spin_city);
        countySpinner = (Spinner)view.findViewById(R.id.spin_county);


//        Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int pos, long id) {
//
//                String[] languages = getResources().getStringArray(R.array.languages);
//                Toast.makeText(getActivity(), "你点击的是:"+languages[pos], 2000).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Another interface callback
//            }
//        });

        listView = (ListView)rootView.findViewById(R.id.listview);
        List<Map<String, Object>> list=getData();
        listView.setAdapter(new ListViewAdapter(getActivity(), list));

        return rootView;
    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 30; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            if (i%2 == 0)
            {
                map.put("image", R.drawable.ic_action_star_5);
                map.put("title", "2018年北京铁路局车站值班员统一考试"+i);
                map.put("info", "试题数量：" + i);
            }
            else if (i%3==0)
            {
                map.put("image", R.drawable.ic_action_star_10);
                map.put("title", "2018年阳泉站车站值班员统一考试"+i);
                map.put("info", "试题数量：" + i);
            }
            else
            {
                map.put("image", R.drawable.ic_action_star_0);
                map.put("title", "2018年统一考试"+i);
                map.put("info", "试题数量：" + i);
            }
            list.add(map);
        }
        return list;
    }

    // 菜单点击
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_exam_menu, menu);//加载menu文件到布局
//        Intent intent = new Intent(getActivity(), ExamAddActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);  // 这是关键的一句
    }

    /**
     *菜单的点击事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.save_select_ter:
                //Toast.makeText(getActivity(), "你点击了 添加！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ExamAddActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}
