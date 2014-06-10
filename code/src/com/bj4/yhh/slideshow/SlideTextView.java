
package com.bj4.yhh.slideshow;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class SlideTextView extends TextView implements SettingManager.TextSettingChangeCallback {
    private Context mContext;

    private SettingManager mSettingManager;

    public SlideTextView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public SlideTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public SlideTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        mSettingManager = SettingManager.getInstance(context);
        setTextContent();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mSettingManager.registerCallback(this);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mSettingManager.unRegisterCallback(this);
    }

    private void setTextContent() {
        setTextColor(mSettingManager.getTextColor());
        setBackgroundColor(mSettingManager.getBackgroundColor());
        setText(mSettingManager.getText());
    }

    @Override
    public void onTextSettingChanged() {
        setTextContent();
    }

}
