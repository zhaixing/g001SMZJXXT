package com.xcf.admin.couldclass.Activitys.Exam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.xcf.admin.couldclass.Dao.ExamServiceyhs;
import com.xcf.admin.couldclass.Entity.CardBean;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MessageContext;
import com.xcf.admin.couldclass.R;
import com.xcf.admin.couldclass.SysApplication.SysApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamAddActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText et_add_exam_name;
    public TextView tvAddExamType = null;
    private OptionsPickerView pvCustomOptions;
    private ArrayList<CardBean> cardItem = new ArrayList<>();
    private TimePickerView pvTimeStart;// 开始时间
    private TimePickerView pvTimeEnd;// 结束时间
    private TextView tv_add_exam_end;// 结束
    private TextView tv_add_exam_start;//开始
    private TextView button;
    private TextView snum;
    private TextView dnum;
    private TextView pnum;
    Activity activity;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        SysApplication.getInstance().addActivity(this);
        activity = this;

        button = findViewById(R.id.submit_tv);
        button.setOnClickListener(this);
        snum = findViewById(R.id.add_exam_danxuan);
        dnum = findViewById(R.id.add_exam_duoxuan);
        pnum = findViewById(R.id.add_exam_panduan);
        et_add_exam_name = findViewById(R.id.add_exam_name);
        et_add_exam_name.setOnClickListener(this);
        snum.setOnClickListener(this);
        dnum.setOnClickListener(this);
        pnum.setOnClickListener(this);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dateNow=new Date(System.currentTimeMillis());
        et_add_exam_name.setText("练习"+simpleDateFormat.format(dateNow));

        getCardData();
        initCustomOptionPicker();

        tvAddExamType = findViewById(R.id.add_exam_type);
        tvAddExamType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvCustomOptions.show();
            }
        });

        // 时间选择
        initTimePicker();
        tv_add_exam_end = findViewById(R.id.add_exam_end);
        tv_add_exam_start = findViewById(R.id.add_exam_start);
        Date now = new Date();
        Long future = now.getTime() + 86400000 * 7;
        Date fu = new Date(future);
        tv_add_exam_start.setText(format.format(now));
        tv_add_exam_start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                pvTimeStart.show(v);
            }
        });
        tv_add_exam_end.setText(format.format(fu));
        tv_add_exam_end.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                pvTimeEnd.show(v);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1).getPickerViewText();
                tvAddExamType.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = v.findViewById(R.id.tv_finish);
                        //final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                        ImageView ivCancel = v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });

                        /*tvAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getCardData();
                                pvCustomOptions.setPicker(cardItem);
                            }
                        });*/

                    }
                })
                .isDialog(true)
                .build();

        pvCustomOptions.setPicker(cardItem);//添加数据


    }

    private void getCardData() {
        cardItem.add(new CardBean(0, "运输"));
        cardItem.add(new CardBean(1, "人身"));
        cardItem.add(new CardBean(2, "管理"));
       /* for (int i = 0; i < 5; i++) {
            cardItem.add(new CardBean(i, "No.ABC12345 " + i));
        }

        for (int i = 0; i < cardItem.size(); i++) {
            if (cardItem.get(i).getCardNo().length() > 20) {
                String str_item = cardItem.get(i).getCardNo().substring(0, 6) + "...";
                cardItem.get(i).setCardNo(str_item);
            }
        }*/
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private void initTimePicker() {//Dialog 模式下，在底部弹出

        pvTimeStart = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv_add_exam_start.setText(format.format(date));
            }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
            @Override
            public void onTimeSelectChanged(Date date) {
                Log.i("pvTimeStart","onTimeSelectChanged");
            }
        }).setType(new boolean[]{true,true,true,true,true,true}).isDialog(true).build();

       Dialog mDialogStart = pvTimeStart.getDialog();
       if (mDialogStart!=null)
       {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM
            );

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTimeStart.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialogStart.getWindow();
            if (dialogWindow!=null){
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);// 修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//底部显示
            }
       }


        pvTimeEnd = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv_add_exam_end.setText(format.format(date));
            }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
            @Override
            public void onTimeSelectChanged(Date date) {
                Log.i("pvTimeEnd","onTimeSelectChanged");
            }
        }).setType(new boolean[]{true,true,true,true,true,true}).isDialog(true).build();

        Dialog mDialogEnd = pvTimeEnd.getDialog();
        if (mDialogEnd!=null)
        {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM
            );

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTimeEnd.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialogEnd.getWindow();
            if (dialogWindow!=null){
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);// 修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//底部显示
            }
        }
    }

    public boolean isnull() {
        boolean bool = true;
        if (et_add_exam_name.getText().length() == 0) {
            Toast.makeText(this, "名称不能为空", Toast.LENGTH_SHORT).show();
            bool = false;
        }
        if (snum.getText().length() == 0 || dnum.getText().length() == 0 || pnum.getText().length() == 0) {
            Toast.makeText(this, "请输入有效的题目个数", Toast.LENGTH_SHORT).show();
            bool = false;
        } else {
            if (Integer.parseInt(snum.getText().toString()) + Integer.parseInt(dnum.getText().toString()) + Integer.parseInt(pnum.getText().toString()) == 0) {
                Toast.makeText(this, "至少含有1道题", Toast.LENGTH_SHORT).show();
                bool = false;
            }
        }
        return bool;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_tv: {
                if (isnull()) {
                    SharedPreferences sp = getSharedPreferences("loginToken", Context.MODE_PRIVATE);
                    String token = sp.getString("token", null);
                    ExamServiceyhs examService = HttpHelper.getInstance().getRetrofitStr().create(ExamServiceyhs.class);
                    Call<String> call = examService.roomadd(token, et_add_exam_name.getText().toString(),
                            tvAddExamType.getText().toString(), tv_add_exam_start.getText().toString(),
                            tv_add_exam_end.getText().toString(), snum.getText().toString(), dnum.getText().toString(),
                            pnum.getText().toString());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            setResult(1);
                            activity.finish();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(activity, MessageContext.INTNET_ERROR, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            }
            case R.id.add_exam_name: {
                et_add_exam_name.setText("");
                break;
            }
            case R.id.add_exam_danxuan: {
                snum.setText("");
                break;
            }
            case R.id.add_exam_duoxuan: {
                dnum.setText("");
                break;
            }
            case R.id.add_exam_panduan: {
                pnum.setText("");
                break;
            }
        }
    }
}
