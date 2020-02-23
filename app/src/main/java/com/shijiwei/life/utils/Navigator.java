package com.shijiwei.life.utils;

import android.app.Activity;
import android.content.Intent;

import com.shijiwei.life.ui.activity.TtsActivity;

import rx_activity_result2.RxActivityResult;

public class Navigator {

  public static void navigateToTTS(Activity context) {
    Intent intent = new Intent(context, TtsActivity.class);
    RxActivityResult.on(context).startIntent(intent);
  }
}
