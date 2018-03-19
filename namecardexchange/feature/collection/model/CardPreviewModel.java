package com.mbox.administrator.namecardexchange.feature.collection.model;

/**
 * Created by Administrator on 10/1/2561.
 */

public class  CardPreviewModel {

    private String cardId;
    private String urlimageCard;
    private int orientationCard;

    /**
     * KEY oritation
     * - PORTAIT = 1
     * - LANDSCAPE = 2
     */

    public CardPreviewModel(String cardId, String urlimageCard, int oritationCard) {
        this.cardId = cardId;
        this.urlimageCard = urlimageCard;
        this.orientationCard = oritationCard;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUrlimageCard() {
        return urlimageCard;
    }

    public void setUrlimageCard(String urlimageCard) {
        this.urlimageCard = urlimageCard;
    }

    public int getOritationCard() {
        return orientationCard;
    }

    public void setOritationCard(int oritationCard) {
        this.orientationCard = oritationCard;
    }
}
