package com.mbox.administrator.namecardexchange.feature.cards.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mangkorn Supparphai on 12/22/2017 AD.
 */

public class CardViewPositionMdl {
    @SerializedName("CardDataCode")
    @Expose
    private String cardDataCode;

    @SerializedName("Color")
    @Expose
    private String color;

    @SerializedName("Font")
    @Expose
    private String font;

    @SerializedName("Top")
    @Expose
    private Integer top;

    @SerializedName("Left")
    @Expose
    private Integer left;

    @SerializedName("PicHigh")
    @Expose
    private Integer picHigh;

    @SerializedName("PicWide")
    @Expose
    private Integer picWide;

    @SerializedName("ShowDataText")
    @Expose
    private Boolean showDataText;

    @SerializedName("ShowImage")
    @Expose
    private Boolean showImage;

    @SerializedName("ShowTitleText")
    @Expose
    private Boolean showTitleText;

    @SerializedName("Style")
    @Expose
    private String style;

    @SerializedName("TextSize")
    @Expose
    private Integer textSize;

    @SerializedName("SideView")
    @Expose
    private Integer sideView;

    public CardViewPositionMdl(String cardDataCode, String color, String font, Integer top, Integer left, Integer picHigh, Integer picWide, Boolean showDataText, Boolean showImage, Boolean showTitleText, String style, Integer textSize, Integer sideView) {
        this.cardDataCode = cardDataCode;
        this.color = color;
        this.font = font;
        this.top = top;
        this.left = left;
        this.picHigh = picHigh;
        this.picWide = picWide;
        this.showDataText = showDataText;
        this.showImage = showImage;
        this.showTitleText = showTitleText;
        this.style = style;
        this.textSize = textSize;
        this.sideView = sideView;
    }

//    public CardViewPositionMdl(String cardDataCode, String color, String font, Integer top, Integer left, Integer picHigh, Integer picWide, Boolean showDataText, Boolean showImage, Boolean showTitleText, String style, Integer textSize) {
//        this.cardDataCode = cardDataCode;
//        this.color = color;
//        this.font = font;
//        this.top = top;
//        this.left = left;
//        this.picHigh = picHigh;
//        this.picWide = picWide;
//        this.showDataText = showDataText;
//        this.showImage = showImage;
//        this.showTitleText = showTitleText;
//        this.style = style;
//        this.textSize = textSize;
//    }

    public String getCardDataCode() {
        return cardDataCode;
    }

    public void setCardDataCode(String cardDataCode) {
        this.cardDataCode = cardDataCode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getPicHigh() {
        return picHigh;
    }

    public void setPicHigh(Integer picHigh) {
        this.picHigh = picHigh;
    }

    public Integer getPicWide() {
        return picWide;
    }

    public void setPicWide(Integer picWide) {
        this.picWide = picWide;
    }

    public Boolean getShowDataText() {
        return showDataText;
    }

    public void setShowDataText(Boolean showDataText) {
        this.showDataText = showDataText;
    }

    public Boolean getShowImage() {
        return showImage;
    }

    public void setShowImage(Boolean showImage) {
        this.showImage = showImage;
    }

    public Boolean getShowTitleText() {
        return showTitleText;
    }

    public void setShowTitleText(Boolean showTitleText) {
        this.showTitleText = showTitleText;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Integer getTextSize() {
        return textSize;
    }

    public void setTextSize(Integer textSize) {
        this.textSize = textSize;
    }

    public Integer getSideView() {
        return sideView;
    }

    public void setSideView(Integer sideView) {
        this.sideView = sideView;
    }
}
