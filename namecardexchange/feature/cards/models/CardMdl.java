package com.mbox.administrator.namecardexchange.feature.cards.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mangkorn Supparphai on 12/12/2017 AD.
 */

public class CardMdl {
    @SerializedName("cardID")
    @Expose
    private String cardID;

    @SerializedName("memberID")
    @Expose
    private String memberID;

    @SerializedName("deviceID")
    @Expose
    private String deviceID;

    @SerializedName("cardDataMdlArrayList")
    @Expose
    private List<CardDataMdl> cardDataMdlArrayList;

    @SerializedName("cardViewFile")
    @Expose
    private String cardViewFile;

    @SerializedName("cardThemeMdl")
    @Expose
    private CardThemeMdl cardThemeMdl;

    @SerializedName("licenseID")
    @Expose
    private String licenseID;

    @SerializedName("BUSIAuthMdl")
    @Expose
    private BUSIAuthMdl BUSIAuthMdl;

    @SerializedName("cardCode")
    @Expose
    private String cardCode;

    @SerializedName("cardName")
    @Expose
    private String cardName;

    @SerializedName("cardURLPreview")
    @Expose
    private String cardURLPreview;

    @SerializedName("expireDate")
    @Expose
    private String expireDate;

    @SerializedName("stt")
    @Expose
    private String stt;

    @SerializedName("commentRefer")
    @Expose
    private String commentRefer;

    @SerializedName("updateDate")
    @Expose
    private String updateDate;

    @SerializedName("createDate")
    @Expose
    private String createDate;

    @SerializedName("cardOrientation")
    private int cardOrientation; /** 1 = portait, 2 = landscape */

    public CardMdl() {
    }

    public CardMdl(String cardID, String memberID, String deviceID, List<CardDataMdl> cardDataMdlArrayList, String cardViewFile, CardThemeMdl cardThemeMdl, String licenseID, com.mbox.administrator.namecardexchange.feature.cards.models.BUSIAuthMdl BUSIAuthMdl, String cardCode, String cardName, String cardURLPreview, String expireDate, String stt, String commentRefer, String updateDate, String createDate, int cardOrientation) {
        this.cardID = cardID;
        this.memberID = memberID;
        this.deviceID = deviceID;
        this.cardDataMdlArrayList = cardDataMdlArrayList;
        this.cardViewFile = cardViewFile;
        this.cardThemeMdl = cardThemeMdl;
        this.licenseID = licenseID;
        this.BUSIAuthMdl = BUSIAuthMdl;
        this.cardCode = cardCode;
        this.cardName = cardName;
        this.cardURLPreview = cardURLPreview;
        this.expireDate = expireDate;
        this.stt = stt;
        this.commentRefer = commentRefer;
        this.updateDate = updateDate;
        this.createDate = createDate;
        this.cardOrientation = cardOrientation;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public List<CardDataMdl> getCardDataMdlArrayList() {
        return cardDataMdlArrayList;
    }

    public void setCardDataMdlArrayList(List<CardDataMdl> cardDataMdlArrayList) {
        this.cardDataMdlArrayList = cardDataMdlArrayList;
    }

    public String getCardViewFile() {
        return cardViewFile;
    }

    public void setCardViewFile(String cardViewFile) {
        this.cardViewFile = cardViewFile;
    }

    public CardThemeMdl getCardThemeMdl() {
        return cardThemeMdl;
    }

    public void setCardThemeMdl(CardThemeMdl cardThemeMdl) {
        this.cardThemeMdl = cardThemeMdl;
    }

    public String getLicenseID() {
        return licenseID;
    }

    public void setLicenseID(String licenseID) {
        this.licenseID = licenseID;
    }

    public BUSIAuthMdl getBUSIAuthMdl() {
        return BUSIAuthMdl;
    }

    public void setBUSIAuthMdl(BUSIAuthMdl BUSIAuthMdl) {
        this.BUSIAuthMdl = BUSIAuthMdl;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardURLPreview() {
        return cardURLPreview;
    }

    public void setCardURLPreview(String cardURLPreview) {
        this.cardURLPreview = cardURLPreview;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getCommentRefer() {
        return commentRefer;
    }

    public void setCommentRefer(String commentRefer) {
        this.commentRefer = commentRefer;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCardOrientation() {
        return cardOrientation;
    }

    public void setCardOrientation(int cardOrientation) {
        this.cardOrientation = cardOrientation;
    }
}
