
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

public class TextSizeFragment extends Fragment implements OnSeekBarChangeListener {

    private View mMainContainer;

    private SeekBar mTextSize;

    private SettingManager mSetting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainContainer = inflater.inflate(R.layout.textsize_fragment, container, false);
        mSetting = SettingManager.getInstance(getActivity());
        mTextSize = (SeekBar)mMainContainer.findViewById(R.id.textsize_seek);
        final int currentTextSize = mSetting.getTextSize() - 1;
        mTextSize.setOnSeekBarChangeListener(this);
        mTextSize.setProgress(currentTextSize);
        return mMainContainer;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // TODO Auto-generated method stub
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                mSetting.setTextSize(mTextSize.getProgress() + 1);
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
