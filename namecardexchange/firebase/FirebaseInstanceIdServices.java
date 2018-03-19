package com.mbox.administrator.namecardexchange.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Administrator on 23/11/2560.
 */

public class FirebaseInstanceIdServices extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIdServices";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        // TODO : SEND TOKEN TO SERVER TO PUSH NOTIFICATION

    }
}
