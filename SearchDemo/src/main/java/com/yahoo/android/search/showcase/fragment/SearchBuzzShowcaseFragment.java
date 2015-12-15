package com.yahoo.android.search.showcase.fragment;

/**
 * Created by mgujare on 12/15/15.
 */

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yahoo.android.search.showcase.R;
import com.yahoo.mobile.client.share.search.SearchError;
import com.yahoo.mobile.client.share.search.interfaces.ITrendingViewListener;
import com.yahoo.mobile.client.share.search.settings.TrendingViewSettings;
import com.yahoo.mobile.client.share.search.ui.view.TrendingView;

public class SearchBuzzShowcaseFragment extends Fragment implements ITrendingViewListener{
    private static final String SHOW_CUSTOMIZED_SEARCH_BUZZ = "SHOW_CUSTOMIZED_SEARCH_BUZZ";

    // TODO: Rename and change types of parameters
    private boolean mShowCustomized;
    private TrendingView mTrendingView;
    private TextView mLoadingTextView;

    public static SearchBuzzShowcaseFragment newInstance(boolean showCustomizedTrendingView) {
        SearchBuzzShowcaseFragment fragment = new SearchBuzzShowcaseFragment();
        Bundle args = new Bundle();
        args.putBoolean(SHOW_CUSTOMIZED_SEARCH_BUZZ, showCustomizedTrendingView);
        fragment.setArguments(args);
        return fragment;
    }
    public SearchBuzzShowcaseFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mShowCustomized = getArguments().getBoolean(SHOW_CUSTOMIZED_SEARCH_BUZZ);
        }
    }
    private void initDefaultTrendingView() {
        mTrendingView.setBackgroundResource(R.drawable.yssdk_trending_view_rounded);
        mTrendingView.initialize(null,this);
    }
    private void initCustomizedTrendingView() {
        mTrendingView.setBackgroundResource(R.drawable.trending_gradient_background);
        TrendingViewSettings.Builder builder = new TrendingViewSettings.Builder();
        builder.setCustomHeader(R.layout.trending_view_header);
        builder.setCustomTerm(R.layout.trending_view_item);

        mTrendingView.initialize(builder.build(),this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View trendingLayout = inflater.inflate(R.layout.fragment_search_buzz_showcase, container, false);
        mTrendingView = (TrendingView) trendingLayout.findViewById(R.id.trending_view);
        mLoadingTextView = (TextView) trendingLayout.findViewById(R.id.loadingText);

        if(mShowCustomized){
            initCustomizedTrendingView();
        }else{
            initDefaultTrendingView();
        }
        return trendingLayout;
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
    @Override
    public void onTrendingViewReady(TrendingView trendingView) {
        mLoadingTextView.setVisibility(View.GONE);
        mTrendingView.setVisibility(View.VISIBLE);
    }
    @Override
    public void onTrendingViewError(SearchError searchError) {
        Toast.makeText(getActivity(), "Error received - " + searchError.getErrorMessage(), Toast.LENGTH_LONG).show();
    }
}
