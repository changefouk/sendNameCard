package com.mbox.administrator.namecardexchange;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Administrator on 24/11/2560.
 */

public class MyApplication extends Application {

    private Fabric fabric;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)           // Enables Crashlytics debugger
                .build();
//        Fabric.with(fabric);
//        Fabric.with(this, new Crashlytics());
    }

    public static Context getContext(){
        return mContext;
    }
}
