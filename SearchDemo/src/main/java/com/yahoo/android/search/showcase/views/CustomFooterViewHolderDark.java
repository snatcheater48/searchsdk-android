package com.yahoo.android.search.showcase.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yahoo.android.search.showcase.R;
import com.yahoo.android.search.showcase.Utils.Utils;
import com.yahoo.mobile.client.share.search.interfaces.ISearchVertical;
import com.yahoo.mobile.client.share.search.settings.SearchSDKSettings;

import java.util.List;


public class CustomFooterViewHolderDark extends CustomBaseFooterViewHolder {

    private List<ISearchVertical> mTabList;
    private int mTabWidth = 0;
    private View mTabIndicator;
    private Context mContext = null;

    public CustomFooterViewHolderDark(Context context) {
        super(context);
        mContext = context;
    }

    public CustomFooterViewHolderDark(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CustomFooterViewHolderDark(Context context, AttributeSet attrs, int defStyle) {
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
            LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.demo_search_tab_with_icon, null);
            TextView tv = (TextView) linearLayout.findViewById(R.id.tab_text);
            String tabName = mTabList.get(counter).getLabel(mContext);
            tv.setText(tabName);
            LayoutParams lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            tv.setOnClickListener(this);
            tv.setTag(index++);
            labelContainer.addView(linearLayout, lp);
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
        int tabSize = mTabList.size();
        if (mTabList != null) {
            for (int i = 0; i < tabSize; i++) {
                if (i == position) {
                    updateTabContent(i, getResources().getColor(R.color.demo_search_tab_text_selected_white), true);
                } else {
                    updateTabContent(i, getResources().getColor(R.color.demo_search_tab_text_unselected_white), false);
                }
            }
            updateIndicatorPosition(position);
        }
    }

    /**
     *  Method to update tab content.
     */

    private void updateTabContent(int position, int color, boolean selected) {

        LinearLayout labelContainer = (LinearLayout) findViewById(R.id.search_tab_label_container);
        LinearLayout linearLayout = (LinearLayout) labelContainer.getChildAt(position);
        TextView tv = (TextView) linearLayout.findViewById(R.id.tab_text);
        ImageView icon = (ImageView) linearLayout.findViewById(R.id.tab_icon);

        switch (getTabController().getTabType(position)) {
            case SearchSDKSettings.TYPE_WEB : icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_web));
                break;
            case SearchSDKSettings.TYPE_IMAGE : icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_image));
                break;
            case SearchSDKSettings.TYPE_VIDEO : icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_video));
                break;
            default: break;
        }

        if (linearLayout != null && tv != null) {
            tv.setTextColor(color);
            if (selected) {
                //tv.setTypeface(null, Typeface.BOLD);
                linearLayout.setBackgroundColor(getResources().getColor(R.color.demo_search_tab_dark_theme_selected_bg));
            } else {
                //tv.setTypeface(null, Typeface.NORMAL);
                linearLayout.setBackgroundColor(getResources().getColor(R.color.demo_search_tab_dark_theme_unselected_bg));
            }
        }

    }

    /**
     *  Use this method to highlight/customize tab indicator.
     */
    protected void updateIndicatorPosition(float position) {
        mTabIndicator.setTranslationX(position * mTabWidth);
    }

}
