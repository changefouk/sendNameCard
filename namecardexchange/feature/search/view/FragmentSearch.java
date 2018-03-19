package com.mbox.administrator.namecardexchange.feature.search.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.collection.view.activity.CardDetailActivity;
import com.mbox.administrator.namecardexchange.feature.search.adapter.SearchCardAdapter;
import com.mbox.administrator.namecardexchange.feature.search.viewmodel.FragmentSearchViewmodel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentSearch extends Fragment {

    private android.support.v7.widget.SearchView mSearchView;

    private RecyclerView recyclerView;
    private FragmentSearchViewmodel viewmodel;
    private SearchCardAdapter adapter;

    public FragmentSearch() {
        super();
    }

    public static FragmentSearch newInstance() {
        FragmentSearch fragment = new FragmentSearch();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        setUi(rootView);
        initInstances(rootView);
        return rootView;
    }

    private void setUi(View rootView) {
        mSearchView = rootView.findViewById(R.id.searchview_search);
        recyclerView = rootView.findViewById(R.id.recycler_search);
    }

    private void initInstances(View rootView) {

        viewmodel = ViewModelProviders.of(getActivity()).get(FragmentSearchViewmodel.class);
        /**
         Focus Keyboard Search View
         **/
        mSearchView.onActionViewExpanded();
        //TODO : Recyclerview Search
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        viewmodel.getPreviewCard().observe(this, cardMdls -> {
            adapter = new SearchCardAdapter(cardMdls, getContext(), (view, position, cardId) -> {
                Intent i = new Intent(getActivity(), CardDetailActivity.class);
                i.putExtra("card_id", cardId);
                startActivity(i);
            });
            recyclerView.setAdapter(adapter);
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    protected void overridePendingTransitionEnter() {
        getActivity().overridePendingTransition(R.anim.anim_slide_from_right, R.anim.anim_slide_to_left);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }
}
