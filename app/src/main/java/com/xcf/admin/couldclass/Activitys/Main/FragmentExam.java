package com.xcf.admin.couldclass.Activitys.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.xcf.admin.couldclass.Activitys.Exam.ExamStartActivity;
import com.xcf.admin.couldclass.Adapter.ListViewAdapter;
import com.xcf.admin.couldclass.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        provinceSpinner = view.findViewById(R.id.spin_province);
        citySpinner = view.findViewById(R.id.spin_city);
        countySpinner = view.findViewById(R.id.spin_county);


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

        listView = rootView.findViewById(R.id.listview);
        List<Map<String, Object>> list=getData();
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        setlistener(list);

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

    public void setlistener(final List list) {
        listView = rootView.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //我们需要的内容，跳转页面或显示详细信息
                //System.out.println(adapter.getItem(position));
                new AlertDialog.Builder(getActivity())
                        .setMessage("开始考试！")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getActivity(), ExamStartActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("否", null)
                        .show();
            }
        });
    }
}
