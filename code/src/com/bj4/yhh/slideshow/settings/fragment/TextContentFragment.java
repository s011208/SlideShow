package com.bj4.yhh.slideshow.settings.fragment;

import com.bj4.yhh.slideshow.R;
import com.bj4.yhh.slideshow.SettingManager;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TextContentFragment extends Fragment {

	private View mMainContainer;
	private EditText mEditText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		mMainContainer = inflater.inflate(R.layout.text_content_fragment,
				container, false);
		final SettingManager setting = SettingManager.getInstance(getActivity());
		mEditText = (EditText) mMainContainer
				.findViewById(R.id.text_content_fragment_edittext);
		mEditText.setText(setting.getText());
		mEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				setting.setText(s.toString());
			}
		});
		return mMainContainer;
	}
}
