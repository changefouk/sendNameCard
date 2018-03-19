package com.mbox.administrator.namecardexchange.feature.exchange.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mbox.administrator.namecardexchange.Manifest;
import com.mbox.administrator.namecardexchange.MyApplication;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.BaseActivity;
import com.mbox.administrator.namecardexchange.feature.exchange.view.fragment.FragmentExchangeNearby;
import com.mbox.administrator.namecardexchange.feature.exchange.view.fragment.FragmentFindId;
import com.mbox.administrator.namecardexchange.feature.exchange.view.fragment.FragmentQrCode;
import com.mbox.administrator.namecardexchange.feature.exchange.view.fragment.FragmentSuggest;
import com.mbox.administrator.namecardexchange.feature.exchange.viewmodel.ExchangeNearbyActivityViewmodel;
import com.mbox.administrator.namecardexchange.feature.ui.view.LoginActivity;

import jp.s64.android.animatedtoolbar.AnimatedToolbar;

public class ExchangeActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ExchangeActivity";
    private AnimatedToolbar mToolbar;
    private ExchangeNearbyActivityViewmodel viewmodel;

    private Button myQrCodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        setUi();
        initInstance();

        if (savedInstanceState == null) {
            if (viewmodel.SAVE_KEY == ExchangeNearbyActivityViewmodel.fragmentID.NEARBY.ordinal()) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.content_exchange, FragmentExchangeNearby.newInstance()).commit();
                myQrCodeButton.setVisibility(View.GONE);
            }
            if (viewmodel.SAVE_KEY == ExchangeNearbyActivityViewmodel.fragmentID.QR_CODE.ordinal()) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.content_exchange, FragmentQrCode.newInstance()).commit();
                myQrCodeButton.setVisibility(View.VISIBLE);

            }
            if (viewmodel.SAVE_KEY == ExchangeNearbyActivityViewmodel.fragmentID.ID.ordinal()) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.content_exchange, FragmentFindId.newInstance()).commit();
                myQrCodeButton.setVisibility(View.GONE);
            }
            if (viewmodel.SAVE_KEY == ExchangeNearbyActivityViewmodel.fragmentID.SUGGEST_CARD.ordinal()) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.content_exchange, FragmentSuggest.newInstance()).commit();
                myQrCodeButton.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void setUi() {
        mToolbar = findViewById(R.id.toolbar_nearby);
        myQrCodeButton = findViewById(R.id.my_QrCode_button);
    }

    @Override
    protected void initInstance() {
        viewmodel = ViewModelProviders.of(this).get(ExchangeNearbyActivityViewmodel.class);
        viewmodel.SAVE_KEY = getIntent().getExtras().getInt(viewmodel.FRAGMENT_KEY);
        Log.d(TAG, "Savekey = " + viewmodel.SAVE_KEY);
        setToolbar();
        settoolbarName(viewmodel.SAVE_KEY);

        myQrCodeButton.setOnClickListener(this);
    }

    private void settoolbarName(int saveKey) {
        /**
         * KEY :
         * 0 = NEARBY
         * 1 = QR CODE
         * 2 = FIND ID
         * 3 = SUGGEST
         */
        switch (saveKey) {
            case 0:
                setTitle(getString(R.string.exchange_title_find_near_card));
                break;
            case 1:
                setTitle(getString(R.string.exchange_title_qr_code));
                break;
            case 2:
                setTitle(getString(R.string.exchange_title_find_with_id));
                break;
            case 3:
                setTitle(getString(R.string.exchange_title_suggest_card));
                break;
        }
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_QrCode_button:
                intentGenerateMyQr();
        }
    }

    private void intentGenerateMyQr() {
        Intent i = new Intent(this, MyQrCodeActivity.class);
        startActivity(i);
    }

    @Override
    protected void updateUI(FirebaseUser user) {
        super.updateUI(user);
        if (user != null) {

        } else {
            Intent i = new Intent(getBaseContext(), LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI(mFirebaseUser);
    }
}
