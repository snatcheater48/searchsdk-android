package com.yahoo.android.search.showcase.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yahoo.android.search.showcase.R;
import com.yahoo.mobile.client.share.search.data.SearchAssistData;
import com.yahoo.mobile.client.share.search.interfaces.ISearchAssistController;
import com.yahoo.mobile.client.share.search.interfaces.ISearchAssistData;
import com.yahoo.mobile.client.share.search.interfaces.ISearchAssistItemHolder;

public class CustomSearchAssistItem extends LinearLayout implements ISearchAssistItemHolder {

    private ISearchAssistData mData;
    private ISearchAssistController mSearchAssistController;

    public CustomSearchAssistItem(Context context) {
        super(context);
    }

    public CustomSearchAssistItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSearchAssistItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setData(ISearchAssistData iSearchAssistData) {
        mData = iSearchAssistData;
        createView();
    }

    private void createView() {

        final int searchAssistType = mData.getType();
        TextView text = (TextView) findViewById(R.id.text);
        String label = mData.getText();
        text.setText(label);
        text.setTag(mData);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchAssistController != null) {
                    mSearchAssistController.onSearchAssistItemClick(mData);
                }
            }
        });

        switch (searchAssistType) {
            case ISearchAssistData.SEARCH_SUGGEST:
            case ISearchAssistData.SEARCH_DEFAULT:
                buildSearchSuggestItem();
                break;
            case ISearchAssistData.LOCAL_WEB:
            case ISearchAssistData.CLEAR_LOCAL_HISTORY:
            case ISearchAssistData.SHOW_ALL_HISTORY:
            case ISearchAssistData.SEARCH_TRENDING:
            case ISearchAssistData.SEARCH_APPS:
            case ISearchAssistData.SEARCH_CONTACTS:
                buildLocalSearchItem();
        }
    }

    private void buildSearchSuggestItem() {
        ImageView plus = (ImageView) findViewById(R.id.add);
        plus.setVisibility(View.VISIBLE);

        if (mData.getType() == SearchAssistData.SEARCH_DEFAULT) {
            plus.setVisibility(View.GONE);
        } else {
            plus.setVisibility(View.VISIBLE);
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSearchAssistController != null) {
                        mSearchAssistController.buildQuery(mData);
                    }
                }
            });
        }
    }

    private void buildLocalSearchItem() {
        ImageView plus = (ImageView) findViewById(R.id.add);
        plus.setVisibility(View.GONE);

        TextView text = (TextView) findViewById(R.id.text);
        ImageView iconView = (ImageView) findViewById(R.id.icon);
        Resources res = getContext().getResources();
        switch (mData.getType()) {
            case ISearchAssistData.LOCAL_WEB:
                iconView.setImageDrawable(res.getDrawable(android.R.drawable.ic_menu_recent_history));
                iconView.setVisibility(View.VISIBLE);
                break;
            case ISearchAssistData.SHOW_ALL_HISTORY:
                text.setText(res.getString(R.string.yssdk_show_all_history));
                text.setGravity(Gravity.CENTER);
                break;
            case ISearchAssistData.CLEAR_LOCAL_HISTORY:
                text.setText(res.getString(R.string.yssdk_clear_history_summary));
                text.setGravity(Gravity.CENTER);
                break;
        }
    }

    @Override
    public TextView getTextView() {
        return (TextView) findViewById(R.id.text);
    }

    @Override
    public View getIconView() {
        return findViewById(R.id.icon);
    }

    @Override
    public void setSearchController(ISearchAssistController iSearchAssistController) {
        mSearchAssistController = iSearchAssistController;
    }
}
