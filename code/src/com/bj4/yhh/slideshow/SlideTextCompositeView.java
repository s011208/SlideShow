
package com.bj4.yhh.slideshow;

import com.bj4.yhh.slideshow.animators.AnimatorCreator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.RelativeLayout;

public class SlideTextCompositeView extends RelativeLayout implements
        SettingManager.TextSettingChangeCallback {
    private SlideText mMainText, mShadowText;

    private int mViewHeight, mViewWidth;

    private ValueAnimator mAnimator;

    private SettingManager mSettingManager;

    public SlideTextCompositeView(Context context) {
        this(context, null);
    }

    public SlideTextCompositeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideTextCompositeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mSettingManager = SettingManager.getInstance(context);
        onTextSettingChanged();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mSettingManager.registerCallback(this);
        if (mAnimator != null) {
            mAnimator.start();
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mSettingManager.unRegisterCallback(this);
        if (mAnimator != null) {
            mAnimator.cancel();
        }
    }

    private void initAnimator() {
        if (mViewHeight == 0 || mViewWidth == 0)
            return;
        mAnimator = AnimatorCreator.createSimpleHScrollAnimator(mMainText, mShadowText, mViewWidth,
                mViewHeight, mSettingManager.getDuration());
        mAnimator.start();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        mMainText = (SlideText)findViewById(R.id.main_text);
        mShadowText = (SlideText)findViewById(R.id.shadow_text);
        getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                mViewHeight = getHeight();
                mViewWidth = getWidth();
                initAnimator();
                return false;
            }
        });
    }

    public void setPreview(boolean isPreview) {
        if (mMainText != null) {
            mMainText.setPreview(isPreview);
        }
        if (mShadowText != null) {
            mShadowText.setPreview(isPreview);
        }
        onTextSettingChanged();
    }

    @Override
    public void onTextSettingChanged() {
        setBackgroundColor(mSettingManager.getBackgroundColor());
        if (mAnimator != null) {
            mAnimator.cancel();
            initAnimator();
        }
        if (mMainText != null) {
            mMainText.onTextSettingChanged();
        }
        if (mShadowText != null) {
            mShadowText.onTextSettingChanged();
        }
    }
}
