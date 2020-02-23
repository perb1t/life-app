package com.shijiwei.life.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.shijiwei.life.app.LifeApplication;

public class PreferencesUtils {

  private static final SharedPreferences preferences;

  static {
    preferences = PreferenceManager.getDefaultSharedPreferences(LifeApplication.getInstance());
  }

  public static <V> V get(String key, V defaultValue) {
    if (defaultValue instanceof String) {
      return (V) preferences.getString(key, (String) defaultValue);
    } else if (defaultValue instanceof Integer) {
      return (V)(Integer)preferences.getInt(key, (Integer) defaultValue);
    } else {
      return defaultValue;
    }
  }

  public static <V> void put(String key, V value) {
    if (value instanceof String) {
      preferences.edit().putString(key, (String) value).commit();
    } else if (value instanceof Integer) {
      preferences.edit().putInt(key, (Integer) value).commit();
    }
  }
}
