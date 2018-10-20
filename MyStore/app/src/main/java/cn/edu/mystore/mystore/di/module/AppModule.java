package cn.edu.mystore.mystore.di.module;

import android.content.Context;

import cn.edu.mystore.mystore.base.StoreApplication;
import cn.edu.mystore.mystore.di.scope.ContextLife;
import cn.edu.mystore.mystore.di.scope.PerApp;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private StoreApplication storeApplication;

    public AppModule(StoreApplication application){
        this.storeApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideAppContext(){
        return storeApplication.getApplicationContext();
    }
}
