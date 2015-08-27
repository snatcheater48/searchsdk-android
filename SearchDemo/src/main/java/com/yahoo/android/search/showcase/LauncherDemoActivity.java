package com.yahoo.android.search.showcase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yahoo.mobile.client.share.search.settings.SearchSDKSettings;
import com.yahoo.mobile.client.share.search.ui.activity.SearchActivity;


public class LauncherDemoActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_demo);

        findViewById(R.id.search_bar_view_group).setOnClickListener(this);
        findViewById(R.id.search_bar_icon).setOnClickListener(this);
        findViewById(R.id.search_bar_edit_text).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SearchSDKSettings.setSearchSuggestEnabled(true);
        SearchActivity.IntentBuilder builder = new SearchActivity.IntentBuilder();
        builder.setCustomTheme(R.style.CustomBlurTheme);
        builder.setCustomHeader(R.layout.search_view_custom_header_blue);
        builder.setCustomFooter(R.layout.search_view_custom_footer_blue);
        builder.setCustomSearchAssist(R.layout.search_view_custom_assist_blue);
        builder.showTransparentBackground(true);
        Intent i = builder.buildIntent(LauncherDemoActivity.this);
        startActivity(i);
    }
}
