package utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhaoyang on 16/8/16.
 * Sharepreference 工具类
 */
public class SharedpreUtil {

    public static final String SHARED_PREFERANCE_NAME = "jiandan_pref";

    /**
     * 保存int类型的数据
     * @param context
     * @param key
     * @param value
     */
    public static void putInt(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putInt(key, value).commit();
    }

    /**
     * 获取int类型的值, 带有默认值
     * @param context
     * @param key
     * @param defaultValue
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(key, defaultValue);
    }

    public static void putStr(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(key, value);
    }

    public static String getStr(Context context, String value) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(value, "");
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, value);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERANCE_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defaultValue);
    }

}
