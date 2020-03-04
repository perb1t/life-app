package com.shijiwei.life.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.shijiwei.life.R;
import com.shijiwei.life.data.api.BaiduApi;
import com.shijiwei.life.data.model.BadiduSerchanResp;
import com.shijiwei.life.data.model.Holiday;
import com.shijiwei.life.presentation.main.MainPresenter;
import com.shijiwei.life.presentation.main.MainView;
import com.shijiwei.life.ui.base.BaseActivity;
import com.shijiwei.life.utils.Navigator;
import com.shijiwei.life.utils.rx.RxBus;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean showToolbar() {
        return false;
    }

    @Override
    public void onInit(@Nullable Bundle savedInstanceState) {
        new RxPermissions(this)
                .request(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.RECORD_AUDIO)
                .subscribe(
                        new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {

                            }
                        });

        delegate(mainPresenter,this);
        RxBus.getDefault().toObservel(String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("RxBus", s);
                    }
                });


    }

    @Override
    public int getOrientation() {
        return 0;
    }

    @OnClick(R.id.btn_tts)
    public void navigateToTTS() {
//    Navigator.navigateToTTS(this);
        mainPresenter.query("2012-01-01");


    }

    @OnClick(R.id.btn_merge)
    public void navigateToMerge() {
        Observable.just(2).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {
                RxBus.getDefault().post("12345");
                return null;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e("====accept", o.toString());
            }
        });

        startActivity(new Intent(this, ListWidgetActivity.class));
    }

    @Override
    public void main() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
