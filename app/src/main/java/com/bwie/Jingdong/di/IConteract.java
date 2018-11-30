package com.bwie.Jingdong.di;

public interface IConteract {

    //建立V层
    public interface IView
    {

        void showData(String message);
        void showList(String message);
        void showList1(String message);
    }

    //建立M层
    public interface IModel
    {
        void requestData(String utl,callListener callListener);

        //接口
        public interface callListener
        {
            void responseMsg(String message);
        }
    }

    //建立IP层
    public interface IPresenter<IView>
    {
        //关联
        void attachIVew(IView iView);
        //解绑
        void deatchView(IView iView);
        //请求
        void requestInfo(String url_string);
        void requestList(String url_string1);
        void requestList1(String url_string2);
    }
}
