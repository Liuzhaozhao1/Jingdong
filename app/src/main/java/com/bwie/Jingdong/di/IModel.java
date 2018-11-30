package com.bwie.Jingdong.di;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class IModel implements IConteract.IModel{

    private static String path = "https://www.zhaoapi.cn/ad/getAd";

    @Override
    public void requestData(String utl, final callListener callListener) {


        HttpUtils httpUtils = HttpUtils.getinstance();
        httpUtils.getdata(utl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                callListener.responseMsg(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();
                callListener.responseMsg(json);
            }
        });
    }
}
