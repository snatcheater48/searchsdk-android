//
//  SearchBarView.java
//  YahooSearchShowcase
//
//  Copyright 2015 Yahoo! Inc.
//  Licensed under the terms of the zLib license. See LICENSE file at the root of this project for license terms.
//

package com.yahoo.android.search.showcase.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yahoo.android.search.showcase.R;

public class SearchBarView extends RelativeLayout {

    private String mDisplayText;
    private boolean isVoiceSearchEnabled;

    private ImageView mVoiceSearchImageView;
    private TextView mSearchTextView;

    public SearchBarView(Context context) {
        super(context);
    }

    public SearchBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SearchBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.SearchBarView, 0, 0);
        mDisplayText = a.getString(R.styleable.SearchBarView_displayText);
        isVoiceSearchEnabled = a.getBoolean(R.styleable.SearchBarView_voiceSearchEnabled, false);
        a.recycle();
        LayoutInflater.from(getContext()).inflate(R.layout.search_bar_view_layout, this, true);
        setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mVoiceSearchImageView = (ImageView) findViewById(R.id.demo_voice_search_imageview);
        mSearchTextView = (TextView) findViewById(R.id.demo_search_bar_textview);
        if (isVoiceSearchEnabled) {
            mVoiceSearchImageView.setVisibility(VISIBLE);
        } else {
            mVoiceSearchImageView.setVisibility(GONE);
        }
        if (!TextUtils.isEmpty(mDisplayText)) {
            mSearchTextView.setText(mDisplayText);
        }
    }
}
