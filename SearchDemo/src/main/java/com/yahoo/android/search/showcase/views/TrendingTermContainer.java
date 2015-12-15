package com.yahoo.android.search.showcase.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yahoo.mobile.client.android.yahoosearchlibrary.R;
import com.yahoo.mobile.client.share.search.interfaces.ITrendingViewItemHolder;

public class TrendingTermContainer extends LinearLayout implements ITrendingViewItemHolder {
    public TrendingTermContainer(Context context) {
        super(context);
    }

    public TrendingTermContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TrendingTermContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public TextView getTextView() {
        return (TextView) findViewById(R.id.yssdk_trending_item_text);
    }
}