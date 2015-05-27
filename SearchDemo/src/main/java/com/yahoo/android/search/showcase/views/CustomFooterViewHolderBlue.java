package com.yahoo.android.search.showcase.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yahoo.android.search.showcase.R;
import com.yahoo.android.search.showcase.Utils.Utils;
import com.yahoo.mobile.client.share.search.interfaces.ISearchVertical;

import java.util.List;


public class CustomFooterViewHolderBlue extends CustomBaseFooterViewHolder {

    private List<ISearchVertical> mTabList;
    private int mTabWidth = 0;
    private View mTabIndicator;
    private Context mContext = null;

    public CustomFooterViewHolderBlue(Context context) {
        super(context);
        mContext = context;
    }

    public CustomFooterViewHolderBlue(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CustomFooterViewHolderBlue(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    @Override
    protected void createTabBar(List<ISearchVertical> tabs) {
        mTabList = tabs;
        setCustomTabList(tabs);
        LayoutInflater inflater =  (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout labelContainer = (LinearLayout) findViewById(R.id.search_tab_label_container);
        labelContainer.removeAllViews();
        int index = 0;
        for (int counter = 0; counter < mTabList.size(); counter++) {
            TextView tv = (TextView) inflater.inflate(R.layout.demo_search_tab, null);
            String tabName = mTabList.get(counter).getLabel(mContext);
            tv.setText(tabName);
            LayoutParams lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            tv.setOnClickListener(this);
            tv.setTag(index++);
            labelContainer.addView(tv, lp);
        }

        mTabWidth = Utils.getScreenWidth(getContext()) / labelContainer.getChildCount();

        mTabIndicator = findViewById(R.id.search_tab_indicator);
        ViewGroup.LayoutParams lp = mTabIndicator.getLayoutParams();
        lp.width = mTabWidth;
        mTabIndicator.setLayoutParams(lp);

        // If there is only one tab, don't show the tab bar.
        // We're changing the height to 0 instead of setting visibility, because the
        // visibility of the container is often changed in core sdk.
        if (mTabList.size() == 1) {
            setTabContainerHeight(0);
        }
    }

    @Override
    protected void setSelectedIndex(int position) {
        if (mTabList != null) {
            for (int i = 0; i < mTabList.size(); i++) {
                if (i == position) {
                    updateTabTextColor(i, getResources().getColor(R.color.demo_search_tab_text_selected_blue), true);
                } else {
                    updateTabTextColor(i, getResources().getColor(R.color.demo_search_tab_text_selected_blue), false);
                }
            }
            updateIndicatorPosition(position);
        }
    }

    /**
     *  Method to update tab text color.
     */

    private void updateTabTextColor(int position, int color, boolean selected) {
        LinearLayout labelContainer = (LinearLayout) findViewById(R.id.search_tab_label_container);
        TextView tv = (TextView) labelContainer.getChildAt(position);
        if (tv != null) {
            tv.setTextColor(color);
            if (selected) {
                tv.setTypeface(null, Typeface.BOLD);
                tv.setBackgroundColor(getResources().getColor(R.color.demo_search_tab_blue_theme_selected_bg));
            } else {
                tv.setTypeface(null, Typeface.NORMAL);
                tv.setBackgroundColor(getResources().getColor(R.color.demo_search_tab_blue_theme_unselected_bg));
            }
        }

    }

    /**
     * Use this method to highlight/customize tab indicator.
     */
    protected void updateIndicatorPosition(float position) {
        mTabIndicator.setTranslationX(position * mTabWidth);
    }

}
