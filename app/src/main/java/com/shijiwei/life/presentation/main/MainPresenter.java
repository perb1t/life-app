package com.shijiwei.life.presentation.main;

import android.util.Log;

import com.shijiwei.life.data.model.Holiday;
import com.shijiwei.life.data.repository.BaiduRepository;
import com.shijiwei.life.presentation.base.RxPresenter;
import com.squareup.moshi.Json;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class MainPresenter extends RxPresenter<MainView> {

    private final BaiduRepository baiduRepository;

    @Inject
    public MainPresenter(BaiduRepository baiduRepository) {
        this.baiduRepository = baiduRepository;
    }


    public void query(String date){
        subscribeWith(baiduRepository.query(date), new DisposableObserver<Holiday>() {
            @Override
            public void onNext(Holiday holiday) {

                Log.e("onNext",holiday.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError",e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
