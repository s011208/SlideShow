
package com.bj4.yhh.slideshow;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingManager {
    private Context mContext;

    private static SettingManager mInstance;

    private SharedPreferences mPref;

    private static final String PREF_NAME = "_slideshow_pref";

    public synchronized SettingManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SettingManager(context);
        }
        return mInstance;
    }

    private SettingManager(Context context) {
        mContext = context.getApplicationContext();
        mPref = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

}
