package cn.edu.mystore.mystore.mvp.view.fragment;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import cn.edu.mystore.mystore.base.BaseFragment;

public class RankingsFragment extends BaseFragment {
    @Override
    protected void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                setState(LoadResult.success);
            }
        }).start();
    }

    @Override
    protected View createSuccessView() {
        TextView tv = new TextView(getContext());
        tv.setText("排行加载成功");
        return tv;
    }
}
