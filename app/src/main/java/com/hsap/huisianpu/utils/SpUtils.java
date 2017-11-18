package com.hsap.huisianpu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhao on 2017/11/16.
 */

public class SpUtils {
    private static final String SPNAME = "hsap" ;
    private static SharedPreferences sp;

    //存布尔类型的值的方法
    public static void putBoolean(String key, boolean value, Context context){
        if (sp==null) {
            sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,value).commit();
    }
    //取布尔类型值
    public static boolean getBoolean(String key, Context context) {
        if (sp==null) {
            sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        boolean b = sp.getBoolean(key, false);
        return b;
    }
    //移除布尔类型值
    public static boolean removeKey(String key, Context context) {
        if (sp==null) {
            sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        return sp.edit().remove(key).commit();
    }
    //存String类型的值的方法
    public static void putString(String key, String value, Context context){
        if (sp==null) {
            sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key,value).commit();
    }
    //取String类型值
    public static String getString(String key, Context context) {
        if (sp==null) {
            sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        String b = sp.getString(key, "");
        return b;
    }
    //存int类型的值的方法
    public static void putInt(String key, int value, Context context){
        if (sp==null) {
            sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key,value).commit();
    }
    //取int类型值
    public static int getInt(String key, Context context) {
        if (sp==null) {
            sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        int b = sp.getInt(key,0);
        return b;
    }
}
