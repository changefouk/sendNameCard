package com.mbox.administrator.namecardexchange.feature.exchange.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mbox.administrator.namecardexchange.Manifest;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.cards.models.QrCodeCardModel;
import com.mbox.administrator.namecardexchange.feature.collection.view.activity.CardDetailActivity;
import com.mbox.administrator.namecardexchange.feature.exchange.view.activity.ExchangeActivity;
import com.mbox.administrator.namecardexchange.feature.exchange.view.activity.MyQrCodeActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class FragmentQrCode extends Fragment  implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;

    private static final String CARD_RESULT = "card_id";
    private static final String IS_ADD_DETAIL = "add_detail";

    public FragmentQrCode() {
        super();
    }

    public static FragmentQrCode newInstance() {
        FragmentQrCode fragment = new FragmentQrCode();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_exchange_qrcode, container, false);
//        setUi(rootView);
//        return rootView;
        mScannerView = new ZXingScannerView(getActivity());
        mScannerView.setAutoFocus(true);
        initInstances();
        return mScannerView;
    }



    private void setUi(View rootView) {

    }

    private void initInstances() {
        requestCameraPermission();
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
        mScannerView.setResultHandler(this);
        mScannerView.setAutoFocus(true);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
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

    public void requestCameraPermission() {
        Dexter.withActivity(getActivity())
                .withPermission(android.Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted
                        //TODO : Open Scan QR CODE
                        Toast.makeText(getApplicationContext(), "Permission already granted", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            ((ExchangeActivity)getActivity()).showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void handleResult(Result result) {
        Log.d("QRCodeScanner", result.getText());
        Log.d("QRCodeScanner", result.getBarcodeFormat().toString());

        QrCodeCardModel qrModel = new Gson().fromJson(result.getText(),QrCodeCardModel.class);

        Intent i = new Intent(getActivity(),CardDetailActivity.class);
        i.putExtra(CARD_RESULT,qrModel.getCardID());
        i.putExtra(IS_ADD_DETAIL,true);
        startActivity(i);

        Handler handler = new Handler();
        handler.postDelayed(() ->  {
            mScannerView.resumeCameraPreview(FragmentQrCode.this);
        }, 2000);
    }
}
