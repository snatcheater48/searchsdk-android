package com.yahoo.android.search.showcase.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yahoo.mobile.client.share.search.interfaces.ISearchController;
import com.yahoo.mobile.client.share.search.interfaces.ISearchViewHolder;
import com.yahoo.android.search.showcase.R;

public class CustomSearchViewHolder extends LinearLayout implements ISearchViewHolder {

    private Context mContenxt = null;

    public CustomSearchViewHolder(Context context) {
        super(context);
    }

    public CustomSearchViewHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContenxt = context;
    }

    public CustomSearchViewHolder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /** Required methods that implement Interface from SDK */

    @Override
    public void setSearchController(final ISearchController searchController) {
        ImageView backArrow = (ImageView) findViewById(R.id.search_bar_icon);
        backArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                searchController.goBack();
            }
        });
    }

    @Override
    public EditText getSearchEditText() {
        return (EditText) findViewById(R.id.search_bar_edit_text);
    }

    @Override
    public View getVoiceSearchButton() {
        return null;
    }

    @Override
    public View getClearTextButton() {
        return findViewById(R.id.search_bar_clear_icon);
    }

    @Override
    public int getSearchViewHeightOffset() {
        return 0;
    }

    @Override
    public void onVoiceSearchAvailabilityChanged(boolean isVoiceSearchAvailable) {

    }

    @Override
    public void onFocusChanged(EditText editText, boolean flag) {}
}
