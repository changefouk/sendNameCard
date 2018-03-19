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

import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.exchange.adapter.ExchangeMenuAdapter;
import com.mbox.administrator.namecardexchange.feature.exchange.view.activity.ExchangeActivity;
import com.mbox.administrator.namecardexchange.feature.exchange.viewmodel.ExchangeNearbyActivityViewmodel;
import com.mbox.administrator.namecardexchange.feature.exchange.viewmodel.FragmentExchangeViewmodel;
import com.mbox.administrator.namecardexchange.feature.ui.view.MainActivity;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentExchange extends Fragment {

    private RecyclerView recyclerView;
    private FragmentExchangeViewmodel viewmodel;
    private ExchangeMenuAdapter adapter;

    public FragmentExchange() {
        super();
    }

    public static FragmentExchange newInstance() {
        FragmentExchange fragment = new FragmentExchange();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exchange, container, false);
        setUi(rootView);
        initInstances(rootView);
        return rootView;
    }

    private void setUi(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerview_exchange);
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        viewmodel = ViewModelProviders.of(getActivity()).get(FragmentExchangeViewmodel.class);
        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutmanager);

        viewmodel.getMenuList().observe(this, list -> {
            if (list != null) {
                adapter = new ExchangeMenuAdapter(list, getContext(),getValueSharedCard(), (view, position) -> {

                    if (list.get(position).getModelID() == FragmentExchangeViewmodel.menuID.FIND_NEAR.ordinal()) {
                        Intent i = new Intent(getActivity(), ExchangeActivity.class);
                        i.putExtra(ExchangeNearbyActivityViewmodel.FRAGMENT_KEY, ExchangeNearbyActivityViewmodel.fragmentID.NEARBY.ordinal());
                        startActivity(i);
                    }
                    if (list.get(position).getModelID() == FragmentExchangeViewmodel.menuID.FIND_QR_CODE.ordinal()) {
                        Intent i = new Intent(getActivity(), ExchangeActivity.class);
                        i.putExtra(ExchangeNearbyActivityViewmodel.FRAGMENT_KEY, ExchangeNearbyActivityViewmodel.fragmentID.QR_CODE.ordinal());
                        startActivity(i);
                    }
                    if (list.get(position).getModelID() == FragmentExchangeViewmodel.menuID.FIND_ID.ordinal()) {
                        Intent i = new Intent(getActivity(), ExchangeActivity.class);
                        i.putExtra(ExchangeNearbyActivityViewmodel.FRAGMENT_KEY, ExchangeNearbyActivityViewmodel.fragmentID.ID.ordinal());
                        startActivity(i);
                    }
                    if (list.get(position).getModelID() == FragmentExchangeViewmodel.menuID.FIND_SUGGEST.ordinal()) {
                        Intent i = new Intent(getActivity(), ExchangeActivity.class);
                        i.putExtra(ExchangeNearbyActivityViewmodel.FRAGMENT_KEY, ExchangeNearbyActivityViewmodel.fragmentID.SUGGEST_CARD.ordinal());
                        startActivity(i);
                    }

                });
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private int getValueSharedCard() {
        SharedPreferences sp = getActivity().getSharedPreferences(((MainActivity) getActivity()).SHARED_VALUE, Context.MODE_PRIVATE);
        int value = sp.getInt(((MainActivity) getActivity()).SHARED_SUGGEST, 0);
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
        if (adapter !=null){
            adapter.setsuggestValue(getValueSharedCard());
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
