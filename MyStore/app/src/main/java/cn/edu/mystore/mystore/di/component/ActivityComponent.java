package cn.edu.mystore.mystore.di.component;

import android.app.Activity;
import android.content.Context;

import cn.edu.mystore.mystore.mvp.view.activity.MainActivity;
import cn.edu.mystore.mystore.di.module.ActivityModule;
import cn.edu.mystore.mystore.di.scope.ContextLife;
import cn.edu.mystore.mystore.di.scope.PerActivity;
import dagger.Component;

@PerActivity
@Component(modules = ActivityModule.class,dependencies = AppComponent.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(MainActivity activity);
}
