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
import com.xcf.admin.couldclass.Activitys.Exam.ExamEndActivity;
import com.xcf.admin.couldclass.Activitys.Exam.ExamStartActivity;
import com.xcf.admin.couldclass.Adapter.ListViewAdapter;
import com.xcf.admin.couldclass.Dao.ExamServiceyhs;
import com.xcf.admin.couldclass.Entity.examroom.Appques;
import com.xcf.admin.couldclass.Entity.examroom.ListExamRoom;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
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

public class FragmentStudy extends Fragment {
    private View rootView;

    private Spinner spin_major;
    private Spinner spin_complete;
    private Spinner spin_time;
    private String major = "";
    private String complete = "";
    private String timestate = "";

    private ListView listView;
    SharedPreferences sp;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);  // 这是关键的一句
    }

    public void getData() {
        sp = getContext().getSharedPreferences("loginToken", Context.MODE_PRIVATE);
        //获取Token;
        listView = rootView.findViewById(R.id.listview);
        String token = sp.getString("token", null);
        final List<Map<String, Object>> list = new ArrayList<>();
        ExamServiceyhs u = HttpHelper.getInstance().getRetrofitStr().create(ExamServiceyhs.class);
        Call<ListExamRoom> call = u.GetExamRoom1(token, major, complete, timestate, "1");
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
                        if (examlist.getStartdate() != null && examlist.getEnddate() != null) {
                            hashMap.put("date", examlist.getStartdate() + "~" + examlist.getEnddate());
                        } else {
                            hashMap.put("date", "全年开放");
                        }
                        hashMap.put("time", examlist.getStarttime() + "~" + examlist.getEndtime());
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
            }

            @Override
            public void onFailure(Call<ListExamRoom> call, Throwable t) {
                System.out.println("失败");
            }
        });
    }

    public void setlistener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                //我们需要的内容，跳转页面或显示详细信息
                //System.out.println(adapter.getItem(position));
                final Map<String, Object> mapall = (Map<String, Object>) parent.getItemAtPosition(position);
                MyApp.papername = mapall.get("name").toString();
                MyApp.roomid = mapall.get("roomid").toString();
                final ExamServiceyhs u = HttpHelper.getInstance().getRetrofitStr().create(ExamServiceyhs.class);
                Call<Appques> call = u.Getpaper(sp.getString("userid", null), MyApp.roomid);
                call.enqueue(new Callback<Appques>() {
                    @Override
                    public void onResponse(Call<Appques> call, Response<Appques> response) {
                        Log.e("success", "onResponse: " + response.body());
                        MyApp.appquesmain = response.body();
                        MyApp.questionsum = MyApp.appquesmain.getS().size() + MyApp.appquesmain.getD().size() + MyApp.appquesmain.getP().size();
                        //获取考题
                        if (mapall.get("complete").equals("已完成")) {
                            new AlertDialog.Builder(getActivity())
                                    .setMessage("请选择")
                                    .setPositiveButton("重新练习", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //清除数据库
                                            ExamServiceyhs examServiceyhs = HttpHelper.getInstance().getRetrofitStr().create(ExamServiceyhs.class);
                                            Call<String> call1 = examServiceyhs.clearpaper(sp.getString("userid", null), MyApp.roomid);
                                            call1.enqueue(new Callback() {
                                                @Override
                                                public void onResponse(Call call, Response response) {
                                                    Call<Appques> call2 = u.Getpaper(sp.getString("userid", null), MyApp.roomid);
                                                    call2.enqueue(new Callback<Appques>() {
                                                        @Override
                                                        public void onResponse(Call<Appques> call, Response<Appques> response) {
                                                            MyApp.appquesmain = response.body();
                                                            Intent intent = new Intent(getActivity(), ExamStartActivity.class);
                                                            intent.putExtra("type", mapall.get("type").toString());
                                                            startActivity(intent);
                                                        }

                                                        @Override
                                                        public void onFailure(Call<Appques> call, Throwable t) {

                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onFailure(Call call, Throwable t) {

                                                }
                                            });

                                        }
                                    })
                                    .setNegativeButton("查看成绩", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent(getActivity(), ExamEndActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();
                        } else {
                            new AlertDialog.Builder(getActivity())
                                    .setMessage("开始练习！")
                                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(i + 1);
                                            Intent intent = new Intent(getActivity(), ExamStartActivity.class);
                                            intent.putExtra("type", map.get("type").toString());
                                            startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("否", null)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Appques> call, Throwable t) {
                        Log.e("fail", "onFailure: ");
                    }
                });
            }
        });

        spin_major = rootView.findViewById(R.id.exam_spin_major);
        spin_complete = rootView.findViewById(R.id.exam_spin_complete);
        spin_time = rootView.findViewById(R.id.exam_spin_time);
        setspinlisten(spin_major);
        setspinlisten(spin_complete);
        setspinlisten(spin_time);
    }

    public void setspinlisten(Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                major = spin_major.getSelectedItem().toString();
                complete = spin_complete.getSelectedItem().toString();
                timestate = spin_time.getSelectedItem().toString();
                getData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_exam, container, false);
        }
        getData();
        setlistener();
        return rootView;
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
                intent.putExtra("erpapertype", "1");
                startActivityForResult(intent, 1);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        // operation succeeded. 默认值是-1
        if (requestCode == 1 && resultCode == 1) {
            getData();
        }
    }
}
