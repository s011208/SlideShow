
package com.bj4.yhh.slideshow;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SettingListAdapter extends BaseAdapter {
    private final String[] mData;

    private Context mContext;

    private LayoutInflater mInflater;

    private int mSelectedItem = -1;

    public void setSelectedItem(int position) {
        mSelectedItem = position;
    }

    public SettingListAdapter(Context context) {
        mContext = context;
        mData = mContext.getResources().getStringArray(R.array.setting_options);
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.length;
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return mData[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.setting_option_row, null);
            holder = new ViewHolder();
            holder.mSettingOption = (TextView)convertView.findViewById(R.id.setting_option);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.mSettingOption.setText(getItem(position));
        if (position == 0) {
        } else if (position == mSelectedItem) {
            holder.mSettingOption.setBackgroundColor(mContext.getResources().getColor(
                    R.color.setting_list_divider));
            holder.mSettingOption.setSelected(true);
        } else {
            holder.mSettingOption.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }

    private class ViewHolder {
        TextView mSettingOption;
    }
}
