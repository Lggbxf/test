package cn.edu.mystore.mystore.base;

import android.app.Application;

public class App extends Application {
    private static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static App getContext(){
        return context;
    }
}
