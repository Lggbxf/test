package cn.edu.mystore.mystore.mvp.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.mystore.mystore.R;
import cn.edu.mystore.mystore.adapter.FixPagerAdapter;
import cn.edu.mystore.mystore.base.BaseActivity;
import cn.edu.mystore.mystore.base.BaseFragment;
import cn.edu.mystore.mystore.factory.FragmentFactory;
import cn.edu.mystore.mystore.utils.AppActivityManager;

public class MainActivity extends BaseActivity {
    private FixPagerAdapter fixPagerAdapter ;
    private String[] titles = {"推荐","分类","排行","管理","我的"};
    private List<Fragment> fragments ;
    private TabLayout tabLayout;
    private ViewPager mainViewPager ;
    private LinearLayout statusBar ;

    //退出返回按钮间隔时间
    private long exitTime = 0;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        setStatusBar();

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mainViewPager = (ViewPager) findViewById(R.id.main_viewPager);

        initViewPagerFragment();
        
    }

    private void initViewPagerFragment() {
        fixPagerAdapter = new FixPagerAdapter(getSupportFragmentManager()) ;

        fragments = new ArrayList<>() ;
        for(int i = 0 ;i < titles.length ; i ++){
            fragments.add(FragmentFactory.createFragment(i)) ;
        }
        fixPagerAdapter.setTitles(titles);
        fixPagerAdapter.setFragments(fragments);

        mainViewPager.setAdapter(fixPagerAdapter);
        tabLayout.setupWithViewPager(mainViewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        mainViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                BaseFragment fragment = FragmentFactory.createFragment(position);
                fragment.show();

            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //ToastUtils.showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                AppActivityManager.getInstance().AppExit(this);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


}
