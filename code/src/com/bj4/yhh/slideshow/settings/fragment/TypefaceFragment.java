
package com.bj4.yhh.slideshow.settings.fragment;

import com.bj4.yhh.slideshow.R;
import com.bj4.yhh.slideshow.SettingManager;
import com.bj4.yhh.slideshow.SlidingFonts;

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

public class TypefaceFragment extends Fragment {

    private View mMainContainer;

    private SettingManager mSetting;

    private GridView mGrid;

    private TypefaceGridAdapter mGridAdapter;

    private LayoutInflater mInflater;

    private Context mContext;

    private String mCurrentTypeface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflater = inflater;
        mContext = getActivity();
        mMainContainer = mInflater.inflate(R.layout.text_typeface_fragment, container, false);
        mSetting = SettingManager.getInstance(getActivity());
        mCurrentTypeface = mSetting.getTextStyle();
        mGrid = (GridView)mMainContainer.findViewById(R.id.type_face_grid);
        mGridAdapter = new TypefaceGridAdapter();
        mGrid.setAdapter(mGridAdapter);
        if ("".equals(mCurrentTypeface) == false) {
            int position = SlidingFonts.FONTS.indexOf(mCurrentTypeface);
            if (position != -1) {
                mGridAdapter.setSelected(position);
            }
        }
        mGrid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentTypeface = mGridAdapter.getItem(position);
                mSetting.setTextStyle(mCurrentTypeface);
                mGridAdapter.setSelected(position);
            }
        });
        return mMainContainer;
    }

    private class TypefaceGridAdapter extends BaseAdapter {

        private int mSelectPosition = -1, mPreviousSelectPosition = -1;

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return SlidingFonts.FONTS.size();
        }

        @Override
        public String getItem(int position) {
            // TODO Auto-generated method stub
            return SlidingFonts.FONTS.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public void setSelected(int position) {
            mPreviousSelectPosition = mSelectPosition;
            mSelectPosition = position;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.typeface_grid_row, null);
                holder = new ViewHolder();
                holder.mTxt = (TextView)convertView.findViewById(R.id.type_face_grid_row);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.mTxt.setTypeface(Typeface.createFromAsset(mContext.getAssets(),
                    getItem(position)));
            if (mSelectPosition == position) {
                ((FrameLayout)convertView).setForeground(mContext.getResources().getDrawable(
                        R.drawable.type_face_grid_row_fg));
            } else if (mPreviousSelectPosition == position) {
                ((FrameLayout)convertView).setForeground(null);
            }
            return convertView;
        }

        private class ViewHolder {
            TextView mTxt;
        }
    }
}
