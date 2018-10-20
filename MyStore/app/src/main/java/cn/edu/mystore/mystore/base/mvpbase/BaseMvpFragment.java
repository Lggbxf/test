package cn.edu.mystore.mystore.base.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import cn.edu.mystore.mystore.base.BaseFragment;
import cn.edu.mystore.mystore.base.StoreApplication;
import cn.edu.mystore.mystore.di.component.DaggerFragmentComponent;
import cn.edu.mystore.mystore.di.component.FragmentComponent;
import cn.edu.mystore.mystore.di.module.FragmentModule;

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    protected FragmentComponent fragmentComponent;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentComponent();
        mPresenter = initInjector();
        mPresenter.attachView(this);
    }

    protected void initFragmentComponent(){
        fragmentComponent= DaggerFragmentComponent.builder().fragmentModule(new FragmentModule(this))
                .appComponent(((StoreApplication)getActivity().getApplication()).getAppComponent()).build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }

    protected abstract T initInjector();


    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
