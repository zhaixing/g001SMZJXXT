package com.xcf.admin.couldclass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcf.admin.couldclass.R;

import java.util.List;
import java.util.Map;

public class ListViewAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public ListViewAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public ImageView image;
        public TextView titlename;
        public TextView date;
        public TextView time;
        public TextView complete;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian=null;
        if(convertView==null){
            zujian=new Zujian();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.item_exam, null);
            zujian.image = convertView.findViewById(R.id.image_exam_head);
            zujian.titlename = convertView.findViewById(R.id.title_exam_name);
            zujian.date = convertView.findViewById(R.id.exam_date);
            //zujian.view=(Button)convertView.findViewById(R.id.view);
            zujian.time = convertView.findViewById(R.id.exam_time);
            zujian.complete = convertView.findViewById(R.id.exam_complete);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.image.setBackgroundResource((Integer)data.get(position).get("image"));
        zujian.titlename.setText((String) data.get(position).get("name"));
        zujian.date.setText((String) data.get(position).get("date"));
        zujian.time.setText((String) data.get(position).get("time"));
        zujian.complete.setText((String) data.get(position).get("complete"));
        return convertView;
    }

}