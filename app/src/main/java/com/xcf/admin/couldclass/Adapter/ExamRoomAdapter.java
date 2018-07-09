package com.xcf.admin.couldclass.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.xcf.admin.couldclass.Entity.examroom.ExamRoom;
import com.xcf.admin.couldclass.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */

public class ExamRoomAdapter extends ArrayAdapter<ExamRoom> {

    private static DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat HH_MM_SS = new SimpleDateFormat("hh:mm:ss");
    int resourceId;

    public ExamRoomAdapter(@NonNull Context context, int resourceId, List<ExamRoom> objs) {
        super(context, resourceId, objs);
        this.resourceId = resourceId;
    }

    public static Long GetFullTime(String time) {
        Calendar calendar = Calendar.getInstance();
        String[] times = time.split(":");

        calendar.set(Calendar.MINUTE, Integer.valueOf(times[1]));
        String secondstr = times[2];
        if (secondstr.contains("上午")) {
            secondstr = secondstr.replace("上午", "").trim();
            calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(times[0]));
        } else {
            secondstr = secondstr.replace("下午", "").trim();
            calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(times[0]) + 12);
        }
        calendar.set(Calendar.SECOND, Integer.valueOf(secondstr));
        return calendar.getTimeInMillis();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ExamRoom examRoom = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.examroomid = view.findViewById(R.id.eid);
            viewHolder.examname = view.findViewById(R.id.ename);
            viewHolder.examstatus = view.findViewById(R.id.estatus);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.examroomid.setText(examRoom.getEr_id().toString());
        viewHolder.examname.setText(examRoom.getEr_Name());
        viewHolder.examstatus.setText("考场开放");

        Date Sdate = null;
        Date Edate = null;

        try {
            if (examRoom.getEr_Start_Date() != null)
                Sdate = YYYY_MM_DD.parse(examRoom.getEr_Start_Date());
            if (examRoom.getEr_End_Date() != null)
                Edate = YYYY_MM_DD.parse(examRoom.getEr_End_Date());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (examRoom != null) {
            if (Sdate != null) {
                if (Sdate.getTime() < System.currentTimeMillis() && System.currentTimeMillis() < Edate.getTime()) {
                    if (System.currentTimeMillis() > GetFullTime(examRoom.getEr_Start_Time()) && System.currentTimeMillis() < GetFullTime(examRoom.getEr_End_Time())) {
                        viewHolder.examstatus.setText("考场开放");
                    } else {
                        viewHolder.examstatus.setText("考场未放");
                    }
                } else {
                    viewHolder.examstatus.setText("考场未放");
                }
            } else {
                if (System.currentTimeMillis() > GetFullTime(examRoom.getEr_Start_Time()) && System.currentTimeMillis() < GetFullTime(examRoom.getEr_End_Time())) {
                    viewHolder.examstatus.setText("考场开放");
                } else {
                    viewHolder.examstatus.setText("考场未放");
                }
            }
        }

        return view;
    }

    class ViewHolder {
        TextView examroomid;
        TextView examname;
        TextView examstatus;
    }
}
