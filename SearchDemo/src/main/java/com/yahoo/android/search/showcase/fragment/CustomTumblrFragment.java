package com.yahoo.android.search.showcase.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yahoo.android.search.showcase.R;
import com.yahoo.android.search.showcase.Utils.Utils;
import com.yahoo.android.search.showcase.adapter.TumblrListAdapter;
import com.yahoo.android.search.showcase.data.TumblrData;
import com.yahoo.mobile.client.share.search.SearchError;
import com.yahoo.mobile.client.share.search.commands.SearchCommand;
import com.yahoo.mobile.client.share.search.data.SearchQuery;
import com.yahoo.mobile.client.share.search.data.SearchResponseData;
import com.yahoo.mobile.client.share.search.data.contentmanager.ContentManager;
import com.yahoo.mobile.client.share.search.interfaces.IQueryManager;
import com.yahoo.mobile.client.share.search.ui.contentfragment.SearchResultFragment;

import java.util.List;

/**
 *
 * Custom fragment is required to extend SearchResultFragment from search sdk.
 */

public class CustomTumblrFragment extends SearchResultFragment implements ContentManager.IContentHandler {
    private TextView queryTextView;
    private static final String TAB_TITLE = "Tumblr";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_custom_tumblr, container, false);
        ListView tumblrList = (ListView) mRootView.findViewById(R.id.tumblrFeed);
        List<TumblrData> tumblrDataList = Utils.parseTumblrDataList(getActivity());
        tumblrList.setAdapter(new TumblrListAdapter(getActivity(), R.layout.tumblr_list_item, tumblrDataList));
        mRootView.requestFocus();
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onContentReceived(ContentManager manager, SearchResponseData content, SearchQuery query) {
        queryTextView.setText(query.getQueryString());
        queryTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onContentError(ContentManager manager, SearchError error, SearchQuery query) {
        queryTextView.setText("Error: " + error.getErrorCode() + " " + query.getQueryString());
        queryTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressReceived(ContentManager manager, SearchCommand.SearchProgressEnum progress, SearchQuery query) {
        queryTextView.setVisibility(View.GONE);
    }

    @Override
    public void moveBackgroundWithParallax(int positionOffset, boolean forward, Context context) {

    }

    /**
     * Apply Top padding to custom views here.
     * @param topPadding
     */

    @Override
    public void setTopPadding(int topPadding) {

    }

    /**
     * Apply Bottom padding to custom views here.
     * @param bottomPadding
     */

    @Override
    public void setBottomPadding(int bottomPadding) {

    }

    /**
     * Apply background color for search results view.
     * @param color
     */

    @Override
    public void setBackgroundColor(int color) {

    }

    @Override
    public IQueryManager getContentManager() {
        return null;
    }

    @Override
    public String getLabel(Context context) {
        if(context != null) {
            return TAB_TITLE;
        }
        return null;
    }

    /**
     *
     * Below methods to override OnScroll behavior of custom fragments.
     */

    @Override
    public OnScrollListener getOnScrollListener() {
        return null;
    }

    @Override
    public void setOnScrollListener(OnScrollListener onScrollListener) {

    }

    @Override
    public boolean isScrollEnabled() {
        return false;
    }

    @Override
    public int getScrollY() {
        return 0;
    }
}
