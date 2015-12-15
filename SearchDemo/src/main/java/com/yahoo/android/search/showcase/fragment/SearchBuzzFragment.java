package com.yahoo.android.search.showcase.fragment;

/**
 * Created by mgujare on 12/15/15.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yahoo.android.search.showcase.R;

public class SearchBuzzFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_buzz, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView defaultSearchBuzz = (ImageView) view.findViewById(R.id.default_button);
        ImageView customizedSearchBuzz = (ImageView) view.findViewById(R.id.customized_button);
        defaultSearchBuzz.setOnClickListener(this);
        customizedSearchBuzz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.default_button:
                showcaseTrendingView(false);
                break;
            case R.id.customized_button:
                showcaseTrendingView(true);
                break;
        }
    }

    private void showcaseTrendingView(boolean shouldCustomize) {
        SearchBuzzShowcaseFragment searchBuzzShowcaseFragment = SearchBuzzShowcaseFragment.newInstance(shouldCustomize);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container,searchBuzzShowcaseFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
