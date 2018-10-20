package cn.edu.mystore.mystore.di.component;

import android.content.Context;

import cn.edu.mystore.mystore.di.module.AppModule;
import cn.edu.mystore.mystore.di.scope.ContextLife;
import cn.edu.mystore.mystore.di.scope.PerApp;
import dagger.Component;

/**
 * 提供全局单例Context对象
 */
@PerApp
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    Context getApplicationContext();

}
