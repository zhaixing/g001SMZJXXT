package com.xcf.admin.couldclass.Activitys.Exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.xcf.admin.couldclass.Activitys.Answer.AnswerActivity;
import com.xcf.admin.couldclass.Adapter.ExamRoomAdapter;
import com.xcf.admin.couldclass.Dao.ExamService;
import com.xcf.admin.couldclass.Entity.examroom.ExamRoom;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamRoomActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    private List<ExamRoom> examRoomList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examroom);
        initexam();
    }

    private void initexam() {

        ExamService examService = HttpHelper.getInstance().getRetrofitStr().create(ExamService.class);
        Call<String> call = examService.GetExamRoom();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body().contains("超时")) {
                        Toast.makeText(ExamRoomActivity.this, "访问超时，请重新登录！", Toast.LENGTH_SHORT).show();
                        ExamRoomActivity.this.finish();
                    } else {
                        JsonParser parser = new JsonParser();
                        JsonArray jsonArray = parser.parse(response.body()).getAsJsonArray();
                        Gson gson = new GsonBuilder()
                                .setDateFormat("yyyy-MM-dd")
                                .create();
                        for (JsonElement user : jsonArray) {
                            //使用GSON，直接转成Bean对象
                            ExamRoom userBean = gson.fromJson(user, ExamRoom.class);
                            examRoomList.add(userBean);
                        }
                        ExamRoomAdapter adapter = new ExamRoomAdapter(ExamRoomActivity.this, R.layout.roomlist, examRoomList);
                        listView = findViewById(R.id.list_view);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(ExamRoomActivity.this);
                    }
                } catch (Exception ex) {
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(this.getClass().toString(), "onResponse: 失败" + t.getMessage() + "_" + t.getCause());
                Log.d(this.getClass().toString(), "onResponse: 失败");
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ExamRoom examRoom = examRoomList.get(position);

        TextView tvstatus = view.findViewById(R.id.estatus);

        if (tvstatus.getText().toString().trim().equals("考场开放")) {
            Intent intent = new Intent(ExamRoomActivity.this, AnswerActivity.class);
            intent.putExtra("examroomid", examRoom.getEr_id());
            startActivity(intent);
        } else {
            Toast.makeText(this, "考场未开放", Toast.LENGTH_SHORT).show();
        }


    }
}
