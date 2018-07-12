package com.xcf.admin.couldclass.Rank;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class ListSimpleAdapter extends SimpleAdapter {
    //item内的按钮点击
    private Context context;
    private LinearLayout linearLayout;
    private List<Map<String, Object>> data;

    public ListSimpleAdapter(Context context,
                             List<Map<String, Object>> data, int resource, String[] from,
                             int[] to) {
        super(context, data, resource, from, to);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
    }

    //返回数据的大小，即listview的行数
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    //根据下标获得某一行的数据
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    //获得指定的Item的下标
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
        /*@Override
        public View getView(final int position, View convertView, ViewGroup parent){
            View v = super.getView(position, convertView, parent);
            Button btn = (Button)v.findViewById(R.id.list_item_btn);
            btn.setOnClickListener(new View.OnClickListener() {

                @SuppressLint("WrongConstant")
                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub

                    Toast.makeText(context, "点击了第"+position+"项", 1000).show();
                }
            });
            return v;
        }*/
}
