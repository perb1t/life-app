package com.shijiwei.life.app;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;
import com.shijiwei.life.di.component.DaggerAppComponent;
import com.shijiwei.life.di.module.AppModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import rx_activity_result2.RxActivityResult;

import static com.shijiwei.life.app.Constant.IFLY_APP_ID;

public class LifeApplication extends Application implements HasAndroidInjector {

  @Inject DispatchingAndroidInjector<Object> androidInjector;

  private static LifeApplication application;

  @Override
  public void onCreate() {
    /* 初始化科大讯飞语音SDK */
    SpeechUtility.createUtility(this, "appid=" + IFLY_APP_ID);
    super.onCreate();
    application = this;
    DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);
    RxActivityResult.register(this);
  }

  @Override
  public AndroidInjector<Object> androidInjector() {
    return androidInjector;
  }

  public static LifeApplication getInstance() {
    return application;
  }
}
