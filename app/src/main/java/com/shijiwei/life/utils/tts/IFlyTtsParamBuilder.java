package com.shijiwei.life.utils.tts;

import android.os.Environment;

import com.iflytek.cloud.SpeechSynthesizer;

import java.util.Map;
import java.util.TreeMap;

public class IFlyTtsParamBuilder {

  public static final Map<String, String> VOICERS = new TreeMap<>();

  public static final String WAVE = "wav";
  public static String DEFAULT_AUDIO_PATH = "/TTS/tts";
  public static String DEFAULT_VOICE_NAME = "女声1";

  public static int DEFAULT_SPEED = 50;
  public static int DEFAULT_PITCH = 50;
  public static int DEFAULT_VOLUME = 50;

  static {
    VOICERS.put("女声1", "xiaoyan");
    VOICERS.put("女声2", "aisxping");
    VOICERS.put("女声3", "aisjinger");
    VOICERS.put("男声1", "aisjiuxu");
    VOICERS.put("童声1", "aisbabyxu");
  }

  private SpeechSynthesizer ttser;

  public IFlyTtsParamBuilder(SpeechSynthesizer ttser) {
    this.ttser = ttser;
  }

  public IFlyTtsParamBuilder setParameter(String key, Object value) {
    ttser.setParameter(key, String.valueOf(value));
    return this;
  }

  public static String getAudioPath(String path) {
    return Environment.getExternalStorageDirectory() + "/" + path + "." + WAVE;
  }
}
