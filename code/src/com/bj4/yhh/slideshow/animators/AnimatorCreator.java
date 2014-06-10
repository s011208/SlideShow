package com.bj4.yhh.slideshow.animators;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;

public class AnimatorCreator {

	public static ValueAnimator createSimpleHScrollAnimator(final View main,
			final View shadow, final int w, final int h, final int duration) {
		ValueAnimator rtn = ValueAnimator.ofInt(w, -w);
		rtn.setRepeatMode(ValueAnimator.INFINITE);
		rtn.setRepeatCount(ValueAnimator.INFINITE);
		rtn.setInterpolator(null);
		rtn.setDuration(duration);
		rtn.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int v = (int) animation.getAnimatedValue();
				if (main != null) {
					main.setTranslationX(v);
				}
			}
		});
		return rtn;
	}

}
