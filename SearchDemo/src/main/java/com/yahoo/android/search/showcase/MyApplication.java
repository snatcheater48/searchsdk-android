//
//  MainApplication.java
//  YahooSearchShowcase
//
//  Copyright 2015 Yahoo! Inc.
//  Licensed under the terms of the zLib license. See LICENSE file at the root of this project for license terms.
//

package com.yahoo.android.search.showcase;

import android.app.Application;

import com.yahoo.mobile.client.share.search.settings.SearchSDKSettings;
import com.yahoo.mobile.client.share.search.util.SafeSearchEnum;

public class MyApplication extends Application {

    private static final String USE_YOUR_DEVELOPER_KEY_HERE = "invalid";

    @Override
    public void onCreate() {
        super.onCreate();
        initializeSearchSDK();
    }

    private void initializeSearchSDK() {
        SearchSDKSettings
                .initializeSearchSDKSettings(
                        new SearchSDKSettings.Builder(USE_YOUR_DEVELOPER_KEY_HERE)
                                .setSafeSearch(SafeSearchEnum.STRICT)
                                .setVoiceSearchEnabled(true));
    }
}