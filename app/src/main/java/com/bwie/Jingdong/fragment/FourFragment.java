package com.bwie.Jingdong.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.Jingdong.R;
import com.bwie.Jingdong.adapter.MyQuanAdapter;
import com.bwie.Jingdong.bean.Urls;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FourFragment extends Fragment {

    private CheckBox check_box;
    private TextView text_price;
    private TextView text_sum;
    private RecyclerView re_view2;
    private static final String pathurl = "http://www.zhaoapi.cn/product/getCarts?uid=71";
    Context context = getActivity();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f4, container, false);
        check_box = (CheckBox)view.findViewById(R.id.check_box);
        text_price = (TextView)view.findViewById(R.id.text_price);
        text_sum = (TextView)view.findViewById(R.id.text_sum);
        re_view2 = (RecyclerView)view.findViewById(R.id.re_view2);
        init();
        return view;
    }

    private void init() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        okHttpClient.newCall(new Request.Builder().url(pathurl).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String s = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Urls urls = gson.fromJson(s, Urls.class);
                        List<Urls.DataBean> data = urls.getData();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        re_view2.setLayoutManager(linearLayoutManager);
                        //设置适配器
                        MyQuanAdapter myQuanAdapter = new MyQuanAdapter(getActivity(), data);
                        re_view2.setAdapter(myQuanAdapter);
                        //myQuanAdapter.setOnItemCheckedLisenter();
                    }
                });
            }
        });
    }
}
