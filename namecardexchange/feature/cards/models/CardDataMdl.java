package com.mbox.administrator.namecardexchange.feature.cards.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mangkorn Supparphai on 12/20/2017 AD.
 */

public class CardDataMdl {

    @SerializedName("cardDataID")
    @Expose
    private String cardDataID;

    @SerializedName("cardID")
    @Expose
    private String cardID;

    @SerializedName("cardDataCode")
    @Expose
    private String cardDataCode;

    @SerializedName("typeData")
    @Expose
    private String typeData;

    @SerializedName("dataText")
    @Expose
    private String dataText;

    @SerializedName("picPath")
    @Expose
    private String picPath;

    @SerializedName("titleText")
    @Expose
    private String titleText;

    @SerializedName("linkURL")
    @Expose
    private String linkURL;

    @SerializedName("showOnCard")
    @Expose
    private String showOnCard;

    @SerializedName("side")
    @Expose
    private Integer side;

    @SerializedName("stt")
    @Expose
    private String stt;

    public CardDataMdl(String cardDataID, String cardID, String cardDataCode, String typeData, String dataText, String picPath, String titleText, String linkURL, String showOnCard, Integer side, String stt) {
        this.cardDataID = cardDataID;
        this.cardID = cardID;
        this.cardDataCode = cardDataCode;
        this.typeData = typeData;
        this.dataText = dataText;
        this.picPath = picPath;
        this.titleText = titleText;
        this.linkURL = linkURL;
        this.showOnCard = showOnCard;
        this.side = side;
        this.stt = stt;
    }

    public CardDataMdl(String cardID, String typeData, String linkURL) {
        this.cardID = cardID;
        this.typeData = typeData;
        this.linkURL = linkURL;
    }

    public String getCardDataID() {
        return cardDataID;
    }

    public void setCardDataID(String cardDataID) {
        this.cardDataID = cardDataID;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardDataCode() {
        return cardDataCode;
    }

    public void setCardDataCode(String cardDataCode) {
        this.cardDataCode = cardDataCode;
    }

    public String getTypeData() {
        return typeData;
    }

    public void setTypeData(String typeData) {
        this.typeData = typeData;
    }

    public String getDataText() {
        return dataText;
    }

    public void setDataText(String dataText) {
        this.dataText = dataText;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public String getShowOnCard() {
        return showOnCard;
    }

    public void setShowOnCard(String showOnCard) {
        this.showOnCard = showOnCard;
    }

    public Integer getSide() {
        return side;
    }

    public void setSide(Integer side) {
        this.side = side;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }
}
