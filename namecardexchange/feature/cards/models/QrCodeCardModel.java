package com.mbox.administrator.namecardexchange.feature.cards.models;

/**
 * Created by Administrator on 15/2/2561.
 */

public class QrCodeCardModel {

    private String cardID;
    private String cardURLPreview;

    public QrCodeCardModel(String cardID, String cardURLPreview) {
        this.cardID = cardID;
        this.cardURLPreview = cardURLPreview;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardURLPreview() {
        return cardURLPreview;
    }

    public void setCardURLPreview(String cardURLPreview) {
        this.cardURLPreview = cardURLPreview;
    }
}
