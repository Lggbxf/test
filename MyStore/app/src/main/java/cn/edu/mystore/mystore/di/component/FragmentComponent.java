package cn.edu.mystore.mystore.di.component;

import android.app.Activity;
import android.content.Context;

import cn.edu.mystore.mystore.mvp.view.fragment.ManagerFragment;
import cn.edu.mystore.mystore.mvp.view.fragment.MineFragment;
import cn.edu.mystore.mystore.mvp.view.fragment.RankingsFragment;
import cn.edu.mystore.mystore.mvp.view.fragment.RecommendedFragment;
import cn.edu.mystore.mystore.mvp.view.fragment.TypeFragment;
import cn.edu.mystore.mystore.di.module.FragmentModule;
import cn.edu.mystore.mystore.di.scope.ContextLife;
import cn.edu.mystore.mystore.di.scope.PerFragment;
import dagger.Component;

@PerFragment
@Component(modules = FragmentModule.class,dependencies = AppComponent.class)
public interface FragmentComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(RecommendedFragment fragment);
    void inject(TypeFragment fragment);
    void inject(RankingsFragment fragment);
    void inject(ManagerFragment fragment);
    void inject(MineFragment fragment);
}
