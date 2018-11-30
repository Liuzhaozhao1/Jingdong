package com.bwie.Jingdong.di;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtils {

    private static HttpUtils httpUtils;
    private final OkHttpClient okHttpClient;

    public HttpUtils() {
        okHttpClient = new OkHttpClient();
    }

    //单例

    public static HttpUtils getinstance()
    {
        if(httpUtils == null)
        {
            synchronized (HttpUtils.class)
            {
                if(httpUtils==null)
                {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    public void getdata(String path, Callback callback)
    {
        Request request = new Request.Builder().url(path).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public void postdata(String path1, FormBody formBody,Callback callback)
    {
        Request request = new Request.Builder().method("POST",formBody).url(path1).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
