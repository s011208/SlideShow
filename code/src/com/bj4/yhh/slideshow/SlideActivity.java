
package com.bj4.yhh.slideshow;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.yenhsun.slidingshow.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SlideActivity extends Activity {

    private InterstitialAd mInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.slide_text_composite_activity);
        mInterstitial = new InterstitialAd(this);
        mInterstitial.setAdUnitId("ca-app-pub-6361389364792908/4404880624");
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitial.loadAd(adRequest);
    }

    public void onBackPressed() {
        if (mInterstitial.isLoaded()) {
            mInterstitial.show();
        }
        super.onBackPressed();
    }

}
