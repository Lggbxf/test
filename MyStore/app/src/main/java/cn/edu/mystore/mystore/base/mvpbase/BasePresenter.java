package cn.edu.mystore.mystore.base.mvpbase;

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);
    void detachView();
}
