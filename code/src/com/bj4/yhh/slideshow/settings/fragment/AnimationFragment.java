
package com.bj4.yhh.slideshow.settings.fragment;

import com.bj4.yhh.slideshow.SettingManager;
import com.bj4.yhh.slideshow.SlideTextCompositeView;
import com.bj4.yhh.slideshow.SlidingFonts;
import com.yenhsun.slidingshow.R;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class AnimationFragment extends Fragment {

    private View mMainContainer;

    private SettingManager mSetting;

    private GridView mGrid;

    private AnimationGridAdapter mGridAdapter;

    private LayoutInflater mInflater;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflater = inflater;
        mContext = getActivity();
        mMainContainer = mInflater.inflate(R.layout.animation_fragment, container, false);
        mSetting = SettingManager.getInstance(getActivity());
        mGrid = (GridView)mMainContainer.findViewById(R.id.animation_grid);
        mGridAdapter = new AnimationGridAdapter();
        mGrid.setAdapter(mGridAdapter);
        mGridAdapter.setSelected(mSetting.getAnimation());
        mGrid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSetting.setAnimation(position);
                mGridAdapter.setSelected(position);
            }
        });
        return mMainContainer;
    }

    private class AnimationGridAdapter extends BaseAdapter {

        private int mSelectPosition = -1;

        private final String[] mData;

        public AnimationGridAdapter() {
            mData = mContext.getResources().getStringArray(R.array.animation_options);
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

        public void setSelected(int position) {
            mSelectPosition = position;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.animation_grid_row, null);
                holder = new ViewHolder();
                holder.mTxt = (TextView)convertView.findViewById(R.id.animation_grid_row);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.mTxt.setText(getItem(position));
            if (mSelectPosition == position) {
                ((FrameLayout)convertView).setForeground(mContext.getResources().getDrawable(
                        R.drawable.type_face_grid_row_fg));
                holder.mHasChangeBg = true;
            } else {
                if (holder.mHasChangeBg) {
                    ((FrameLayout)convertView).setForeground(null);
                    holder.mHasChangeBg = false;
                }
            }
            return convertView;
        }

        private class ViewHolder {
            TextView mTxt;

            boolean mHasChangeBg = false;
        }
    }
}
