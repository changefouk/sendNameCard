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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.cards.functionHepler.CardAttributeDefault;
import com.mbox.administrator.namecardexchange.feature.cards.view.CardActivity;
import com.mbox.administrator.namecardexchange.feature.collection.adapter.DetailCardAdapter;
import com.mbox.administrator.namecardexchange.feature.collection.view.activity.CardDetailActivity;
import com.mbox.administrator.namecardexchange.feature.collection.viewmodel.FragmentCardDetailViewmodel;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentCardDetail extends Fragment implements View.OnClickListener, CardAttributeDefault {

    private static final String TAG = FragmentCardDetail.class.getSimpleName();

    private RecyclerView recyclerView;
    private DetailCardAdapter adapter;
    private LinearLayout linearAddCollection;
    private Button btnAddCard, btnCencelCard;

    private String idPreviewCard;
    private static final String IS_ADD_DETAIL = "add_detail";
    private static final String CARD_ID = "card_id";

    private FragmentCardDetailViewmodel viewModel;

    public FragmentCardDetail() {
        super();
    }

    public static FragmentCardDetail newInstance() {
        FragmentCardDetail fragment = new FragmentCardDetail();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_card, container, false);
        setUi(rootView);
        initInstances(rootView);
        return rootView;
    }

    private void setUi(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerview_carddetail);
        linearAddCollection = rootView.findViewById(R.id.linear_add_collection);
        btnAddCard = rootView.findViewById(R.id.btn_add_collection);
        btnCencelCard = rootView.findViewById(R.id.btn_cancel_collection);
    }

    private void initInstances(View rootView) {

        viewModel = ViewModelProviders.of(getActivity()).get(FragmentCardDetailViewmodel.class);
        checkIntentToExchange(getActivity().getIntent().getExtras().getBoolean(IS_ADD_DETAIL));
        idPreviewCard = getActivity().getIntent().getExtras().getString(CARD_ID);
        Log.d(TAG, "ID Recive : " + idPreviewCard);

        viewModel.setDetailData(idPreviewCard);

        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutmanager);
        viewModel.getPreviewCard().observe(this, cardDataMdls -> {
            adapter = new DetailCardAdapter(cardDataMdls, getContext(), (view, position) -> {
                //TODO: setclick adapter
                if (cardDataMdls.get(position).getTypeData().equals(FACEBOOK__TYPE)) {
                    if (cardDataMdls.get(position).getLinkURL().isEmpty()) {
                        ((CardDetailActivity) getActivity()).intentFacebook(cardDataMdls.get(position).getDataText());
                    }
                    ((CardDetailActivity) getActivity()).intentFacebook(cardDataMdls.get(position).getLinkURL());
                }
                if (cardDataMdls.get(position).getTypeData().equals(YOUTUBE_TYPE)) {
                    if (cardDataMdls.get(position).getLinkURL().isEmpty()) {
                        ((CardDetailActivity) getActivity()).intentYoutube(cardDataMdls.get(position).getDataText());
                    }
                    ((CardDetailActivity) getActivity()).intentYoutube(cardDataMdls.get(position).getLinkURL());
                }
                if (cardDataMdls.get(position).getTypeData().equals(TWITTER_TYPE)) {
                    if (cardDataMdls.get(position).getLinkURL().isEmpty()) {
                        ((CardDetailActivity) getActivity()).intentTwitter(cardDataMdls.get(position).getDataText());
                    }
                    ((CardDetailActivity) getActivity()).intentTwitter(cardDataMdls.get(position).getLinkURL());
                }
                if (cardDataMdls.get(position).getTypeData().equals(LINE_TYPE)) {
                    if (cardDataMdls.get(position).getLinkURL().isEmpty()) {
                        ((CardDetailActivity) getActivity()).intentLine(cardDataMdls.get(position).getDataText());
                    }
                    ((CardDetailActivity) getActivity()).intentLine(cardDataMdls.get(position).getLinkURL());
                }
                if (cardDataMdls.get(position).getTypeData().equals(WEB_TYPE)) {
                    if (cardDataMdls.get(position).getLinkURL().isEmpty()) {
                        ((CardDetailActivity) getActivity()).intentWebsite(cardDataMdls.get(position).getDataText());
                    }
                    ((CardDetailActivity) getActivity()).intentWebsite(cardDataMdls.get(position).getLinkURL());
                }
                if (cardDataMdls.get(position).getTypeData().equals(EMAIL_TYPE)) {
                    ((CardDetailActivity) getActivity()).intentSendEmail(cardDataMdls.get(position).getDataText());
                }
                if (cardDataMdls.get(position).getTypeData().equals(ADDRESS_TYPE)) {
                    ((CardDetailActivity) getActivity()).intentGoogleMaps(cardDataMdls.get(position).getDataText());
                }
                if (cardDataMdls.get(position).getTypeData().equals(PREVIEW_CARD_TYPE)) {
                    clickToDetail();
                }
            });
            adapter.setOrientationCard(viewModel.getCardMdl().getCardOrientation());
            recyclerView.setAdapter(adapter);
//                    adapter.notifyItemRangeInserted(0,cardDataMdls.size());
//                    recyclerView.scrollToPosition(cardDataMdls.size() -1);
            adapter.notifyDataSetChanged();
        });

        btnAddCard.setOnClickListener(this);
        btnCencelCard.setOnClickListener(this);
    }

    private void checkIntentToExchange(boolean isExchange) {
        if (isExchange) {
            linearAddCollection.setVisibility(View.VISIBLE);
        } else {
            linearAddCollection.setVisibility(View.GONE);
        }
    }

    private void clickToDetail() {
        Intent i = new Intent(getActivity(), CardActivity.class);
        i.putExtra("card_id", idPreviewCard);
        startActivity(i);
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

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        getActivity().overridePendingTransition(R.anim.anim_slide_from_right, R.anim.anim_slide_to_left);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_collection:
                addCardToCollection();
            case R.id.btn_cancel_collection:
                cancleCardSuggestion();
        }
    }



    private void cancleCardSuggestion() {
        SharedPreferences sp = getActivity().getSharedPreferences(((CardDetailActivity) getActivity()).SHARED_VALUE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(((CardDetailActivity) getActivity()).SHARED_SUGGEST, 1);
        editor.commit();
        linearAddCollection.setVisibility(View.GONE);
    }

    private void addCardToCollection() {
        SharedPreferences sp = getActivity().getSharedPreferences(((CardDetailActivity) getActivity()).SHARED_VALUE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(((CardDetailActivity) getActivity()).SHARED_ADDCARD, 1);
        editor.putInt(((CardDetailActivity) getActivity()).SHARED_SUGGEST, 0);
        editor.commit();
        getActivity().onBackPressed();
    }
}