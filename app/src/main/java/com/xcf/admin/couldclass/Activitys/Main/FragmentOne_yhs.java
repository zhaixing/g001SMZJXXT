package com.xcf.admin.couldclass.Activitys.Main;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;

import com.xcf.admin.couldclass.Adapter.RankAdapter;
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
        tabHost = view.findViewById(R.id.tabhost);
        tabHost.setup();
        list_week = view.findViewById(R.id.list_week);


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,getData());
       /* SimpleAdapter adapter = new SimpleAdapter(getContext(), getData(), R.layout.activity_rank_item,
                new String[]{"rank", "image", "text"},
                new int[]{R.id.list_item_rank, R.id.list_item_image, R.id.list_item_name});*/
        //setlistener();
        RankAdapter adapter = new RankAdapter(getActivity(), getData());
        list_week.setAdapter(adapter);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("周榜", null).setContent(R.id.list_week));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("月榜", null).setContent(R.id.list_month));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("季榜", null).setContent(R.id.list_quarter));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("年榜", null).setContent(R.id.list_year));
        return view;
    }


    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        List<Object> list = new ArrayList<>();

       /* int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB");*/

       /* BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),R.drawable.head1, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;  获取图片大小，判断是否需要压缩*/

        list.add(decodeSampledBitmapFromResource(getResources(), R.drawable.head1, 100, 100));
        list.add(decodeSampledBitmapFromResource(getResources(), R.drawable.head2, 100, 100));
        list.add(decodeSampledBitmapFromResource(getResources(), R.drawable.head3, 100, 100));
        list.add(decodeSampledBitmapFromResource(getResources(), R.drawable.head4, 100, 100));
        list.add(decodeSampledBitmapFromResource(getResources(), R.drawable.head4, 100, 100));
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("rank", i + 1);
            map.put("image", list.get(i));
            map.put("name", "王大陆");
            map.put("score", "80");
            data.add(map);
        }
        return data;
    }

    //item点击事件 final SimpleAdapter adapter
    /*public void setlistener() {
        list_week = view.findViewById(R.id.list_week);
        list_week.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //我们需要的内容，跳转页面或显示详细信息
                //System.out.println(adapter.getItem(position));
            }
        });
    }*/

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
