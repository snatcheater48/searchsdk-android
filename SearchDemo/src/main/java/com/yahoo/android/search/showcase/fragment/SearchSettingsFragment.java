//
//  SearchSettingsFragment.java
//  YahooSearchShowcase
//
//  Copyright 2015 Yahoo! Inc.
//  Licensed under the terms of the zLib license. See LICENSE file at the root of this project for license terms.
//

package com.yahoo.android.search.showcase.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import com.yahoo.android.search.showcase.R;
import com.yahoo.mobile.client.share.search.settings.SearchSDKSettings;
import com.yahoo.mobile.client.share.search.ui.activity.SearchActivity;

public class SearchSettingsFragment extends Fragment {

    private static final String TABS = "tabs";

    private Switch mSearchAssistSwitch;
    private CheckBox mWebSearchCheckbox;
    private CheckBox mImageSearchCheckbox;
    private CheckBox mVideoSearchCheckbox;
    private Button mPreviewSearchButton;
    private EditText mAutoFillSearchEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_settings, container, false);
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        mSearchAssistSwitch = (Switch) rootView.findViewById(R.id.search_settings_assist_switch);
        mWebSearchCheckbox = (CheckBox) rootView.findViewById(R.id.search_settings_web_checkbox);
        mImageSearchCheckbox = (CheckBox) rootView.findViewById(R.id.search_settings_image_checkbox);
        mVideoSearchCheckbox = (CheckBox) rootView.findViewById(R.id.search_settings_video_checkbox);
        mPreviewSearchButton = (Button) rootView.findViewById(R.id.search_settings_preview_button);
        mAutoFillSearchEditText = (EditText) rootView.findViewById(R.id.search_settings_content_suggest_edittext);
        mPreviewSearchButton.setOnClickListener(mSearchBarClickListener);
    }

    private View.OnClickListener mSearchBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchSearchWithCustomizedSettings();
        }
    };

    private void launchSearchWithCustomizedSettings() {
        // Enable/Disable Search Assist based on switch setting
        SearchSDKSettings.setSearchSuggestEnabled(mSearchAssistSwitch.isChecked());

        SearchActivity.IntentBuilder builder = new SearchActivity.IntentBuilder();

        // Check what tabs need to be displayed
        int tabsToDisplay = 0;
        if (mWebSearchCheckbox.isChecked()) {
            builder.addWebVertical();
        }
        if (mImageSearchCheckbox.isChecked()) {
            builder.addImageVertical();
        }
        if (mVideoSearchCheckbox.isChecked()) {
            builder.addVideoVertical();
        }

        String autoFillSearchQuery = mAutoFillSearchEditText.getText().toString();

        // Default tabs if none of the boxes are checked
        if (tabsToDisplay != 0) {
            builder.addWebVertical();
            builder.addImageVertical();
            builder.addVideoVertical();
        }

        if (!TextUtils.isEmpty(autoFillSearchQuery)) {
            // Search for predefined query
            builder.setQueryString(autoFillSearchQuery);
        }

        Intent i = builder.buildIntent(getActivity());
        startActivity(i);
    }

}
