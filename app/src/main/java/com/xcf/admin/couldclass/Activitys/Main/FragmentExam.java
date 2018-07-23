package com.xcf.admin.couldclass.Activitys.Main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.xcf.admin.couldclass.Activitys.Exam.ExamAddActivity;
import com.xcf.admin.couldclass.Activitys.Exam.ExamStartActivity;
import com.xcf.admin.couldclass.Adapter.ListViewAdapter;
import com.xcf.admin.couldclass.Dao.ExamServiceyhs;
import com.xcf.admin.couldclass.Entity.examroom.ListExamRoom;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentExam extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private View rootView;
    private View view;

    private Spinner provinceSpinner = null;  //省级（省、直辖市）
    private Spinner citySpinner = null;     //地级市
    private Spinner countySpinner = null;    //县级（区、县、县级市）

    private ListView listView;
    SharedPreferences sp;

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
        getData();
        return rootView;
    }

    public void getData() {
        sp = getContext().getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        //获取Token;
        String token = sp.getString("token", null);
        final List<Map<String, Object>> list = new ArrayList<>();
        ExamServiceyhs u = HttpHelper.getInstance().getRetrofitStr().create(ExamServiceyhs.class);
        Call<ListExamRoom> call = u.GetExamRoom1(token);
        call.enqueue(new Callback<ListExamRoom>() {
            @Override
            public void onResponse(Call<ListExamRoom> call, Response<ListExamRoom> response) {
                if (response.body().getBool().equals("success")) {
                    System.out.println("成功");
                    for (ListExamRoom.Examlist examlist : response.body().getList()
                            ) {
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                        HashMap<String, Object> hashMap = new HashMap();
                        //Date curr=new Date(examlist.getStarttime());
                        //System.out.println(format.format(new Date(examlist.getStarttime())));
                        hashMap.put("date", examlist.getStartdate() + "~" + examlist.getEnddate());
                        hashMap.put("time", examlist.getStarttime().replace("上午", "AM").replace("下午", "PM")
                                + "~" + examlist.getEndtime().replace("上午", "AM").replace("下午", "PM"));
                        hashMap.put("name", examlist.getExamroomname());
                        hashMap.put("roomid", examlist.getExamroomid());
                        hashMap.put("type", examlist.getType());
                        switch (examlist.getComplete().toString()) {
                            case "0": {
                                hashMap.put("complete", "未参加");
                                hashMap.put("image", R.drawable.ic_action_star_0);
                                break;
                            }
                            case "1": {
                                hashMap.put("complete", "未完成");
                                hashMap.put("image", R.drawable.ic_action_star_5);
                                break;
                            }
                            case "2": {
                                hashMap.put("complete", "已完成");
                                hashMap.put("image", R.drawable.ic_action_star_10);
                                break;
                            }
                            case "3": {
                                hashMap.put("complete", "缺考");
                                hashMap.put("image", R.drawable.ic_action_star_0);
                                break;
                            }
                            case "4": {
                                hashMap.put("complete", "未参加");
                                hashMap.put("image", R.drawable.ic_action_star_0);
                                break;
                            }
                        }
                        list.add(hashMap);
                    }
                } else {
                    Log.e(getTag(), "onCreateView: 账号在别处登录");
                }
                ListViewAdapter adapter = new ListViewAdapter(getActivity(), list);
                listView.setAdapter(adapter);
                setlistener(list);
            }

            @Override
            public void onFailure(Call<ListExamRoom> call, Throwable t) {
                System.out.println("失败");
            }
        });
    }

    public void setlistener(final List<Map<String, Object>> list) {
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
                                Map<String, Object> map = list.get(i + 1);
                                Intent intent = new Intent(getActivity(), ExamStartActivity.class);
                                intent.putExtra("roomid", map.get("roomid").toString());
                                intent.putExtra("userid", sp.getString("userid", null));
                                intent.putExtra("type", map.get("type").toString());
                                intent.putExtra("name", map.get("name").toString());
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("否", null)
                        .show();
            }
        });
    }

    // 菜单点击
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
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
     * 菜单的点击事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
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
