//
//  MainApplication.java
//  YahooSearchShowcase
//
//  Copyright 2015 Yahoo! Inc.
//  Licensed under the terms of the zLib license. See LICENSE file at the root of this project for license terms.
//

package com.yahoo.android.search.showcase;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yahoo.mobile.client.share.search.location.SearchLocationManager;
import com.yahoo.mobile.client.share.search.settings.SearchSDKSettings;
import com.yahoo.mobile.client.share.search.util.SafeSearchEnum;

public class MyApplication extends Application {

    private static final String USE_YOUR_DEVELOPER_KEY_HERE = BuildConfig.developerAppId;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeSearchSDK();
        initImageLoader();
    }

    private void initializeSearchSDK() {
        SearchLocationManager locationManager = new SearchLocationManager(this);
        locationManager.requestUpdates();
        SearchSDKSettings
                .initializeSearchSDKSettings(
                        new SearchSDKSettings.Builder(USE_YOUR_DEVELOPER_KEY_HERE)
                                .setSafeSearch(SafeSearchEnum.STRICT)
                                .setVoiceSearchEnabled(true)
                                .setConsumptionModeEnabled(false)
                                .setSearchLocationManager(locationManager));
    }

    /**
     * Initializing UniversalImageLoader cache with standard parameters.
     * Please read more here: https://github.com/nostra13/Android-Universal-Image-Loader
     */
    private void initImageLoader() {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}