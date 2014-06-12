
package com.bj4.yhh.slideshow.animators;

import com.bj4.yhh.slideshow.SettingManager;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

public class AnimatorCreator {
    private static final void clearPreviousStatus(final View parent, final View main,
            final View shadow) {
        if (parent != null) {
            parent.setTranslationX(0);
            parent.setTranslationY(0);
            parent.setScaleX(1);
            parent.setScaleY(1);
            parent.setAlpha(1);
        }
        if (main != null) {
            main.setTranslationX(0);
            main.setTranslationY(0);
            main.setScaleX(1);
            main.setScaleY(1);
            main.setAlpha(1);
        }
        if (shadow != null) {
            shadow.setTranslationX(0);
            shadow.setTranslationY(0);
            shadow.setScaleX(1);
            shadow.setScaleY(1);
            shadow.setAlpha(1);
            shadow.setVisibility(View.INVISIBLE);
        }
    }

    public static AnimatorSet createAnimator(final View parent, final View main, final View shadow,
            final int w, final int h, final int duration, Context context) {
        SettingManager sm = SettingManager.getInstance(context);
        AnimatorSet rtn = null;
        clearPreviousStatus(parent, main, shadow);
        switch (sm.getAnimation()) {
            case 0:
                rtn = createSimpleHScrollAnimator(parent, main, shadow, w, h, duration);
                break;
            case 1:
                rtn = createReverseSimpleHScrollAnimator(parent, main, shadow, w, h, duration);
                break;
            case 2:
                rtn = createFlashAnimator(parent, main, shadow, w, h, duration);
                break;
            case 3:
                rtn = createHearBeatAnimator(parent, main, shadow, w, h, duration);
                break;
            case 4:
                rtn = createHearBeatWithShadowAnimator(parent, main, shadow, w, h, duration);
                break;
            case 5:
                rtn = createHIntersectAnimator(parent, main, shadow, w, h, duration);
                break;
            case 6:
                rtn = createVIntersectAnimator(parent, main, shadow, w, h, duration);
                break;
        }
        return rtn;
    }

    public static AnimatorSet createVIntersectAnimator(final View parent, final View main,
            final View shadow, final int w, final int h, final int duration) {
        AnimatorSet rtn = new AnimatorSet();
        ValueAnimator va = ValueAnimator.ofFloat(h, 0, 0, 0, -h);
        va.setRepeatMode(ValueAnimator.INFINITE);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setInterpolator(null);
        va.setDuration(duration);
        va.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (Float)animation.getAnimatedValue();
                if (main != null) {
                    main.setTranslationY(v);
                }
            }
        });
        ValueAnimator va1 = ValueAnimator.ofFloat(-h, 0, 0, 0, h);
        va1.setRepeatMode(ValueAnimator.INFINITE);
        va1.setRepeatCount(ValueAnimator.INFINITE);
        va1.setInterpolator(null);
        va1.setDuration(duration);
        va1.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (Float)animation.getAnimatedValue();
                if (shadow != null) {
                    shadow.setTranslationY(v);
                    shadow.setVisibility(View.VISIBLE);
                }
            }
        });

        rtn.playTogether(va, va1);
        return rtn;
    }

    public static AnimatorSet createHIntersectAnimator(final View parent, final View main,
            final View shadow, final int w, final int h, final int duration) {
        AnimatorSet rtn = new AnimatorSet();
        ValueAnimator va = ValueAnimator.ofFloat(w, 0, 0, 0, -w);
        va.setRepeatMode(ValueAnimator.INFINITE);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setInterpolator(null);
        va.setDuration(duration);
        va.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (Float)animation.getAnimatedValue();
                if (main != null) {
                    main.setTranslationX(v);
                }
            }
        });
        ValueAnimator va1 = ValueAnimator.ofFloat(-w, 0, 0, 0, w);
        va1.setRepeatMode(ValueAnimator.INFINITE);
        va1.setRepeatCount(ValueAnimator.INFINITE);
        va1.setInterpolator(null);
        va1.setDuration(duration);
        va1.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (Float)animation.getAnimatedValue();
                if (shadow != null) {
                    shadow.setTranslationX(v);
                    shadow.setVisibility(View.VISIBLE);
                }
            }
        });

        rtn.playTogether(va, va1);
        return rtn;
    }

    public static AnimatorSet createHearBeatWithShadowAnimator(final View parent, final View main,
            final View shadow, final int w, final int h, final int duration) {
        AnimatorSet rtn = new AnimatorSet();
        ValueAnimator va = ValueAnimator.ofFloat(1f, 3, 1);
        va.setRepeatMode(ValueAnimator.INFINITE);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setInterpolator(null);
        va.setDuration(duration);
        va.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (Float)animation.getAnimatedValue();
                if (main != null) {
                    main.setScaleX(v);
                    main.setScaleY(v);
                }
            }
        });
        ValueAnimator va1 = ValueAnimator.ofFloat(1f, 3, 1);
        va1.setRepeatMode(ValueAnimator.INFINITE);
        va1.setRepeatCount(ValueAnimator.INFINITE);
        va1.setInterpolator(null);
        va1.setDuration(duration);
        va1.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (Float)animation.getAnimatedValue();
                if (shadow != null) {
                    shadow.setScaleX(v);
                    shadow.setScaleY(v);
                    shadow.setVisibility(View.VISIBLE);
                }
            }
        });
        va1.setStartDelay(duration / 15);
        rtn.playTogether(va, va1);
        return rtn;
    }

    public static AnimatorSet createHearBeatAnimator(final View parent, final View main,
            final View shadow, final int w, final int h, final int duration) {
        AnimatorSet rtn = new AnimatorSet();
        ValueAnimator va = ValueAnimator.ofFloat(0.8f, 3, 2.2f, 1);
        va.setRepeatMode(ValueAnimator.INFINITE);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setInterpolator(null);
        va.setDuration(duration);
        va.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (Float)animation.getAnimatedValue();
                if (main != null) {
                    main.setScaleX(v);
                    main.setScaleY(v);
                }
            }
        });
        rtn.playTogether(va);
        return rtn;
    }

    public static AnimatorSet createFlashAnimator(final View parent, final View main,
            final View shadow, final int w, final int h, final int duration) {
        AnimatorSet rtn = new AnimatorSet();
        ValueAnimator va = ValueAnimator.ofInt(0, 1, 2, 3, 4);
        va.setRepeatMode(ValueAnimator.INFINITE);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setInterpolator(null);
        va.setDuration(duration / 20);
        va.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (Integer)animation.getAnimatedValue();
                if (parent != null) {
                    if (v < 1) {
                        parent.setBackgroundColor(Color.RED);
                    } else if (v < 2) {
                        parent.setBackgroundColor(Color.BLUE);
                    } else if (v < 3) {
                        parent.setBackgroundColor(Color.GREEN);
                    } else if (v < 4) {
                        parent.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });
        rtn.playTogether(va);
        return rtn;
    }

    public static AnimatorSet createReverseSimpleHScrollAnimator(final View parent,
            final View main, final View shadow, final int w, final int h, final int duration) {
        AnimatorSet rtn = new AnimatorSet();
        ValueAnimator va = ValueAnimator.ofInt(-w, w);
        va.setRepeatMode(ValueAnimator.INFINITE);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setInterpolator(null);
        va.setDuration(duration);
        va.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (Integer)animation.getAnimatedValue();
                if (main != null) {
                    main.setTranslationX(v);
                }
            }
        });
        rtn.playTogether(va);
        return rtn;
    }

    public static AnimatorSet createSimpleHScrollAnimator(final View parent, final View main,
            final View shadow, final int w, final int h, final int duration) {
        AnimatorSet rtn = new AnimatorSet();
        ValueAnimator va = ValueAnimator.ofInt(w, -w);
        va.setRepeatMode(ValueAnimator.INFINITE);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setInterpolator(null);
        va.setDuration(duration);
        va.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (Integer)animation.getAnimatedValue();
                if (main != null) {
                    main.setTranslationX(v);
                }
            }
        });
        rtn.playTogether(va);
        return rtn;
    }

}
