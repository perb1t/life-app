package com.shijiwei.life.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.iflytek.cloud.SpeechConstant;
import com.shijiwei.life.R;
import com.shijiwei.life.presentation.tts.TtsPresenter;
import com.shijiwei.life.presentation.tts.TtsView;
import com.shijiwei.life.ui.base.BaseActivity;
import com.shijiwei.life.utils.PreferencesUtils;
import com.shijiwei.life.widget.SeekBar;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

import static com.shijiwei.life.app.Constant.KEY_TTS_FILE_PATH;
import static com.shijiwei.life.app.Constant.KEY_TTS_PITCH;
import static com.shijiwei.life.app.Constant.KEY_TTS_SPEED;
import static com.shijiwei.life.app.Constant.KEY_TTS_VOICER_NAME;
import static com.shijiwei.life.app.Constant.KEY_TTS_VOLUME;
import static com.shijiwei.life.utils.ScreenAdapter.HEIGHT;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.DEFAULT_AUDIO_PATH;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.DEFAULT_PITCH;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.DEFAULT_SPEED;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.DEFAULT_VOICE_NAME;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.DEFAULT_VOLUME;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.VOICERS;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.getAudioPath;

public class TtsActivity extends BaseActivity implements TtsView, SeekBar.Callback {

  @BindView(R.id.drawer_container)
  DrawerLayout drawerContainer;

  @BindView(R.id.sb_speed)
  SeekBar sbSpeed;

  @BindView(R.id.sb_pitch)
  SeekBar sbPitch;

  @BindView(R.id.sb_volume)
  SeekBar sbVolume;

  @BindView(R.id.et_tts_text)
  EditText etTtsText;

  @BindView(R.id.et_file_path)
  EditText etFilePath;

  @BindView(R.id.tv_voicer)
  TextView tvVoicer;

  @Inject TtsPresenter ttsPresenter;

  @Override
  public int getLayoutResId() {
    return R.layout.activity_tts;
  }

  @Override
  public boolean showToolbar() {
    return true;
  }

  @Override
  public void onInit(@Nullable Bundle savedInstanceState) {
    delegate(ttsPresenter, this);
    initWidget();
    drawerContainer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

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
                if (aBoolean) {
                  ttsPresenter.initTTS(TtsActivity.this);
                } else {
                  finish();
                }
              }
            });
  }

  private void initWidget() {
    sbSpeed.setValue(PreferencesUtils.get(KEY_TTS_SPEED, DEFAULT_SPEED));
    sbPitch.setValue(PreferencesUtils.get(KEY_TTS_PITCH, DEFAULT_PITCH));
    sbVolume.setValue(PreferencesUtils.get(KEY_TTS_VOLUME, DEFAULT_VOLUME));
    sbSpeed.setCallback(this);
    sbVolume.setCallback(this);
    sbPitch.setCallback(this);
    tvVoicer.setText(PreferencesUtils.get(KEY_TTS_VOICER_NAME, DEFAULT_VOICE_NAME));
    etFilePath.setText(PreferencesUtils.get(KEY_TTS_FILE_PATH, DEFAULT_AUDIO_PATH));
    etFilePath.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {}

          @Override
          public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(s)) {
              toast("请输入文件路径");
            } else {
              PreferencesUtils.put(KEY_TTS_FILE_PATH, s.toString());
              ttsPresenter
                  .getiFlyTtsParamBuilder()
                  .setParameter(SpeechConstant.TTS_AUDIO_PATH, getAudioPath(s.toString()));
            }
          }
        });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_tts, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.set_tts_config) {
      if (drawerContainer.isDrawerOpen(Gravity.RIGHT)) {
        drawerContainer.closeDrawer(Gravity.RIGHT);
      } else {
        drawerContainer.openDrawer(Gravity.RIGHT);
      }
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public int getOrientation() {
    return HEIGHT;
  }

  @OnClick(R.id.btn_tts)
  public void tts(View view) {
    Editable text = etTtsText.getText();
    ttsPresenter.ttsAndPreplay(text);
  }

  @OnClick({R.id.draw_menu_layout})
  public void m() {}

  @OnClick({R.id.tv_voicer})
  public void selectVoicer(TextView tv) {
    String[] keySet = VOICERS.keySet().toArray(new String[VOICERS.size()]);
    new AlertDialog.Builder(this)
        .setTitle("请选择发声模型")
        .setItems(
            keySet,
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                String name = keySet[which];
                tv.setText(name);
                PreferencesUtils.put(KEY_TTS_VOICER_NAME, name);
                ttsPresenter
                    .getiFlyTtsParamBuilder()
                    .setParameter(SpeechConstant.VOICE_NAME, VOICERS.get(name));
              }
            })
        .show();
  }

  @Override
  public void onSelected(SeekBar seekBar, int value) {
    switch (seekBar.getId()) {
      case R.id.sb_speed:
        PreferencesUtils.put(KEY_TTS_SPEED, value);
        ttsPresenter
            .getiFlyTtsParamBuilder()
            .setParameter(SpeechConstant.SPEED, String.valueOf(value));
        break;
      case R.id.sb_pitch:
        PreferencesUtils.put(KEY_TTS_PITCH, value);
        ttsPresenter
            .getiFlyTtsParamBuilder()
            .setParameter(SpeechConstant.PITCH, String.valueOf(value));
        break;
      case R.id.sb_volume:
        PreferencesUtils.put(KEY_TTS_VOLUME, value);
        ttsPresenter
            .getiFlyTtsParamBuilder()
            .setParameter(SpeechConstant.VOLUME, String.valueOf(value));
        break;
    }
  }

  @Override
  public void initConfig() {}

  @Override
  public void showLoading() {}

  @Override
  public void hideLoading() {}
}
