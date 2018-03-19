package com.mbox.administrator.namecardexchange.feature.collection.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.collection.adapter.PreviewCardAdapter;
import com.mbox.administrator.namecardexchange.feature.collection.view.activity.CardDetailActivity;
import com.mbox.administrator.namecardexchange.feature.collection.viewmodel.FragmentFriendcardViewModel;
import com.mbox.administrator.namecardexchange.feature.ui.view.MainActivity;
import com.mbox.administrator.namecardexchange.util.HideShowScrollListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentFriendcard extends Fragment {

    private RecyclerView recyclerView;
    private FragmentFriendcardViewModel viewModel;
    private PreviewCardAdapter adapter;

    public FragmentFriendcard() {
        super();
    }

    public static FragmentFriendcard newInstance() {
        FragmentFriendcard fragment = new FragmentFriendcard();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friendcard, container, false);
        setUi(rootView);
        initInstances(rootView);
        return rootView;
    }

    private void setUi(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerview_firendcard);
    }

    private void initInstances(View rootView) {
        //TODO MOCKDATA

        viewModel = ViewModelProviders.of(getActivity()).get(FragmentFriendcardViewModel.class);
        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutmanager);
        if (((MainActivity) getActivity()).getmFirebaseUser() != null) {
            viewModel.getPreviewCard(((MainActivity) getActivity()).getCardmember(),
                    getValueSharedCard()).observe(this, cardPreviewModels -> {
                List<CardMdl> lists = new ArrayList<>();
                for (int a = 0; a < cardPreviewModels.size(); a++) {
                    if (((MainActivity) getActivity()).getmFirebaseUser() != null) {
                        if (!cardPreviewModels.get(a).getMemberID().equals(((MainActivity) getActivity()).getCardmember())) {
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

    private int getValueSharedCard() {
        SharedPreferences sp = getActivity().getSharedPreferences(((MainActivity) getActivity()).SHARED_VALUE, Context.MODE_PRIVATE);
        int value = sp.getInt(((MainActivity) getActivity()).SHARED_ADDCARD, 0);
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

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            viewModel.getPreviewCard(((MainActivity) getActivity()).getCardmember()
                    , getValueSharedCard()).observe(this, list -> {
                List<CardMdl> lists = new ArrayList<>();
                for (int a = 0; a < list.size(); a++) {
                    if (!list.get(a).getMemberID().equals(((MainActivity) getActivity()).getCardmember())) {
                        if (((MainActivity) getActivity()).getmFirebaseUser() != null) {
                            lists.add(list.get(a));
                        }
                    }
                }
                adapter.updateData(lists);
            });
        }
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
