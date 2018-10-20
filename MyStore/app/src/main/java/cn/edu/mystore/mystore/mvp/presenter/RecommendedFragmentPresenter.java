package cn.edu.mystore.mystore.mvp.presenter;

import cn.edu.mystore.mystore.base.mvpbase.BasePresenter;
import cn.edu.mystore.mystore.mvp.view.view.RecommendedFragmentView;

public interface RecommendedFragmentPresenter extends BasePresenter<RecommendedFragmentView> {

    /**
     * 获取推荐数据
     */
    void getRecommendedData();
}
