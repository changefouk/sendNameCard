package com.mbox.administrator.namecardexchange.feature.cards.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mangkorn Supparphai on 12/13/2017 AD.
 */

public class BUSIAuthMdl {

    @SerializedName("BUSIAuthID")
    @Expose
    private String BUSIAuthID;

    @SerializedName("linkURL")
    @Expose
    private String linkURL;

    @SerializedName("picPath")
    @Expose
    private String picPath;

    @SerializedName("licenseID")
    @Expose
    private String licenseID;

    @SerializedName("stt")
    @Expose
    private String stt;

    public BUSIAuthMdl() {
    }

    public BUSIAuthMdl(String BUSIAuthID, String linkURL, String picPath, String licenseID, String stt) {
        this.BUSIAuthID = BUSIAuthID;
        this.linkURL = linkURL;
        this.picPath = picPath;
        this.licenseID = licenseID;
        this.stt = stt;
    }

    public String getBUSIAuthID() {
        return BUSIAuthID;
    }

    public void setBUSIAuthID(String BUSIAuthID) {
        this.BUSIAuthID = BUSIAuthID;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getLicenseID() {
        return licenseID;
    }

    public void setLicenseID(String licenseID) {
        this.licenseID = licenseID;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }
}
