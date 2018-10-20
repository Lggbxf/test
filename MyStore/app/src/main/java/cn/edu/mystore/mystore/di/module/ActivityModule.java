package cn.edu.mystore.mystore.di.module;

import android.app.Activity;
import android.content.Context;

import cn.edu.mystore.mystore.di.scope.ContextLife;
import cn.edu.mystore.mystore.di.scope.PerActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity ;

    public ActivityModule(Activity activity){
        this.mActivity = activity ;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideActivityContext(){
        return mActivity ;
    }

    @Provides
    @PerActivity
    public Activity provideActivity(){
        return mActivity ;
    }
}
