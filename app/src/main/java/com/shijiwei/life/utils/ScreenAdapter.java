package com.shijiwei.life.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class ScreenAdapter {

  public static final int WIDTH = 0;
  public static final int HEIGHT = 1;

  private static float sNoncompatDensity = 0f;
  private static float sNoncompatScaledDensity = 0f;

  public static void calcuteDensity(int ori, Activity activity, Application application) {
    final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
    if (sNoncompatDensity == 0) {
      sNoncompatDensity = appDisplayMetrics.density;
      sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
      application.registerComponentCallbacks(
          new ComponentCallbacks() {
            @Override
            public void onConfigurationChanged(Configuration newConfig) {
              if (newConfig != null && newConfig.fontScale > 0) {
                sNoncompatScaledDensity =
                    application.getResources().getDisplayMetrics().scaledDensity;
              }
            }

            @Override
            public void onLowMemory() {}
          });
    }

    final float targetDensity;
    if (ori == WIDTH) {
      targetDensity = appDisplayMetrics.widthPixels / 360f;
    } else {
      targetDensity = appDisplayMetrics.heightPixels / 640f;
    }
    final float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
    final int targetDensityDpi = (int) (160 * targetDensity);

    final DisplayMetrics activityDisplay = activity.getResources().getDisplayMetrics();
    activityDisplay.density = targetDensity;
    activityDisplay.scaledDensity = targetScaledDensity;
    activityDisplay.densityDpi = targetDensityDpi;
  }
}
