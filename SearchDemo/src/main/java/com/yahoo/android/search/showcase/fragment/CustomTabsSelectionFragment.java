package com.yahoo.android.search.showcase.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yahoo.android.search.showcase.R;
import com.yahoo.mobile.client.share.search.settings.SearchSDKSettings;
import com.yahoo.mobile.client.share.search.ui.activity.SearchActivity;
import com.yahoo.mobile.client.share.search.ui.activity.TrendingSearchEnum;

public class CustomTabsSelectionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_custom_tabs_selection, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView twoTabs = (ImageView) view.findViewById(R.id.demo_two_tabs);
        ImageView fourTabs = (ImageView) view.findViewById(R.id.demo_four_tabs);

        twoTabs.setOnClickListener(mTwoCustomTabsClickListener);
        fourTabs.setOnClickListener(mFourCustomTabsClickListener);
    }

    private final View.OnClickListener mTwoCustomTabsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchTwoCustomTabs();
        }
    };

    private final View.OnClickListener mFourCustomTabsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchFourCustomTabs();
        }
    };

    /**
     *  Tumblr + Web
     */

    private void launchTwoCustomTabs() {
        SearchSDKSettings.setSearchSuggestEnabled(true);
        SearchActivity.IntentBuilder builder = new SearchActivity.IntentBuilder();
        builder.setQueryString("gif");
        //Trending suggestions are available with valid appID.
        builder.setTrendingCategory(TrendingSearchEnum.SPORTS);
        builder.addVertical(CustomTumblrFragment.class.getName(), new Bundle());
        builder.addWebVertical();
        builder.setCustomHeader(R.layout.search_view_custom_header_tumblr);
        builder.setCustomFooter(R.layout.search_view_custom_footer_tumblr);
        Intent i = builder.buildIntent(getActivity());
        startActivity(i);
    }

    /**
     *  Web + Images + Videos + Tumblr
     */

    private void launchFourCustomTabs() {
        SearchSDKSettings.setSearchSuggestEnabled(true);
        SearchActivity.IntentBuilder builder = new SearchActivity.IntentBuilder();
        builder.setQueryString("yahoo");
        //Trending suggestions are available with valid appID.
        builder.setTrendingCategory(TrendingSearchEnum.SPORTS);
        builder.addWebVertical();
        builder.addImageVertical();
        builder.addVideoVertical();
        builder.addVertical(CustomTumblrFragment.class.getName(), new Bundle());
        builder.setCustomHeader(R.layout.search_view_custom_header_tumblr);
        Intent i = builder.buildIntent(getActivity());
        startActivity(i);
    }
}
