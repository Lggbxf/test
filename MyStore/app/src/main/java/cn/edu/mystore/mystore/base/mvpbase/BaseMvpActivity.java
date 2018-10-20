package cn.edu.mystore.mystore.base.mvpbase;

import android.os.Bundle;
import android.widget.Toast;

import cn.edu.mystore.mystore.base.BaseActivity;
import cn.edu.mystore.mystore.base.StoreApplication;
import cn.edu.mystore.mystore.di.component.ActivityComponent;
import cn.edu.mystore.mystore.di.component.DaggerActivityComponent;
import cn.edu.mystore.mystore.di.module.ActivityModule;

/**
 * Activity实现MVP的基类
 * @param <T>
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    protected ActivityComponent activityComponent;
    protected T mPresenter;

    //通过Dagger2注入的是Presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        mPresenter = initInjector();
        //绑定view
        mPresenter.attachView(this);
    }

    protected void initActivityComponent(){
        DaggerActivityComponent.builder().activityModule(new ActivityModule(this))
                .appComponent(((StoreApplication)getApplication()).getAppComponent())
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            //解除绑定
            mPresenter.detachView();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 完成注入并返回Presenter对象
     * @return
     */
    protected abstract T initInjector();

}
