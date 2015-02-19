//
//  ContentSuggestDemoFragment.java
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
import com.yahoo.android.search.showcase.views.SearchSettingsView;
import com.yahoo.mobile.client.share.search.settings.SearchSDKSettings;
import com.yahoo.mobile.client.share.search.ui.activity.SearchActivity;

public class ContentSuggestDemoFragment extends Fragment {

    private static final String TABS = "tabs";
    private static final String CATS = "Cats";
    private static final String AUGMENTED_REALITY = "Augmented Reality";

    private SearchSettingsView mSearchSettingsAugmentedReality;
    private SearchSettingsView mSearchSettingsCatsView;
    private View mSearchBarAugmentedRealityView;
    private View mSearchBarCatsView;
    private Button mAugmentedRealityPreviewbutton;
    private Button mCatsImageSearchPreviewbutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content_suggest, container, false);
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        mSearchSettingsAugmentedReality = (SearchSettingsView) rootView.findViewById(R.id.content_suggest_augmented_reality_search_settings_layout);
        mSearchSettingsCatsView = (SearchSettingsView) rootView.findViewById(R.id.content_suggest_cats_settings_layout);
        mSearchBarAugmentedRealityView = rootView.findViewById(R.id.demo_search_bar_augmented_reality_imageview);
        mSearchBarCatsView = rootView.findViewById(R.id.demo_search_bar_cats_imageview);
        mAugmentedRealityPreviewbutton = (Button) mSearchSettingsAugmentedReality.findViewById(R.id.search_settings_preview_button);
        mCatsImageSearchPreviewbutton = (Button) mSearchSettingsCatsView.findViewById(R.id.search_settings_preview_button);

        // Update the search settings view
        mSearchSettingsAugmentedReality.setSearchSettings(false, true, getResources().getString(R.string.demo_search_result_all));
        mSearchSettingsCatsView.setSearchSettings(false, true, getResources().getString(R.string.demo_search_result_images_only));
        // Set Click listeners to launch Search
        mSearchBarAugmentedRealityView.setOnClickListener(mAugmentedRealitySearchBarClickListener);
        mSearchBarCatsView.setOnClickListener(mCatsSearchBarClickListener);
        mAugmentedRealityPreviewbutton.setOnClickListener(mAugmentedRealitySearchBarClickListener);
        mCatsImageSearchPreviewbutton.setOnClickListener(mCatsSearchBarClickListener);
    }

    private final View.OnClickListener mAugmentedRealitySearchBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchSearchForAugmentedReality();
        }
    };

    private final View.OnClickListener mCatsSearchBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchImageSearchForCats();
        }
    };

    /**
     * Search for Predefined query
     */
    private void launchSearchForAugmentedReality() {
        SearchSDKSettings.setSearchSuggestEnabled(false);
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        // Search for a predefined query "Augmented Reality" with all tabs enabled
        intent.putExtra(SearchActivity.QUERY_STRING, AUGMENTED_REALITY);
        startActivity(intent);
    }

    /**
     * Launch Image Search for Predefined query "Cats"
     */
    private void launchImageSearchForCats() {
        SearchSDKSettings.setSearchSuggestEnabled(false);
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        // Search for predefined query "Cats"
        intent.putExtra(SearchActivity.QUERY_STRING, CATS);
        // Show only the Images tab for the search query
        intent.putExtra(TABS, SearchSDKSettings.TYPE_IMAGE);
        startActivity(intent);
    }

}
