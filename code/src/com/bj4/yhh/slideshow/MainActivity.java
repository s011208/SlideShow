
package com.bj4.yhh.slideshow;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
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
        mSettingList = (ListView)findViewById(R.id.setting_options_list);
        mSettingListAdapter = new SettingListAdapter(this);
        mSettingList.setAdapter(mSettingListAdapter);
        mSettingList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    mSettingListAdapter.setSelectedItem(position);
                    mSettingListAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
