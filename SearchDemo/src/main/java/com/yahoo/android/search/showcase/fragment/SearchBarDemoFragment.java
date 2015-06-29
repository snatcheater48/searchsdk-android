//
//  SearchBarDemoFragment.java
//  YahooSearchShowcase
//
//  Copyright 2015 Yahoo! Inc.
//  Licensed under the terms of the zLib license. See LICENSE file at the root of this project for license terms.
//

package com.yahoo.android.search.showcase.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yahoo.android.search.showcase.R;
import com.yahoo.android.search.showcase.views.SearchBarView;
import com.yahoo.android.search.showcase.views.SearchSettingsView;

import com.yahoo.mobile.client.share.search.ui.activity.*;
import com.yahoo.mobile.client.share.search.settings.SearchSDKSettings;
import com.yahoo.mobile.client.share.search.ui.activity.SearchActivity;
import com.yahoo.mobile.client.share.search.ui.activity.TrendingSearchEnum;


public class SearchBarDemoFragment extends Fragment {

    private static final String TABS = "tabs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_bar_demo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // All Search Settings views
        SearchSettingsView searchSettingsAllEnabledView = (SearchSettingsView) view.findViewById(R.id.demo_search_settings_all_layout);
        SearchSettingsView searchSettingsImagesView = (SearchSettingsView) view.findViewById(R.id.demo_search_settings_images_layout);
        SearchSettingsView searchSettingsMediaView = (SearchSettingsView) view.findViewById(R.id.demo_search_settings_media_layout);

        // All Search Bar Views
        SearchBarView searchBarAllEnabledView = (SearchBarView) view.findViewById(R.id.demo_search_bar_all_layout);
        SearchBarView searchBarImagesView = (SearchBarView) view.findViewById(R.id.demo_search_bar_images_layout);
        SearchBarView searchBarMediaView = (SearchBarView) view.findViewById(R.id.demo_search_bar_media_layout);

        // All Search Setting preview buttons
        Button searchAllPreviewButton = (Button) searchSettingsAllEnabledView.findViewById(R.id.search_settings_preview_button);
        Button searchImagesPreviewButton = (Button) searchSettingsImagesView.findViewById(R.id.search_settings_preview_button);
        Button searchMediaPreviewButton = (Button) searchSettingsMediaView.findViewById(R.id.search_settings_preview_button);

        // Set search settings display view
        searchSettingsAllEnabledView.setSearchSettings(true, true, getResources().getString(R.string.demo_search_result_all));
        searchSettingsImagesView.setSearchSettings(true, true, getResources().getString(R.string.demo_search_result_images_only));
        searchSettingsMediaView.setSearchSettings(true, true, getResources().getString(R.string.demo_search_result_images_video));

        // Attach Search Bar View Click Listeners
        searchBarAllEnabledView.setOnClickListener(mSearchBarAllEnabledClickListener);
        searchBarImagesView.setOnClickListener(mSearchBarImagesOnlyClickListener);
        searchBarMediaView.setOnClickListener(mSearchBarAllMediaClickListener);

        // Attach Search Settings Button Preview listener (Same functionality as clicking on the Search Bar)
        searchAllPreviewButton.setOnClickListener(mSearchBarAllEnabledClickListener);
        searchImagesPreviewButton.setOnClickListener(mSearchBarImagesOnlyClickListener);
        searchMediaPreviewButton.setOnClickListener(mSearchBarAllMediaClickListener);
    }

    private final View.OnClickListener mSearchBarAllEnabledClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchAllEnabledSearch();
        }
    };

    private final View.OnClickListener mSearchBarAllMediaClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchMediaSearch();
        }
    };

    private final View.OnClickListener mSearchBarImagesOnlyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchImageSearch();
        }
    };

    /**
     * Launch regular search activity with all tabs
     */
    private void launchAllEnabledSearch() {
        SearchSDKSettings.setSearchSuggestEnabled(true);
        SearchActivity.IntentBuilder builder = new SearchActivity.IntentBuilder();
        //Trending suggestions are available with valid appID.
        builder.setTrendingCategory(TrendingSearchEnum.SPORTS);
        builder.addWebVertical();
        builder.addImageVertical();
        builder.addVideoVertical();
        builder.addLocalVertical();
        Intent i = builder.buildIntent(getActivity());
        startActivity(i);
    }

    /**
     * Launch Search Activity with Media Tabs (Images and Video)
     */
    private void launchMediaSearch() {
        SearchSDKSettings.setSearchSuggestEnabled(true);
        SearchActivity.IntentBuilder builder = new SearchActivity.IntentBuilder();
        builder.addImageVertical();
        builder.addVideoVertical();
        Intent i = builder.buildIntent(getActivity());
        startActivity(i);
    }

    /**
     * Launch Search Activity with only Images Tab
     */
    private void launchImageSearch() {
        SearchSDKSettings.setSearchSuggestEnabled(true);
        SearchActivity.IntentBuilder builder = new SearchActivity.IntentBuilder();
        builder.addImageVertical();
        Intent i = builder.buildIntent(getActivity());
        startActivity(i);
    }

}
