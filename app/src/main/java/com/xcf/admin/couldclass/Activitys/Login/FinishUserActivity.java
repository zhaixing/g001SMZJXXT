package com.xcf.admin.couldclass.Activitys.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xcf.admin.couldclass.Dao.OrgService;
import com.xcf.admin.couldclass.Dao.UserService;
import com.xcf.admin.couldclass.Entity.organization.Organization;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinishUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    TextView txt_name;
    Spinner spinner1;
    Spinner spinner2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_user);
        SetListen();
    }

    private void SetListen() {
        txt_name = findViewById(R.id.txt_finish_name);
        spinner1 = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        btn = findViewById(R.id.btn_finish_ok);

        spinner1.setOnItemSelectedListener(this);

        btn.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        OrgService orgService = HttpHelper.getInstance().getRetrofit().create(OrgService.class);
        Call<List<Organization>> call = orgService.GetOrgAll("0");
        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                List<Organization> orglist = response.body();
                ArrayAdapter<Organization> adapter;
                adapter = new ArrayAdapter<Organization>(FinishUserActivity.this, android.R.layout.simple_spinner_item, orglist);
//设置下拉列表的风格
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);//将adapter 添加到spinner中
                spinner1.setVisibility(View.VISIBLE);//设置默认值
            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        OrgService orgService = HttpHelper.getInstance().getRetrofit().create(OrgService.class);
        Call<List<Organization>> call = orgService.GetOrgAll(((Organization) spinner1.getSelectedItem()).getOrg_Id().toString());
        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                List<Organization> orglist = response.body();
                ArrayAdapter<Organization> adapter;
                adapter = new ArrayAdapter<Organization>(FinishUserActivity.this, android.R.layout.simple_spinner_item, orglist);//设置下拉列表的风格

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter);//将adapter 添加到spinner中
                spinner2.setVisibility(View.VISIBLE);//设置默认值
            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (txt_name.getText().toString().equals("")) {
            Toast.makeText(FinishUserActivity.this, "请输入姓名!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (spinner2.getSelectedItem() == null) {

        }

        UserService userService = HttpHelper.getInstance().getRetrofitStr().create(UserService.class);
        Call<String> call = userService.FinishUserApp(txt_name.getText().toString(), ((Organization) spinner2.getSelectedItem()).getOrg_Id().toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
