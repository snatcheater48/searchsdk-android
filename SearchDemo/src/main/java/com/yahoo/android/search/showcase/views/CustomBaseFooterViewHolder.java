package com.yahoo.android.search.showcase.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yahoo.mobile.client.share.search.interfaces.IFooterViewHolder;
import com.yahoo.mobile.client.share.search.interfaces.ITabController;
import java.util.List;

public abstract class CustomBaseFooterViewHolder extends LinearLayout implements IFooterViewHolder, View.OnClickListener {

    public static final float EPS = 0.0001f;
    private ITabController mTabController;
    private List<String> mTabList;
    private int currentTab;

    public CustomBaseFooterViewHolder(Context context) {
        super(context);
    }

    public CustomBaseFooterViewHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomBaseFooterViewHolder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /** ITabViewHolder methods. Required methods that implement Interface from SDK. */

    @Override
    public void setTabController(ITabController tabController) {
        mTabController = tabController;
    }

    @Override
    public void setTabs(List<String> tabs) {
        createTabBar(tabs);
    }

    @Override
    public void addTab(String tab) {
        mTabList.add(tab);
        createTabBar(mTabList);
    }

    @Override
    public void onTabChanged(float position) {
        int intValue = (int) position;
        //if it is almost an integer value
        if (Math.abs(position - intValue) < EPS) {
            setSelectedIndex(intValue);
        }
    }

    /** Method for onClick behavior of tab */

    @Override
    public void onClick(View v) {
        if(v instanceof TextView) {
            String tabName = ((TextView) v).getText().toString();
            currentTab = mTabList.indexOf(tabName);
            if(currentTab > -1) {
                mTabController.changeTab(currentTab);
            }
        }
    }

    /**
     * Optional custom methods.
     *
     */

    public void setCustomTabList(List<String> tabList) {
        mTabList = tabList;
    }

    protected ITabController getTabController() {
        return mTabController;
    }

    protected void createTabBar(List<String> tabs) {}

    protected void setTabContainerHeight(int height) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = height;
        setLayoutParams(params);
    }

    protected void setSelectedIndex(int position) {}

}
