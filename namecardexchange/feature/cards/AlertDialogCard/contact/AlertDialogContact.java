package com.mbox.administrator.namecardexchange.feature.cards.AlertDialogCard.contact;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.cards.AlertDialogCard.contact.model.ContactDetailMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardDataMdl;


/**
 * Created by Mangkorn Supparphai on 12/28/2017 AD.
 */

public class AlertDialogContact implements LoaderManager.LoaderCallbacks<Cursor>{
    private Context context = null;
    private Dialog dialog = null;
    private TextView tv_data_alert = null;
    private ImageView img_data_alert = null;
    private CardDataMdl cardDataMdl;
    private TextView tv_contact_name = null;
    private ImageView img_contact_img = null;
    private View in_content;

    public AlertDialogContact(Context context, CardDataMdl cardDataMdl){
        this.context = context;
        this.cardDataMdl = cardDataMdl;
        setUi();
    }

    public AlertDialogContact(Context context) {
        this.context = context;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    private void setUi(){
        dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_card_contact);
        dialog.setCancelable(true);

        img_data_alert = dialog.findViewById(R.id.img_data_alert);
        tv_data_alert =  dialog.findViewById(R.id.tv_data_alert);

        tv_contact_name = dialog.findViewById(R.id.tv_contact_name);
        img_contact_img = dialog.findViewById(R.id.img_contact_img);

        in_content = dialog.findViewById(R.id.in_content);

        img_data_alert.setOnTouchListener(onTouchTelephone);


        initInstance();
    }

    private void initInstance(){
        if (cardDataMdl != null && !TextUtils.isEmpty(cardDataMdl.getDataText())){
            tv_data_alert.setText(cardDataMdl.getDataText());
            ContactDetailMdl contactDetailMdl = getContactName(cardDataMdl.getDataText(), context);
            if (contactDetailMdl != null && contactDetailMdl.getName() != null){
                tv_contact_name.setText(contactDetailMdl.getName());
                Glide.with(context).load(contactDetailMdl.getImageUri()).into(img_contact_img);
            }else {
                in_content.setVisibility(View.GONE);
            }

        }
    }


    private View.OnTouchListener onTouchTelephone = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view == img_data_alert) {
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
        }
    };


    public void show() {
        dialog.show();
    }

    private ContactDetailMdl getContactName(String phoneNumber, Context context) {

        phoneNumber = phoneNumber.replace("-", "");
        phoneNumber = phoneNumber.replace(" ", "");

        if (phoneNumber.substring(0,2).equals("02") && phoneNumber.length() > 9){
            phoneNumber = phoneNumber.substring(0, 9);
        }

        ContactDetailMdl contactDetailMdl = new ContactDetailMdl();

        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup.PHOTO_THUMBNAIL_URI};

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null) {
            if(cursor.moveToFirst()) {
                contactDetailMdl.setName(cursor.getString(0));
                contactDetailMdl.setImageUri(Uri.parse(cursor.getString(1)));
            }
            cursor.close();
        }


        return contactDetailMdl;
    }



}
