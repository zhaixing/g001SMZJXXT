package com.xcf.admin.couldclass.Activitys.Main;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.xcf.admin.couldclass.Activitys.Rank.RankuserActivity;
import com.xcf.admin.couldclass.Adapter.RankAdapter;
import com.xcf.admin.couldclass.Dao.RankService;
import com.xcf.admin.couldclass.Entity.rank.rankuserlist;
import com.xcf.admin.couldclass.MyContext.ClientContext;
import com.xcf.admin.couldclass.MyContext.HttpHelper;
import com.xcf.admin.couldclass.MyContext.MessageContext;
import com.xcf.admin.couldclass.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentOne_yhs extends Fragment {

    private TabHost tabHost;
    View view;
    ListView list_week;
    ListView list_month;
    ListView list_quar;
    ListView list_year;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_rank_top, container, false);
        super.onCreate(savedInstanceState);
        tabHost = view.findViewById(R.id.tabhost);
        tabHost.setup();
        list_week = view.findViewById(R.id.list_week);
        list_month = view.findViewById(R.id.list_month);
        list_quar = view.findViewById(R.id.list_quarter);
        list_year = view.findViewById(R.id.list_year);
        RankService rankService = HttpHelper.getInstance().getRetrofitStr().create(RankService.class);
        Call<rankuserlist> call = rankService.Getranklist();
        call.enqueue(new Callback<rankuserlist>() {
            @Override
            public void onResponse(Call<rankuserlist> call, Response<rankuserlist> response) {
                getData(response.body().getListweek(), list_week);
                getData(response.body().getListmonth(), list_month);
                getData(response.body().getListquarter(), list_quar);
                getData(response.body().getListyear(), list_year);

            }

            @Override
            public void onFailure(Call<rankuserlist> call, Throwable t) {
                Toast.makeText(getContext(), MessageContext.INTNET_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,getData());
       /* SimpleAdapter adapter = new SimpleAdapter(getContext(), getData(), R.layout.activity_rank_item,
                new String[]{"rank", "image", "text"},
                new int[]{R.id.list_item_rank, R.id.list_item_image, R.id.list_item_name});*/
        //setlistener();

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("周榜", null).setContent(R.id.list_week));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("月榜", null).setContent(R.id.list_month));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("季榜", null).setContent(R.id.list_quarter));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("年榜", null).setContent(R.id.list_year));
        return view;
    }


    public void getData(List list, ListView listView) {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            ArrayList arrayList = (ArrayList) list.get(i);
            map.put("rank", i + 1);
            map.put("image", ClientContext.My_ip + arrayList.get(3));
            map.put("name", arrayList.get(0));
            map.put("score", (int) ((double) arrayList.get(1)) + "分");
            map.put("userid", (int) ((double) arrayList.get(2)));
            data.add(map);
        }
        setlistener(listView, data);
        RankAdapter adapter = new RankAdapter(getActivity(), data);
        listView.setAdapter(adapter);
    }

    //item点击事件 final SimpleAdapter adapter
    public void setlistener(ListView listView, final List<Map<String, Object>> data) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //我们需要的内容，跳转页面或显示详细信息
                Intent intent = new Intent(getActivity(), RankuserActivity.class);
                intent.putExtra("userid", data.get(position).get("userid").toString());
                startActivity(intent);
            }
        });
    }

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
