package com.mbox.administrator.namecardexchange.feature.cards.AlertDialogCard.contact.model;

import android.content.Context;
import android.net.Uri;

/**
 * Created by Mangkorn Supparphai on 1/5/2018 AD.
 */

public class ContactDetailMdl {
    private String name;
    private Uri imageUri;


    public ContactDetailMdl (){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
