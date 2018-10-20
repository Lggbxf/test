package cn.edu.mystore.mystore.mvp.presenter.impl;

import android.os.SystemClock;

import javax.inject.Inject;

import cn.edu.mystore.mystore.base.mvpbase.BasePresenterImpl;
import cn.edu.mystore.mystore.mvp.presenter.RecommendedFragmentPresenter;
import cn.edu.mystore.mystore.mvp.view.view.RecommendedFragmentView;

public class RecommendedPresenterImpl extends BasePresenterImpl<RecommendedFragmentView> implements RecommendedFragmentPresenter {

    @Inject
    public RecommendedPresenterImpl(){

    }

    @Override
    public void getRecommendedData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                mPresenterView.onRecommendedDataSuccess();
            }
        }).start();
    }

}
