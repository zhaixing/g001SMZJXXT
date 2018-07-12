package com.xcf.admin.couldclass.Activitys.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

import com.xcf.admin.couldclass.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentOne_yhs extends Fragment {

    private TabHost tabHost;
    View view;
    ListView list_week;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_rank_top, container, false);
        super.onCreate(savedInstanceState);
        //getActivity().setContentView(R.layout.activity_rank_top);
        tabHost = view.findViewById(R.id.tabhost);
        tabHost.setup();
        list_week = view.findViewById(R.id.list_week);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,getData());
        //inflater=LayoutInflater.from(getContext());

        SimpleAdapter adapter = new SimpleAdapter(getContext(), getData(), R.layout.activity_rank_item,
                new String[]{"rank", "image", "text"},
                new int[]{R.id.list_item_rank, R.id.list_item_image, R.id.list_item_text});
        setlistener(adapter);
        list_week.setAdapter(adapter);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("周榜", null).setContent(R.id.list_week));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("月榜", null).setContent(R.id.list_month));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("季榜", null).setContent(R.id.list_quarter));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("年榜", null).setContent(R.id.list_year));
        return view;
    }


    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> list = new ArrayList<>();
        list.add(R.drawable.head1);
        list.add(R.drawable.head2);
        list.add(R.drawable.head3);
        list.add(R.drawable.head4);
        list.add(R.drawable.head5);
        for (int i = 0; i < 5; i++) {
            map.put("rank", i);
            map.put("image", list.get(i));
            map.put("text", "王大陆");
            data.add(map);
        }

        return data;
    }

    //item点击事件
    public void setlistener(final SimpleAdapter adapter) {
        list_week = view.findViewById(R.id.list_week);
        list_week.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //我们需要的内容，跳转页面或显示详细信息
                System.out.println(adapter.getItem(position));
            }
        });
    }
}
