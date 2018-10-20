package cn.edu.mystore.mystore.mvp.view.view;

import cn.edu.mystore.mystore.base.mvpbase.BaseView;

public interface RecommendedFragmentView extends BaseView {


    void onRecommendedDataSuccess();
    void onRecommendedDataError();
}
