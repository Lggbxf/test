package cn.edu.mystore.mystore.base;

import android.os.Handler;

import cn.edu.mystore.mystore.di.component.AppComponent;
import cn.edu.mystore.mystore.di.component.DaggerAppComponent;
import cn.edu.mystore.mystore.di.module.AppModule;

public class StoreApplication extends App {
    private static int mMainThreadId;
    private static Handler mHandler;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mHandler=new Handler();
        initApplicationComponent();

    }

    private void initApplicationComponent(){
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 返回主线程的pid
     * @return
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }
    /**
     * 返回Handler
     * @return
     */
    public static Handler getHandler() {
        return mHandler;
    }
}
