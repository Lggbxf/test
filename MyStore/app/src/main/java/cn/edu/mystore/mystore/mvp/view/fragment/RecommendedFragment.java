package cn.edu.mystore.mystore.mvp.view.fragment;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import cn.edu.mystore.mystore.base.BaseFragment;
import cn.edu.mystore.mystore.base.mvpbase.BaseMvpFragment;
import cn.edu.mystore.mystore.base.mvpbase.BasePresenterImpl;
import cn.edu.mystore.mystore.mvp.presenter.RecommendedFragmentPresenter;
import cn.edu.mystore.mystore.mvp.presenter.impl.RecommendedPresenterImpl;
import cn.edu.mystore.mystore.mvp.view.view.RecommendedFragmentView;

public class RecommendedFragment extends BaseMvpFragment<RecommendedPresenterImpl> implements RecommendedFragmentView {

    @Inject
    public RecommendedPresenterImpl recommendedPresenter;

    @Override
    protected void load() {
        recommendedPresenter.getRecommendedData();
    }

    @Override
    protected View createSuccessView() {
        TextView tv = new TextView(getContext());
        tv.setText("推荐加载成功");
        return tv;
    }

    @Override
    protected RecommendedPresenterImpl initInjector() {
        fragmentComponent.inject(this);
        return recommendedPresenter;
    }

    @Override
    public void onRecommendedDataSuccess() {
        setState(LoadResult.success);
    }

    @Override
    public void onRecommendedDataError() {
        setState(LoadResult.error);
    }
}
