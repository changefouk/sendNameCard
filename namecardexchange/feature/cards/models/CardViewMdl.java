package com.mbox.administrator.namecardexchange.feature.cards.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CardViewMdl {

    //Model card view file

    @SerializedName("cardId")
    private String cardId;

    @SerializedName("positionCard")
    private List<CardViewPositionMdl> cardViewPositionMdlList;

    public CardViewMdl(String cardId, List<CardViewPositionMdl> cardViewPositionMdlsList) {
        this.cardId = cardId;
        this.cardViewPositionMdlList = cardViewPositionMdlsList;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public List<CardViewPositionMdl> getCardViewPositionMdlList() {
        return cardViewPositionMdlList;
    }

    public void setCardViewPositionMdlList(List<CardViewPositionMdl> cardViewPositionMdlList) {
        this.cardViewPositionMdlList = cardViewPositionMdlList;
    }
}
