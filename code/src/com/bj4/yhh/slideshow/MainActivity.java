
package com.bj4.yhh.slideshow;

import com.bj4.yhh.slideshow.settings.fragment.*;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends Activity {

    private ListView mSettingList;

    private SettingListAdapter mSettingListAdapter;

    private SlideTextCompositeView mSlideTextCompositeView;

    private FrameLayout mAdmobView;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SlidingFonts.initIfNeeded(this);
        initComponents();
        initAd();
    }

    public void onResume() {
        super.onResume();
        mAdmobView.setVisibility(View.VISIBLE);
        mAdView.resume();
    }

    public void onPause() {
        super.onPause();
        mAdView.pause();
    }

    public void onDestroy() {
        super.onDestroy();
        mAdView.destroy();
    }

    private void initAd() {
        mAdmobView = (FrameLayout)findViewById(R.id.ad_mob);
        final ImageView closeAd = (ImageView)findViewById(R.id.ad_view_close_btn);
        closeAd.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        closeAd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mAdmobView != null) {
                    mAdmobView.setVisibility(View.GONE);
                }
            }
        });
        closeAd.setVisibility(View.GONE);
        mAdView = new AdView(this);
        mAdView.setAdUnitId("ca-app-pub-6361389364792908/7622482621");
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdmobView.addView(mAdView);
        mAdView.loadAd(new AdRequest.Builder().build());
        mAdView.setAdListener(new AdListener() {
            public void onAdLoaded() {
                closeAd.setVisibility(View.VISIBLE);
                closeAd.bringToFront();
            }
        });
    }

    private void initComponents() {
        mSlideTextCompositeView = (SlideTextCompositeView)findViewById(R.id.main_preview);
        mSlideTextCompositeView.setPreview(true);
        mSettingList = (ListView)findViewById(R.id.setting_options_list);
        mSettingListAdapter = new SettingListAdapter(this);
        mSettingList.setAdapter(mSettingListAdapter);
        mSettingList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSettingListAdapter.setSelectedItem(position);
                mSettingListAdapter.notifyDataSetChanged();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, SlideActivity.class));
                        break;
                    case 1:
                        transaction
                                .replace(R.id.main_fragment_container, new TextContentFragment());
                        transaction.commit();
                        break;
                    case 2:
                        transaction.replace(R.id.main_fragment_container,
                                new BackgroundColorFragment());
                        transaction.commit();
                        break;
                    case 3:
                        transaction.replace(R.id.main_fragment_container, new TextColorFragment());
                        transaction.commit();
                        break;
                    case 4:
                        transaction.replace(R.id.main_fragment_container, new TextSizeFragment());
                        transaction.commit();
                        break;
                    case 5:
                        transaction.replace(R.id.main_fragment_container, new TypefaceFragment());
                        transaction.commit();
                        break;
                    case 6:
                        transaction.replace(R.id.main_fragment_container, new DurationFragment());
                        transaction.commit();
                        break;
                    case 7:
                        transaction.replace(R.id.main_fragment_container, new DurationFragment());
                        transaction.commit();
                        break;
                }
            }
        });
    }
}
