//
//  SearchDemoFragmentFactory.java
//  YahooSearchShowcase
//
//  Copyright 2015 Yahoo! Inc.
//  Licensed under the terms of the zLib license. See LICENSE file at the root of this project for license terms.
//

package com.yahoo.android.search.showcase;


import android.app.Fragment;

import com.yahoo.android.search.showcase.fragment.ContentSuggestDemoFragment;
import com.yahoo.android.search.showcase.fragment.SearchBarDemoFragment;
import com.yahoo.android.search.showcase.fragment.SearchSettingsFragment;
import com.yahoo.android.search.showcase.fragment.SearchToLinkFragment;
import com.yahoo.android.search.showcase.fragment.ThemeSelectionFragment;

public class SearchDemoFragmentFactory {

    public static Fragment getFragment(int id) {
        switch (id) {
            case 1:
                SearchBarDemoFragment fragment = new SearchBarDemoFragment();
                return fragment;
            case 2:
                ContentSuggestDemoFragment contentSuggestDemoFragment = new ContentSuggestDemoFragment();
                return contentSuggestDemoFragment;
            case 3:
                SearchToLinkFragment searchToLinkFragment = new SearchToLinkFragment();
                return searchToLinkFragment;
            case 4:
                ThemeSelectionFragment themeSelectionFragment = new ThemeSelectionFragment();
                return themeSelectionFragment;
            case 5:
                SearchSettingsFragment searchSettingsFragment = new SearchSettingsFragment();
                return searchSettingsFragment;
            default:
                Fragment placeHolderFragment = new Fragment();
                return placeHolderFragment;
        }
    }
}
