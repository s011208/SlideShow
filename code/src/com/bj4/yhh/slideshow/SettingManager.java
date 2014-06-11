package com.bj4.yhh.slideshow;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;

public class SettingManager {
	private Context mContext;

	private Handler mHandler = new Handler();

	private static SettingManager mInstance;

	private SharedPreferences mPref;

	public interface TextSettingChangeCallback {
		public void onTextSettingChanged();
	}

	public final ArrayList<TextSettingChangeCallback> mCallbacks = new ArrayList<TextSettingChangeCallback>();

	private static final String PREF_NAME = "_slideshow_pref";

	private static final String KEY_TEXT = "pref_text";

	private static final String KEY_BACKGROUND = "pref_background";

	private static final String KEY_TEXT_COLOR = "pref_text_color";

	private static final String KEY_TEXT_SIZE = "pref_text_size";

	private static final String KEY_TEXT_STYLE = "pref_text_style";

	private static final String KEY_DURATION = "pref_durate";

	public synchronized static SettingManager getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new SettingManager(context);
		}
		return mInstance;
	}

	private SettingManager(Context context) {
		mContext = context.getApplicationContext();
		mPref = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
	}

	public void registerCallback(TextSettingChangeCallback cb) {
		if (mCallbacks.contains(cb) == false)
			mCallbacks.add(cb);
	}

	public void unRegisterCallback(TextSettingChangeCallback cb) {
		mCallbacks.remove(cb);
	}

	public void notifySettingChanged() {

		for (final TextSettingChangeCallback cb : mCallbacks) {
			mHandler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					cb.onTextSettingChanged();
				}
			});
		}
	}

	public int getDuration() {
		return mPref.getInt(KEY_DURATION, 5000);
	}

	public void setDuration(int duration) {
		mPref.edit().putInt(KEY_DURATION, duration).commit();
		notifySettingChanged();
	}

	public int getBackgroundColor() {
		return mPref.getInt(KEY_BACKGROUND, Color.WHITE);
	}

	public void setBackgroundColor(int color) {
		mPref.edit().putInt(KEY_BACKGROUND, color).commit();
		notifySettingChanged();
	}

	public int getTextColor() {
		return mPref.getInt(KEY_TEXT_COLOR, Color.BLACK);
	}

	public void setTextColor(int color) {
		mPref.edit().putInt(KEY_TEXT_COLOR, color).commit();
		notifySettingChanged();
	}

	public int getTextSize() {
		return mPref.getInt(KEY_TEXT_SIZE, 25);
	}

	public void setTextSize(int size) {
		mPref.edit().putInt(KEY_TEXT_SIZE, size).commit();
		notifySettingChanged();
	}

	public String getTextStyle() {
		return mPref.getString(KEY_TEXT_STYLE, "");
	}

	public void setTextStyle(String style) {
		mPref.edit().putString(KEY_TEXT_STYLE, style).commit();
		notifySettingChanged();
	}

	public String getText() {
		return mPref.getString(KEY_TEXT, "SHOW!");
	}

	public void setText(String text) {
		mPref.edit().putString(KEY_TEXT, text).commit();
		notifySettingChanged();
	}

}
