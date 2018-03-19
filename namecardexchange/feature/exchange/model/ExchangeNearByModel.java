package com.mbox.administrator.namecardexchange.feature.exchange.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 20/2/2561.
 */

public class ExchangeNearByModel {

    @SerializedName("cardId")
    @Expose
    private String cardId;

    @SerializedName("imgurlCard")
    @Expose
    private String imgurlCard;

    @SerializedName("cardOrientation")
    @Expose
    private int cardOrientation;

    public ExchangeNearByModel(String cardID, String imgurlCard, int cardOrientation) {
        this.cardId = cardID;
        this.imgurlCard = imgurlCard;
        this.cardOrientation = cardOrientation;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getImgurlCard() {
        return imgurlCard;
    }

    public void setImgurlCard(String imgurlCard) {
        this.imgurlCard = imgurlCard;
    }

    public int getCardOrientation() {
        return cardOrientation;
    }

    public void setCardOrientation(int cardOrientation) {
        this.cardOrientation = cardOrientation;
    }
}
