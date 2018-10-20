package cn.edu.mystore.mystore.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import cn.edu.mystore.mystore.R;
import cn.edu.mystore.mystore.utils.UIUtils;

public abstract class BaseFragment extends Fragment {
    /**
     * 默认状态
     */
    public final static int STATE_UNKNOW = 0;

    /**
     * 加载中状态
     */
    public final static int STATE_LOADING = 1;

    /**
     * 加载失败状态
     */
    public final static int STATE_ERROR = 2;

    /**
     * 加载成功状态
     */
    public final static int STATE_SUCCESS = 3;

    /**
     * 加载空状态
     */
    public final static int STATE_EMPTY = 4;

    private int state = STATE_UNKNOW;

    private View loadingView;
    private View errorView;
    private View successView;
    private View emptyView;

    private FrameLayout frameLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        frameLayout = new FrameLayout(getContext());
        init();
        show();
        return frameLayout;
    }


    /**
     * 将布局添加到帧布局中
     */
    private void init() {
        if(loadingView == null){
            loadingView = createLoadingView();
            frameLayout.addView(loadingView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }
        if(errorView == null){
            errorView = createErrorView();
            frameLayout.addView(errorView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }
        if(emptyView == null){
            emptyView = createEmptyView();
            frameLayout.addView(emptyView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }
        if(successView == null){
            successView = createSuccessView();
            frameLayout.addView(successView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        }

        showPager();

    }



    /**
     * 请求网络，修改状态
     */
    public void show() {
        if(state == STATE_UNKNOW || state == STATE_ERROR || state == STATE_EMPTY){
            state = STATE_LOADING;
            load();
        }
        showPager();
    }

    /**
     * 模拟网络请求
     */
    protected abstract void load();

    protected void setState(LoadResult result ) {
        state = result.getValue();
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPager();
            }
        });
    }

    public enum LoadResult{
        error(STATE_ERROR),success(STATE_SUCCESS),empty(STATE_EMPTY);
        int value;
        LoadResult(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }

    /**
     * 根据不同状态显示不同布局
     */
    private void showPager() {
        if(loadingView!=null){
            loadingView.setVisibility(state == STATE_UNKNOW||state == STATE_LOADING ? View.VISIBLE : View.GONE);
        }
        if(errorView!=null){
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.GONE);
        }
        if(emptyView!=null){
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.GONE);
        }
        if(successView!=null){
            successView.setVisibility(state == STATE_SUCCESS ? View.VISIBLE : View.GONE);
        }

    }

    protected abstract View createSuccessView();

    private View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_page_empty);
    }

    private View createErrorView() {
        return UIUtils.inflate(R.layout.loading_error_page);
    }

    private View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page);
    }
}
