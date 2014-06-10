package com.bj4.yhh.slideshow;

import com.bj4.yhh.slideshow.settings.fragment.BackgroundColorFragment;
import com.bj4.yhh.slideshow.settings.fragment.DurationFragment;
import com.bj4.yhh.slideshow.settings.fragment.TextColorFragment;
import com.bj4.yhh.slideshow.settings.fragment.TextContentFragment;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends Activity {

	private ListView mSettingList;

	private SettingListAdapter mSettingListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initComponents();
	}

	private void initComponents() {
		mSettingList = (ListView) findViewById(R.id.setting_options_list);
		mSettingListAdapter = new SettingListAdapter(this);
		mSettingList.setAdapter(mSettingListAdapter);
		mSettingList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mSettingListAdapter.setSelectedItem(position);
				mSettingListAdapter.notifyDataSetChanged();
				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				switch (position) {
				case 0:
					break;
				case 1:
					transaction.replace(R.id.main_fragment_container,
							new TextContentFragment());
					transaction.commit();
					break;
				case 2:
					transaction.replace(R.id.main_fragment_container,
							new BackgroundColorFragment());
					transaction.commit();
					break;
				case 3:
					transaction.replace(R.id.main_fragment_container,
							new TextColorFragment());
					transaction.commit();
					break;
				case 4:
					break;
				case 5:
					break;
				case 7:
					transaction.replace(R.id.main_fragment_container,
							new DurationFragment());
					transaction.commit();
					break;
				}
			}
		});
	}
}
