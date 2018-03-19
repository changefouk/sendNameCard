package com.mbox.administrator.namecardexchange.feature.exchange.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.collection.view.activity.CardDetailActivity;
import com.mbox.administrator.namecardexchange.feature.exchange.adapter.SuggestCardAdapter;
import com.mbox.administrator.namecardexchange.feature.exchange.view.activity.ExchangeActivity;
import com.mbox.administrator.namecardexchange.feature.exchange.viewmodel.FragmentSuggestViewmodel;
import com.mbox.administrator.namecardexchange.feature.ui.view.MainActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentSuggest extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayout linearEmptyData;
    private FragmentSuggestViewmodel viewmodel;

    private SuggestCardAdapter adapter;

    public FragmentSuggest() {
        super();
    }

    public static FragmentSuggest newInstance() {
        FragmentSuggest fragment = new FragmentSuggest();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exchange_suggest, container, false);
        setUI(rootView);
        initInstances(rootView);
        return rootView;
    }

    private void setUI(View rootView) {
        recyclerView = rootView.findViewById(R.id.recycler_suggest);
        linearEmptyData = rootView.findViewById(R.id.linear_suggest_empty);
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        viewmodel = ViewModelProviders.of(getActivity()).get(FragmentSuggestViewmodel.class);

        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutmanager);

        if (getValueSharedCard() != 0) {
            linearEmptyData.setVisibility(View.GONE);
            viewmodel.getSuggestCard().observe(getActivity(), list -> {

                List<CardMdl> lists = new ArrayList<>();
                for (int a = 0; a < list.size(); a++) {
                    if (((ExchangeActivity) getActivity()).getmFirebaseUser() != null) {
                        if (!list.get(a).getMemberID().equals(((ExchangeActivity) getActivity()).getCardmember())) {
                            lists.add(list.get(a));
                        }
                    }
                }

                adapter = new SuggestCardAdapter(lists, getContext(), (view, position) -> {
                    Intent i = new Intent(getActivity(), CardDetailActivity.class);
                    i.putExtra("card_id", lists.get(position).getCardID());
                    startActivity(i);
                });
                recyclerView.setAdapter(adapter);
            });
        } else {
            recyclerView.setVisibility(View.GONE);
            linearEmptyData.setVisibility(View.VISIBLE);
        }

    }

    private int getValueSharedCard() {
        SharedPreferences sp = getActivity().getSharedPreferences(((ExchangeActivity) getActivity()).SHARED_VALUE, Context.MODE_PRIVATE);
        int value = sp.getInt(((ExchangeActivity) getActivity()).SHARED_SUGGEST, 0);
        return value;
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
