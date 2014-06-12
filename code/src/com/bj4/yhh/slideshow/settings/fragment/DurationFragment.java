package com.bj4.yhh.slideshow.settings.fragment;

import com.bj4.yhh.slideshow.SettingManager;
import com.yenhsun.slidingshow.R;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class DurationFragment extends Fragment implements
		OnSeekBarChangeListener {

	private View mMainContainer;
	private SeekBar mDuration;
	private SettingManager mSetting;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		mMainContainer = inflater.inflate(R.layout.duration_fragment,
				container, false);
		mSetting = SettingManager.getInstance(getActivity());
		mDuration = (SeekBar) mMainContainer.findViewById(R.id.duration_seek);
		final int currentDuration = (mSetting.getDuration() / 1000) - 1;
		mDuration.setOnSeekBarChangeListener(this);
		mDuration.setProgress(currentDuration);
		return mMainContainer;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mSetting.setDuration((mDuration.getProgress() + 1) * 1000);
			}
		}).start();

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}
}
