package com.xcf.admin.couldclass.Activitys.Main;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
            map.put("image", R.drawable.head1);
            map.put("title", "这是一个标题"+i);
            map.put("info", "这是一个详细信息" + i);
            list.add(map);
        }
        return list;
    }
}
