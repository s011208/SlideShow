package com.bj4.yhh.slideshow.settings.fragment;

import com.bj4.yhh.slideshow.R;
import com.bj4.yhh.slideshow.SettingManager;

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

public class TextColorFragment extends Fragment implements
		OnSeekBarChangeListener {

	private View mMainContainer;
	private SeekBar mRed, mGreen, mBlue;
	private SettingManager mSetting;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		mMainContainer = inflater.inflate(R.layout.text_color_fragment,
				container, false);
		mSetting = SettingManager.getInstance(getActivity());
		mRed = (SeekBar) mMainContainer.findViewById(R.id.txt_color_seek_r);
		mGreen = (SeekBar) mMainContainer.findViewById(R.id.txt_color_seek_g);
		mBlue = (SeekBar) mMainContainer.findViewById(R.id.txt_color_seek_b);
		final int currentColor = mSetting.getTextColor();
		mRed.setOnSeekBarChangeListener(this);
		mGreen.setOnSeekBarChangeListener(this);
		mBlue.setOnSeekBarChangeListener(this);
		mRed.setProgress(Color.red(currentColor));
		mGreen.setProgress(Color.green(currentColor));
		mBlue.setProgress(Color.blue(currentColor));
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
				mSetting.setTextColor(Color.rgb(mRed.getProgress(),
						mGreen.getProgress(), mBlue.getProgress()));
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
