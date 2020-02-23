package com.shijiwei.life.presentation.base;

public class RxPresenter<V extends BaseView> implements BasePresenter<V>{

    private V baseView;

    public void attach(V baseView) {
        this.baseView = baseView;
    }

    public void detach() {
        baseView = null;
    }

    @Override
    public V getBaseView() {
        return baseView;
    }

}
