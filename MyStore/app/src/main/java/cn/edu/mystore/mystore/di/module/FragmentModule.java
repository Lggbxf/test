package cn.edu.mystore.mystore.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import cn.edu.mystore.mystore.di.scope.ContextLife;
import cn.edu.mystore.mystore.di.scope.PerActivity;
import cn.edu.mystore.mystore.di.scope.PerFragment;
import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment){
        this.mFragment = fragment;
    }

    @Provides
    @PerFragment
    public Fragment provideFragment(){
        return mFragment;
    }

    @Provides
    @PerFragment
    public Activity provideFragmentActivity(){
        return mFragment.getActivity();
    }


    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideFragmentContext(){
        return mFragment.getContext();
    }
}
