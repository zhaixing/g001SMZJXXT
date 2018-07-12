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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.xcf.admin.couldclass.R;
import com.nineoldandroids.*;
public class FragmentExam extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private View rootView;
    private View view;

    private Spinner provinceSpinner = null;  //省级（省、直辖市）
    private Spinner citySpinner = null;     //地级市
    private Spinner countySpinner = null;    //县级（区、县、县级市）

    private int tab;
    private boolean tag = false;
    private View itemView=null;
    private ListView lv;
    private String table,content;
    public static String field,value;
    private TextView tv1, tv2, info;
    private SimpleCursorAdapter adapter;
    private Cursor cursor;
    private FloatingActionButton fabtest,fabprac;

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

        lv = rootView.findViewById(R.id.lv_que);

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

        return rootView;
    }

}
