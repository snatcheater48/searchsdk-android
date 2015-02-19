//
//  SearchSettingsView.java
//  YahooSearchShowcase
//
//  Copyright 2015 Yahoo! Inc.
//  Licensed under the terms of the zLib license. See LICENSE file at the root of this project for license terms.
//

package com.yahoo.android.search.showcase.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yahoo.android.search.showcase.R;

public class SearchSettingsView extends RelativeLayout {

    private TextView mSearchAssistStatusTextView;
    private TextView mVoiceSearchStatusTextView;
    private TextView mSearchResultStatusTextView;

    public SearchSettingsView(Context context) {
        super(context);
        init();
    }

    public SearchSettingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchSettingsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.search_settings_layout, this, true);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mSearchAssistStatusTextView = (TextView) findViewById(R.id.search_assist_status_textview);
        mVoiceSearchStatusTextView = (TextView) findViewById(R.id.voice_search_status_textview);
        mSearchResultStatusTextView = (TextView) findViewById(R.id.search_results_status_textview);
    }

    public void setSearchSettings(boolean isSearchAssistEnabled, boolean isVoiceSearchEnabled, String searchResultStatusType) {
        mSearchAssistStatusTextView.setText(isSearchAssistEnabled ? R.string.demo_on :R.string.demo_off);
        mVoiceSearchStatusTextView.setText(isVoiceSearchEnabled ? R.string.demo_on :R.string.demo_off);
        mSearchResultStatusTextView.setText(searchResultStatusType);
    }
}
