package com.mbox.administrator.namecardexchange.feature.cards.AlertDialogCard.facebook;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.BaseActivity;
import com.mbox.administrator.namecardexchange.feature.cards.AlertDialogCard.contact.model.ContactDetailMdl;
import com.mbox.administrator.namecardexchange.feature.cards.functionHepler.CardAttributeDefault;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardDataMdl;


/**
 * Created by Mangkorn Supparphai on 12/28/2017 AD.
 */

public class AlertDialogSNS implements LoaderManager.LoaderCallbacks<Cursor>, CardAttributeDefault {

    private Context mContext;
    private Dialog mDialog;
    private TextView tvData;
    private ImageView imgData;
    private CardDataMdl model;


    public AlertDialogSNS(Context mContext, CardDataMdl model) {
        this.mContext = mContext;
        this.model = model;
        setUi();
    }

    private void setUi() {
        mDialog = new Dialog(mContext);
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.custom_dialog_facebook);
        mDialog.setCancelable(true);

        tvData = mDialog.findViewById(R.id.tv_alert_facebook);
        imgData = mDialog.findViewById(R.id.image_alert_facebook);

        imgData.setOnTouchListener(onTouchImage);
        imgData.setOnClickListener(view -> onButtonSNSClick());
        initInstance();
    }

    private void initInstance() {
        if (model != null && !TextUtils.isEmpty(model.getDataText())) {
            switch (model.getTypeData()) {
                case FACEBOOK__TYPE:
                    tvData.setText(model.getDataText());
                    Glide.with(mContext).load(model.getPicPath()).into(imgData);
                    break;
                case TWITTER_TYPE:

                    break;
                case GMAIL__TYPE:

                    break;
                case YOUTUBE_TYPE:

                    break;
            }
        }
    }

    private View.OnTouchListener onTouchImage = (view, motionEvent) -> {
        if (view == imgData) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    view.setAlpha((float) 0.2);
                    break;
                case MotionEvent.ACTION_UP:
                    view.setAlpha((float) 1.0);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    view.setAlpha((float) 1.0);
                    break;
            }
        }
        return true;
    };

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public void show() {
        mDialog.show();
    }

    private void onButtonSNSClick() {
        Log.d("FLUKE ALERT","ALERT CLICK");
        switch (model.getTypeData()) {
            case FACEBOOK__TYPE:
                Intent i = getOpenFacebookIntent(mContext, model.getLinkURL());
                mContext.startActivity(i);
                break;
            case TWITTER_TYPE:

                break;
            case GMAIL__TYPE:

                break;
            case YOUTUBE_TYPE:

                break;
        }
    }

    public static Intent getOpenFacebookIntent(Context context, String url) {
        String facebookuser = "https://www.facebook.com/" + url;
        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://facewebmodal/f?href=" + url)); //Trys to make intent with FB's URI
        } catch (Exception e) {
            Log.d("FACEBOOK", "facebook : NO application intent to web");
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(facebookuser)); //catches and opens a url to the desired page
        }
    }
}
