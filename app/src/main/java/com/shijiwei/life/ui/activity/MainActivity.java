package com.shijiwei.life.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shijiwei.life.R;
import com.shijiwei.life.ui.base.BaseActivity;
import com.shijiwei.life.utils.Navigator;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

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
  }

  @Override
  public int getOrientation() {
    return 0;
  }

  @OnClick(R.id.btn_tts)
  public void navigateToTTS() {
    Navigator.navigateToTTS(this);
  }

  @OnClick(R.id.btn_merge)
  public void navigateToMerge() {}
}
