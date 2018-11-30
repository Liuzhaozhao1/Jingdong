package com.bwie.Jingdong.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.Jingdong.R;
import com.bwie.Jingdong.adapter.MyNAdapter;
import com.bwie.Jingdong.adapter.MyUserAdapter;
import com.bwie.Jingdong.bean.News;
import com.bwie.Jingdong.bean.News1;
import com.bwie.Jingdong.bean.Users;
import com.bwie.Jingdong.di.IConteract;
import com.bwie.Jingdong.di.Presenter;
import com.google.gson.Gson;
import com.recker.flybanner.FlyBanner;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment implements IConteract.IView{

    private IConteract.IPresenter presenter;
    private FlyBanner viewpage;
    private  List<String> list =new ArrayList<>();
    private RecyclerView re_view;
    private  String url_string = "https://www.zhaoapi.cn/ad/getAd";
    private String url_string1 = "https://www.zhaoapi.cn/product/getCatagory";
    private String url_string2 = "https://www.zhaoapi.cn/product/getProductCatagory";
    private RecyclerView re_view1;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0)
            {
                String json = (String) msg.obj;
                Jiexi(json);
            }
        }

        private void Jiexi(String json) {
            Gson gson = new Gson();
            News news = gson.fromJson(json, News.class);
            List<News.DataBean> data = news.getData();

           // Log.i("xxx",data+"");
            for (int i = 0; i <data.size() ; i++) {
                String s = data.get(i).getIcon();
                list.add(s);
            }
            //Log.i("xxx",list+"");
            viewpage.setImagesUrl(list);

        }
    };
    private SmartRefreshLayout smar_layout;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1, container, false);
        viewpage = (FlyBanner)view.findViewById(R.id.view_pager);
        re_view = (RecyclerView)view.findViewById(R.id.re_view);
        re_view1 = (RecyclerView)view.findViewById(R.id.re_view1);
        smar_layout = (SmartRefreshLayout)view.findViewById(R.id.smar_layout);
        smar_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        smar_layout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
        smar_layout.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true));

        smar_layout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建P层
        presenter = new Presenter();
        presenter.attachIVew(this);
        presenter.requestInfo(url_string);

    }

    @Override
    public void showData(String message) {
        Log.d("xxx", message);
        Message message1 = new Message();
        message1.what=0;
        message1.obj=message;
        handler.sendMessage(message1);
        presenter.requestList(url_string1);
    }


    @Override
    public void showList(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Users users = gson.fromJson(message, Users.class);
                Log.i("aaa",message);
                List<Users.DataBean> data = users.getData();
                Log.i("zzz",data+"");
                MyUserAdapter myUserAdapter = new MyUserAdapter(getActivity(), (ArrayList<Users.DataBean>) data);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
                re_view.setLayoutManager(gridLayoutManager);
                re_view.setAdapter(myUserAdapter);
            }
        });
        presenter.requestList1(url_string);
    }

    @Override
    public void showList1(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                News1 news1 = gson.fromJson(message, News1.class);
                Log.i("aaa",message);
                List<News1.TuijianBean.ListBean> list1 = news1.getTuijian().getList();
                Log.i("zzz",list1+"");
                MyNAdapter myNAdapter1 = new MyNAdapter(getActivity(), (ArrayList<News1.TuijianBean.ListBean>) list1);
                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
                re_view1.setLayoutManager(gridLayoutManager1);
                re_view1.setAdapter(myNAdapter1);
            }
        });
    }
}
