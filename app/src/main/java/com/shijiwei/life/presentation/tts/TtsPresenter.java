package com.shijiwei.life.presentation.tts;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.shijiwei.life.presentation.base.RxPresenter;
import com.shijiwei.life.utils.PreferencesUtils;
import com.shijiwei.life.utils.tts.IFlyTtsParamBuilder;

import javax.inject.Inject;

import static com.shijiwei.life.app.Constant.KEY_TTS_FILE_PATH;
import static com.shijiwei.life.app.Constant.KEY_TTS_PITCH;
import static com.shijiwei.life.app.Constant.KEY_TTS_SPEED;
import static com.shijiwei.life.app.Constant.KEY_TTS_VOICER_NAME;
import static com.shijiwei.life.app.Constant.KEY_TTS_VOLUME;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.DEFAULT_AUDIO_PATH;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.DEFAULT_PITCH;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.DEFAULT_SPEED;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.DEFAULT_VOICE_NAME;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.DEFAULT_VOLUME;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.VOICERS;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.WAVE;
import static com.shijiwei.life.utils.tts.IFlyTtsParamBuilder.getAudioPath;

public class TtsPresenter extends RxPresenter<TtsView> {

  private SpeechSynthesizer ttser;
  private IFlyTtsParamBuilder iFlyTtsParamBuilder;

  @Inject
  public TtsPresenter() {}

  public void initTTS(Context context) {
    ttser =
        SpeechSynthesizer.createSynthesizer(
            context,
            new InitListener() {
              @Override
              public void onInit(int code) {
                if (code != ErrorCode.SUCCESS) {
                } else {
                  initTtsConfig(getiFlyTtsParamBuilder());
                }
              }
            });
  }

  public IFlyTtsParamBuilder getiFlyTtsParamBuilder() {
    if (iFlyTtsParamBuilder == null) {
      iFlyTtsParamBuilder = new IFlyTtsParamBuilder(ttser);
    }
    return iFlyTtsParamBuilder;
  }

  public IFlyTtsParamBuilder initTtsConfig(IFlyTtsParamBuilder builder) {
    builder
        .setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD)
        .setParameter(SpeechConstant.TTS_DATA_NOTIFY, 1)
        .setParameter(SpeechConstant.STREAM_TYPE, 3)
        .setParameter(SpeechConstant.SPEED, PreferencesUtils.get(KEY_TTS_SPEED, DEFAULT_SPEED))
        .setParameter(SpeechConstant.VOLUME, PreferencesUtils.get(KEY_TTS_VOLUME, DEFAULT_VOLUME))
        .setParameter(SpeechConstant.PITCH, PreferencesUtils.get(KEY_TTS_PITCH, DEFAULT_PITCH))
        .setParameter(
            SpeechConstant.VOICE_NAME,
            VOICERS.get(PreferencesUtils.get(KEY_TTS_VOICER_NAME, DEFAULT_VOICE_NAME)))
        .setParameter(SpeechConstant.AUDIO_FORMAT, WAVE)
        .setParameter(
            SpeechConstant.TTS_AUDIO_PATH,
            getAudioPath(PreferencesUtils.get(KEY_TTS_FILE_PATH, DEFAULT_AUDIO_PATH)));

    return builder;
  }

  public void ttsAndPreplay(Editable text) {
    if (TextUtils.isEmpty(text)) {
      getBaseView().toast("请输入文本");
    } else {
      ttser.startSpeaking(
          text.toString(),
          new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {}

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {}

            @Override
            public void onSpeakPaused() {}

            @Override
            public void onSpeakResumed() {}

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {}

            @Override
            public void onCompleted(SpeechError speechError) {}

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {}
          });
    }
  }
}
