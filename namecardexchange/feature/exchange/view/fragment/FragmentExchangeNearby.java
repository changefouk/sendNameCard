package com.mbox.administrator.namecardexchange.feature.exchange.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.PublishCallback;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.Strategy;
import com.google.android.gms.nearby.messages.SubscribeCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.collection.view.activity.CardDetailActivity;
import com.mbox.administrator.namecardexchange.feature.exchange.model.ExchangeNearByModel;
import com.mbox.administrator.namecardexchange.feature.exchange.view.activity.ExchangeActivity;
import com.mbox.administrator.namecardexchange.feature.ui.view.MainActivity;
import com.mbox.administrator.namecardexchange.util.UriTypeAdapter;
import com.mbox.administrator.namecardexchange.feature.exchange.adapter.NearbyAdapter;
import com.mbox.administrator.namecardexchange.feature.exchange.viewmodel.FragmentExchangeNearbyViewmodel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentExchangeNearby extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "FragmentExchangeNearby";

    private static final int TTL_IN_SECONDS = 5 * 60; // Three minutes.
    private static final Strategy PUB_SUB_STRATEGY = new Strategy.Builder()
            .setTtlSeconds(TTL_IN_SECONDS).build();

    private static final String IS_ADD_DETAIL = "add_detail";
    private static final String CARD_ID = "card_id";

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Uri.class, new UriTypeAdapter())
            .create();
    private GoogleApiClient mGoogleApiClient;

    private Message mPubmessage;
    private MessageListener mMessageListener;

    private ExchangeNearByModel cardNearbyModel;

    private SwitchCompat mSwitchNearby;
    private RecyclerView recyclerView;
    private NearbyAdapter adapter;
    private List<ExchangeNearByModel> listnearby = new ArrayList<>();
    private FragmentExchangeNearbyViewmodel viewmodel;


    public FragmentExchangeNearby() {
        super();
    }

    public static FragmentExchangeNearby newInstance() {
        FragmentExchangeNearby fragment = new FragmentExchangeNearby();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exchange_nearby, container, false);
        setUI(rootView);
        initInstances(rootView);
        return rootView;
    }

    private void setUI(View rootView) {
        mSwitchNearby = rootView.findViewById(R.id.nearby_switch);
        recyclerView = rootView.findViewById(R.id.recyclerview_nearby);
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here

        viewmodel = ViewModelProviders.of(getActivity()).get(FragmentExchangeNearbyViewmodel.class);
        viewmodel.getPreviewCard().observe(this, cardMdls -> {
            for (int a = 0; a < cardMdls.size(); a++) {
                if (cardMdls.get(a).getMemberID().equals(((ExchangeActivity) getActivity()).getCardmember())) {
                    cardNearbyModel = new ExchangeNearByModel(cardMdls.get(a).getCardID(), cardMdls.get(a).getCardURLPreview(), cardMdls.get(a).getCardOrientation());
                    Log.d("FLUKESSSSSS", "nearby model : " + new Gson().toJson(cardNearbyModel));
                    doingNearby();
                }
            }
        });


    }

    private void doingNearby() {
        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutmanager);
        adapter = new NearbyAdapter(getContext(), (view, position, model) -> {
            /**
             * TODO : Onclick list to send nameCard
             */
            Intent i = new Intent(getActivity(), CardDetailActivity.class);
            i.putExtra(CARD_ID, model.getCardId());
            i.putExtra(IS_ADD_DETAIL, true);
            startActivity(i);
        });

        recyclerView.setAdapter(adapter);

        mPubmessage = new Message(GSON.toJson(cardNearbyModel).getBytes());

        mMessageListener = new MessageListener() {
            @Override
            public void onFound(Message message) {
                try {
                    final ExchangeNearByModel model = GSON.fromJson(new String(message.getContent()), ExchangeNearByModel.class);
                    Log.d("FLUKEEE", "model found " + new Gson().toJson(model));
                    adapter.nearbyFound(model);

                } catch (final JsonSyntaxException e) {
                    Log.e(TAG, "Invalid message received: " + new String(message.getContent()));
                    Log.e(TAG, "Invalid message exception:", e);
                }
            }

            @Override
            public void onLost(Message message) {
                try {
                    final ExchangeNearByModel model = GSON.fromJson(new String(message.getContent()), ExchangeNearByModel.class);
                    Log.e(TAG, "User Lost :" + model.getCardId());
                    adapter.nearbyLost(model);
                } catch (final JsonSyntaxException e) {
                    Log.e(TAG, "Invalid message received: " + new String(message.getContent()));
                    Log.e(TAG, "Invalid message exception:", e);
                }
            }
        };

        mSwitchNearby.setOnCheckedChangeListener((compoundButton, isCheck) -> {
            if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                if (isCheck) {
                    openNearby();
                } else {
                    closeNearby();
                }
            }
        });
        buildGoogleApiClient();
    }

    private void buildGoogleApiClient() {
        if (mGoogleApiClient != null) {
            return;
        }
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Nearby.MESSAGES_API)
                .addConnectionCallbacks(this)
                .enableAutoManage(getActivity(), this)
                .build();
    }

    private void closeNearby() {
        listnearby.clear();
        adapter.nearbyClose();
        Nearby.Messages.unsubscribe(mGoogleApiClient, mMessageListener);

        Log.i(TAG, "Unpublishing.");
        Nearby.Messages.unpublish(mGoogleApiClient, mPubmessage);
    }

    private void openNearby() {
        listnearby.clear();
        adapter.nearbyClose();
        SubscribeOptions options = new SubscribeOptions.Builder()
                .setStrategy(PUB_SUB_STRATEGY)
                .setCallback(new SubscribeCallback() {
                    @Override
                    public void onExpired() {
                        super.onExpired();
                        Log.i(TAG, "No longer subscribing");
                        getActivity().runOnUiThread(() -> mSwitchNearby.setChecked(false));
                    }
                }).build();
        Nearby.Messages.subscribe(mGoogleApiClient, mMessageListener, options)
                .setResultCallback(status -> {
                    if (status.isSuccess()) {
                        Log.i(TAG, "Subscribed successfully.");
                    } else {
                        logAndShowSnackbar("Could not subscribe, status = " + status);
                        mSwitchNearby.setChecked(false);
                    }
                });

        ////////////////////////
        Log.i(TAG, "Publishing");
        PublishOptions options2 = new PublishOptions.Builder()
                .setStrategy(PUB_SUB_STRATEGY)
                .setCallback(new PublishCallback() {
                    @Override
                    public void onExpired() {
                        super.onExpired();
                        Log.i(TAG, "No longer publishing");
                        getActivity().runOnUiThread(() -> mSwitchNearby.setChecked(false));
                    }
                }).build();

        Nearby.Messages.publish(mGoogleApiClient, mPubmessage, options2)
                .setResultCallback(status -> {
                    if (status.isSuccess()) {
                        Log.i(TAG, "Published successfully.");
                    } else {
                        logAndShowSnackbar("Could not publish, status = " + status);
                        mSwitchNearby.setChecked(false);
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

    private void logAndShowSnackbar(final String text) {
        Log.w(TAG, text);
        View container = getActivity().findViewById(R.id.content_nearby);
        if (container != null) {
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "GoogleApiClient connected");
        // We use the Switch buttons in the UI to track whether we were previously doing pub/sub (
        // switch buttons retain state on orientation change). Since the GoogleApiClient disconnects
        // when the activity is destroyed, foreground pubs/subs do not survive device rotation. Once
        // this activity is re-created and GoogleApiClient connects, we check the UI and pub/sub
        // again if necessary.
        if (mSwitchNearby.isChecked()) {
            openNearby();
        }
        if (mSwitchNearby.isChecked()) {
            closeNearby();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        logAndShowSnackbar("Connection suspended. Error code: " + i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mSwitchNearby.setEnabled(false);
        logAndShowSnackbar("Exception while connecting to Google Play services: " +
                connectionResult.getErrorMessage());
    }
}
