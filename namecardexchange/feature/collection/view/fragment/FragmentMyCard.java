package com.mbox.administrator.namecardexchange.feature.collection.view.fragment;

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

import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.collection.adapter.PreviewCardAdapter;
import com.mbox.administrator.namecardexchange.feature.collection.view.activity.CardDetailActivity;
import com.mbox.administrator.namecardexchange.feature.collection.viewmodel.FragmentMyCardViewmodel;
import com.mbox.administrator.namecardexchange.feature.ui.view.MainActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentMyCard extends Fragment {

    private RecyclerView recyclerView;
    private FragmentMyCardViewmodel viewModel;
    private PreviewCardAdapter adapter;

    public FragmentMyCard() {
        super();
    }

    public static FragmentMyCard newInstance() {
        FragmentMyCard fragment = new FragmentMyCard();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mycard, container, false);
        setUi(rootView);
        initInstances(rootView);
        return rootView;
    }

    private void setUi(View rootview) {
        recyclerView = rootview.findViewById(R.id.recyclerview_mycard);
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        viewModel = ViewModelProviders.of(getActivity()).get(FragmentMyCardViewmodel.class);

        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutmanager);
        //TODO MOCKDATA
        SharedPreferences sp = getActivity().getSharedPreferences(((MainActivity) getActivity()).SHARED_VALUE, Context.MODE_PRIVATE);
        int value = sp.getInt(((MainActivity) getActivity()).SHARED_ADDCARD, 0);
        if (((MainActivity) getActivity()).getmFirebaseUser() != null) {
            viewModel.getPreviewCard(((MainActivity) getActivity()).getCardmember(), value).observe(this, cardPreviewModels -> {
                List<CardMdl> lists = new ArrayList<>();
                for (int a = 0; a < cardPreviewModels.size(); a++) {
                    if (((MainActivity) getActivity()).getmFirebaseUser() != null) {
                        if (cardPreviewModels.get(a).getMemberID().equals(((MainActivity) getActivity()).getCardmember())) {
                            lists.add(cardPreviewModels.get(a));
                        }
                    }
                }

                adapter = new PreviewCardAdapter(lists, getContext(), (view, position) -> {
                    Intent i = new Intent(getActivity(), CardDetailActivity.class);
                    i.putExtra("card_id", lists.get(position).getCardID());
                    startActivity(i);
                });
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            });
        }
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
