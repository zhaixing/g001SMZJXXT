package com.xcf.admin.couldclass.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcf.admin.couldclass.R;

import java.util.List;
import java.util.Map;

public class RankAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public RankAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public final class Zujian {
        public TextView rank;
        public ImageView imageView;
        public TextView name;
        public TextView score;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RankAdapter.Zujian zujian = null;
        if (view == null) {
            zujian = new RankAdapter.Zujian();
            //获得组件，实例化组件
            view = layoutInflater.inflate(R.layout.activity_rank_item, null);
            zujian.rank = view.findViewById(R.id.list_item_rank);
            zujian.imageView = view.findViewById(R.id.list_item_image);
            zujian.name = view.findViewById(R.id.list_item_name);
            zujian.score = view.findViewById(R.id.list_item_score);
            view.setTag(zujian);
        } else {
            zujian = (RankAdapter.Zujian) view.getTag();
        }
        //绑定数据
        zujian.rank.setText(data.get(i).get("rank").toString());
        zujian.imageView.setImageBitmap((Bitmap) data.get(i).get("image"));
        zujian.name.setText((String) data.get(i).get("name"));
        zujian.score.setText((String) data.get(i).get("score"));
        return view;
    }
}
