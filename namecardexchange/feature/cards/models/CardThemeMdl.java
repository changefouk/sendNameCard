package com.mbox.administrator.namecardexchange.feature.cards.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mangkorn Supparphai on 12/12/2017 AD.
 */

public class CardThemeMdl {
    @SerializedName("cardThemeID")
    @Expose
    private String cardThemeID;

    @SerializedName("cardThemeCode")
    @Expose
    private String cardThemeCode;

    @SerializedName("cardThemeName")
    @Expose
    private String cardThemeName;


    @SerializedName("frontTheme")
    @Expose
    private String frontTheme;

    @SerializedName("backTheme")
    @Expose
    private String backTheme;

    @SerializedName("watermark")
    @Expose
    private String watermark;

    @SerializedName("licenseID")
    @Expose
    private String licenseID;


    public CardThemeMdl(String cardThemeID, String cardThemeCode, String cardThemeName, String frontTheme, String backTheme, String watermark, String licenseID) {
        this.cardThemeID = cardThemeID;
        this.cardThemeCode = cardThemeCode;
        this.cardThemeName = cardThemeName;
        this.frontTheme = frontTheme;
        this.backTheme = backTheme;
        this.watermark = watermark;
        this.licenseID = licenseID;
    }

    public String getCardThemeID() {
        return cardThemeID;
    }

    public void setCardThemeID(String cardThemeID) {
        this.cardThemeID = cardThemeID;
    }

    public String getCardThemeCode() {
        return cardThemeCode;
    }

    public void setCardThemeCode(String cardThemeCode) {
        this.cardThemeCode = cardThemeCode;
    }

    public String getCardThemeName() {
        return cardThemeName;
    }

    public void setCardThemeName(String cardThemeName) {
        this.cardThemeName = cardThemeName;
    }

    public String getFrontTheme() {
        return frontTheme;
    }

    public void setFrontTheme(String frontTheme) {
        this.frontTheme = frontTheme;
    }

    public String getBackTheme() {
        return backTheme;
    }

    public void setBackTheme(String backTheme) {
        this.backTheme = backTheme;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public String getLicenseID() {
        return licenseID;
    }

    public void setLicenseID(String licenseID) {
        this.licenseID = licenseID;
    }
}
