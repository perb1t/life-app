package com.shijiwei.life.presentation.base;

public interface BasePresenter <V extends BaseView> {

    void attach(V baseView);

    void detach();

    V getBaseView();
}
