package com.gcode.rateit.extras.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.gcode.rateit.extras.values.Constants;
import com.gcode.rateit.rateit.MyApplication;

public class SharedPrefUtils {
    public static void saveToPreferences(String preferenceName, String value) {
        SharedPreferences.Editor editor = getSharedPref().edit();
        editor.putString(preferenceName, value);
        editor.apply();
    }

    public static String readFromPreferences(String preferenceName, String defaultValue) {
        return getSharedPref().getString(preferenceName, defaultValue);
    }

    public static void saveToPreferences(String preferenceName, Integer value) {
        SharedPreferences.Editor editor = getSharedPref().edit();
        editor.putInt(preferenceName, value);
        editor.apply();
    }

    public static Integer readFromPreferences(String preferenceName, Integer defaultValue) {
        return getSharedPref().getInt(preferenceName, defaultValue);
    }

    public static void saveToPreferences(String preferenceName, Long value) {
        SharedPreferences.Editor editor = getSharedPref().edit();
        editor.putLong(preferenceName, value);
        editor.apply();
    }

    public static Long readFromPreferences(String preferenceName, Long defaultValue) {
        return getSharedPref().getLong(preferenceName, defaultValue);
    }

    private static SharedPreferences getSharedPref() {
        return MyApplication.getAppContext().
                getSharedPreferences(Constants.PREFERENCES_FILE, Context.MODE_PRIVATE);
    }
}
