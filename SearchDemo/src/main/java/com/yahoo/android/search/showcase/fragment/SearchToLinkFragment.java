//
//  SearchToLinkFragment.java
//  YahooSearchShowcase
//
//  Copyright 2015 Yahoo! Inc.
//  Licensed under the terms of the zLib license. See LICENSE file at the root of this project for license terms.
//

package com.yahoo.android.search.showcase.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.yahoo.android.search.showcase.R;
import com.yahoo.android.search.showcase.views.SearchSettingsView;
import com.yahoo.mobile.client.share.search.settings.SearchSDKSettings;
import com.yahoo.mobile.client.share.search.ui.activity.SearchToLinkActivity;
import com.yahoo.mobile.client.share.search.ui.activity.TrendingSearchEnum;

public class SearchToLinkFragment extends Fragment {

    private static final String TAG = SearchToLinkFragment.class.getSimpleName();
    private static final int REQUEST_CODE_SEARCH_TO_LINK = 100;
    private static final String SEPARATOR = " : ";
    private static final String NEW_LINE = "\n \n";

    private SearchSettingsView mSearchForALinkSettingsView;
    private SearchSettingsView mSearchForAnImageSettingsView;
    private SearchSettingsView mSearchForAVideoSettingsView;
    private Button mSearchForLinkPreviewButton;
    private Button mSearchForImagesPreviewButton;
    private Button mSearchForVideoPreviewbutton;
    private TextView mSearchToShareResultTextView;
    private ImageView mSelectedImagePreview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_to_link, container, false);
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        // Get all search setting display views
        mSearchForALinkSettingsView = (SearchSettingsView) rootView.findViewById(R.id.search_for_link_settings_view);
        mSearchForAnImageSettingsView = (SearchSettingsView) rootView.findViewById(R.id.search_for_image_settings_view);
        mSearchForAVideoSettingsView = (SearchSettingsView) rootView.findViewById(R.id.search_for_video_settings_view);
        mSearchForLinkPreviewButton = (Button) mSearchForALinkSettingsView.findViewById(R.id.search_settings_preview_button);
        mSearchForImagesPreviewButton = (Button) mSearchForAnImageSettingsView.findViewById(R.id.search_settings_preview_button);
        mSearchForVideoPreviewbutton = (Button) mSearchForAVideoSettingsView.findViewById(R.id.search_settings_preview_button);
        mSearchToShareResultTextView = (TextView) rootView.findViewById(R.id.search_to_share_result_textview);
        mSelectedImagePreview = (ImageView) rootView.findViewById(R.id.search_to_link_preview_imageview);

        // Update the View to display the correct search settings
        mSearchForALinkSettingsView.setSearchSettings(true, true, getResources().getString(R.string.demo_search_result_all));
        mSearchForAnImageSettingsView.setSearchSettings(true, true, getResources().getString(R.string.demo_search_result_images_only));
        mSearchForAVideoSettingsView.setSearchSettings(true, true, getResources().getString(R.string.demo_search_result_video_only));

        // Assign preview button click listeners
        mSearchForLinkPreviewButton.setOnClickListener(mSearchForLinkClickListener);
        mSearchForImagesPreviewButton.setOnClickListener(mSearchForImageClickListener);
        mSearchForVideoPreviewbutton.setOnClickListener(mSearchForVideoClickListener);
    }

    private final View.OnClickListener mSearchForLinkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchSearchForLinkWebActivity();
        }
    };

    private final View.OnClickListener mSearchForImageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchSearchforImageActivity();
        }
    };

    private final View.OnClickListener mSearchForVideoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchSearchforVideoActivity();
        }
    };

    /**
     * Launch the  {@link SearchToLinkActivity} for Web result
     */
    private void launchSearchForLinkWebActivity() {
        SearchSDKSettings.setSearchSuggestEnabled(true);
        SearchToLinkActivity.IntentBuilder builder = new SearchToLinkActivity.IntentBuilder();
        //Trending suggestions are available with valid appID.
        builder.setTrendingCategory(TrendingSearchEnum.DEFAULT);
        builder.addWebVertical();
        Intent i = builder.buildIntent(getActivity());
        startActivityForResult(i, REQUEST_CODE_SEARCH_TO_LINK);
    }

    /**
     * Launch the  {@link SearchToLinkActivity} for an Image result
     */
    private void launchSearchforImageActivity() {
        SearchSDKSettings.setSearchSuggestEnabled(true);
        SearchToLinkActivity.IntentBuilder builder = new SearchToLinkActivity.IntentBuilder();
        builder.addImageVertical();
        Intent i = builder.buildIntent(getActivity());
        startActivityForResult(i, REQUEST_CODE_SEARCH_TO_LINK);
    }

    /**
     * Launch the  {@link SearchToLinkActivity} for Video result
     */
    private void launchSearchforVideoActivity() {
        SearchSDKSettings.setSearchSuggestEnabled(true);
        SearchToLinkActivity.IntentBuilder builder = new SearchToLinkActivity.IntentBuilder();
        builder.addVideoVertical();
        Intent i = builder.buildIntent(getActivity());
        startActivityForResult(i, REQUEST_CODE_SEARCH_TO_LINK);
    }

    // Search to Link Result Handling
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_CANCELED) {
            if (data != null && data.hasExtra(SearchToLinkActivity.SHARE_ERROR_CODE)) {
                SearchToLinkActivity errorCode = (SearchToLinkActivity) data.getExtras().get(SearchToLinkActivity.SHARE_ERROR_CODE);
                String message = data.getStringExtra(SearchToLinkActivity.SHARE_ERROR_MESSAGE);
                // Handle errorCode and the errorMesage
                Log.e(TAG, "Error in onActivityResult: " + message + " Error code: " + errorCode);
            }
        } else if (requestCode == REQUEST_CODE_SEARCH_TO_LINK) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getBundleExtra(SearchToLinkActivity.SHARE_BUNDLE);
                int type = bundle.getInt(SearchToLinkActivity.SHARED_OBJECT_TYPE);
                switch (type) {
                    case SearchToLinkActivity.WEB:
                        constructWebResultView(bundle);
                        break;
                    case SearchToLinkActivity.LOCAL:
                        constructWebResultView(bundle);
                        break;
                    case SearchToLinkActivity.IMAGES:
                        constructImageResultView(bundle);
                        break;
                    case SearchToLinkActivity.VIDEOS:
                        constructVideoResultView(bundle);
                        break;
                    default:
                        break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Extract properties for user selected image from Search result bundle
     *
     * @param bundle
     */
    private void constructImageResultView(Bundle bundle) {

        // Title of the image to be shared
        String title = bundle.getString(SearchToLinkActivity.TITLE);
        // Description of the image to be shared
        String description = bundle.getString(SearchToLinkActivity.DESCRIPTION);
        // short url to be shared. Might be Null.
        String shortUrl = bundle.getString(SearchToLinkActivity.SHORT_URL);
        // Source web page url. Can be used as fall back if short url is Null
        String source_url = bundle.getString(SearchToLinkActivity.SOURCE_URL);
        // Original image url. Use this to construct your "preview" view
        String full_url = bundle.getString(SearchToLinkActivity.FULL_URL);
        // Thumbnail image url. Use this to construct your thumbnail "preview" view
        String thumb_url = bundle.getString(SearchToLinkActivity.THUMBNAIL_URL);

        // Display the image selected for sharing/linking
        mSelectedImagePreview.setVisibility(View.VISIBLE);
        loadImageAsync(full_url);

        StringBuilder shareStringBuilder = new StringBuilder();
        formatSearchResult(SearchToLinkActivity.TITLE, Html.fromHtml(title).toString(), shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.DESCRIPTION, description, shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.SHORT_URL, shortUrl, shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.SOURCE_URL, source_url, shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.FULL_URL, full_url, shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.THUMBNAIL_URL, thumb_url, shareStringBuilder);
        mSearchToShareResultTextView.setText(shareStringBuilder.toString());
    }

    /**
     * Extract properties for user selected web link from Search result bundle
     *
     * @param bundle
     */
    private void constructWebResultView(Bundle bundle) {
        mSelectedImagePreview.setVisibility(View.GONE);
        // Title of the web link to be shared
        String title = bundle.getString(SearchToLinkActivity.TITLE);
        // Short url to be shared. Might be Null.
        String shortUrl = bundle.getString(SearchToLinkActivity.SHORT_URL);
        // Source web page url. Can be used as fall back if short url is Null
        String source_url = bundle.getString(SearchToLinkActivity.SOURCE_URL);
        // Internal yahoo url that needs to be loaded in order to attribute clicks to your app.
        String attrib_url = bundle.getString(SearchToLinkActivity.ATTRIB_URL);

        StringBuilder shareStringBuilder = new StringBuilder();
        formatSearchResult(SearchToLinkActivity.TITLE, Html.fromHtml(title).toString(), shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.SHORT_URL, shortUrl, shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.SOURCE_URL, source_url, shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.ATTRIB_URL, attrib_url, shareStringBuilder);
        mSearchToShareResultTextView.setText(shareStringBuilder.toString());
    }

    /**
     * Extract properties for user selected video from Search result bundle
     *
     * @param bundle
     */
    private void constructVideoResultView(Bundle bundle) {
        mSelectedImagePreview.setVisibility(View.GONE);

        // Title of the video to be shared
        String title = bundle.getString(SearchToLinkActivity.TITLE);
        // Description of the video to be shared
        String description = bundle.getString(SearchToLinkActivity.DESCRIPTION);
        // Short url to be shared. Might be Null.
        String shortUrl = bundle.getString(SearchToLinkActivity.SHORT_URL);
        // Source web page url. Can be used as fall back if short url is Null
        String source_url = bundle.getString(SearchToLinkActivity.SOURCE_URL);
        // Thumbnail image url. Use this to construct your thumbnail "preview" view
        String thumb_url = bundle.getString(SearchToLinkActivity.THUMBNAIL_URL);

        StringBuilder shareStringBuilder = new StringBuilder();
        formatSearchResult(SearchToLinkActivity.TITLE, Html.fromHtml(title).toString(), shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.DESCRIPTION, description, shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.SHORT_URL, shortUrl, shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.SOURCE_URL, source_url, shareStringBuilder);
        formatSearchResult(SearchToLinkActivity.THUMBNAIL_URL, thumb_url, shareStringBuilder);
        mSearchToShareResultTextView.setText(shareStringBuilder.toString());
    }

    private void formatSearchResult(String key, String value, StringBuilder builder) {
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
            builder.append(key);
            builder.append(SEPARATOR);
            builder.append(value);
            builder.append(NEW_LINE);
        }
    }

    /**
     * Load preview image
     *
     * @param imageUri
     */
    private void loadImageAsync(final String imageUri) {
        if (!TextUtils.isEmpty(imageUri)) {
            ImageLoader.getInstance().loadImage(imageUri, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, final Bitmap loadedImage) {
                    if (null != loadedImage) {
                        mSelectedImagePreview.setImageBitmap(loadedImage);
                    }
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    Log.e(TAG, "Failed to load Preview Image Thumbnail");
                }
            });
        }
    }
}
