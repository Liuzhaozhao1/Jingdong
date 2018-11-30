package com.bwie.Jingdong.di;

import java.lang.ref.WeakReference;

public class Presenter implements IConteract.IPresenter<IConteract.IView>{

    IConteract.IView iView;
    private IConteract.IModel iModel;
    private WeakReference<IConteract.IView> iViewWeakReference;
    private WeakReference<IConteract.IModel> iModelWeakReference;
    private String utl = "";
    @Override
    public void attachIVew(IConteract.IView iView) {
        this.iView = iView;
         iModel = new IModel();
        iViewWeakReference = new WeakReference<>(iView);
        iModelWeakReference = new WeakReference<>(iModel);

    }

    @Override
    public void deatchView(IConteract.IView iView) {

        iViewWeakReference.clear();
        iModelWeakReference.clear();
    }

    @Override
    public void requestInfo(String url_string) {
        utl = url_string;
        iModel.requestData(utl,new IConteract.IModel.callListener() {
            @Override
            public void responseMsg(String message) {
                iView.showData(message);
            }
        });
    }

    @Override
    public void requestList(String url_string1) {

        utl = url_string1;
        iModel.requestData(utl, new IConteract.IModel.callListener() {
            @Override
            public void responseMsg(String message) {
                iView.showList(message);
            }
        });
    }

    @Override
    public void requestList1(String url_string2) {
        utl = url_string2;
        iModel.requestData(utl, new IConteract.IModel.callListener() {
            @Override
            public void responseMsg(String message) {
                iView.showList1(message);
            }
        });
    }
}
