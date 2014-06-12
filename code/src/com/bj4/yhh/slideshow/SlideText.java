
package com.bj4.yhh.slideshow;

import com.yenhsun.slidingshow.R;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.TextView;
import android.widget.Toast;

public class SlideText extends TextView implements SettingManager.TextSettingChangeCallback {
    private Context mContext;

    private SettingManager mSettingManager;

    private boolean mIsPreview = false;

    public SlideText(Context context) {
        this(context, null);
    }

    public SlideText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        mSettingManager = SettingManager.getInstance(context);
        setTextContent();
        setMaxLines(3);
        setBackgroundColor(Color.TRANSPARENT);
    }

    public void setPreview(boolean isPreview) {
        mIsPreview = isPreview;
    }

    private void setTextContent() {
        setTextColor(mSettingManager.getTextColor());
        setText(mSettingManager.getText());
        if (mIsPreview == false)
            setTextSize(mSettingManager.getTextSize());
        else {
            setTextSize(mContext.getResources().getDimension(R.dimen.preview_text_size));
        }
        String textStyle = mSettingManager.getTextStyle();
        if ("".equals(textStyle) == false)
            setTypeface(Typeface.createFromAsset(mContext.getAssets(), textStyle));
    }

    @Override
    public void onTextSettingChanged() {
        setTextContent();
    }

}
