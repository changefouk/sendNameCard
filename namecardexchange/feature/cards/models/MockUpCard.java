package com.mbox.administrator.namecardexchange.feature.cards.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mbox.administrator.namecardexchange.feature.cards.functionHepler.CardAttributeDefault;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mangkorn Supparphai on 12/20/2017 AD.
 */

public class MockUpCard implements CardAttributeDefault, IDataMockUpCard {

    public static final String HORIZONTAL = "HORIZONTAL";
    public static final String VERTICAL = "VERTICAL";

    private final String SHOW = "T";
    private final String FRONTSIDE = "front";
    public static final String LAYOUTCARD = "{ \"cardId\": \"TestCard\", \"positionCard\": " + "[" +
            " { \"CardDataCode\": \"View1\", \"Color\": \"#196994\", \"Font\": \"supermarket_font\", \"Left\": 220, \"PicHigh\": 0, \"PicWide\": 0, \"ShowDataText\": true, \"ShowImage\": false, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 50, \"Top\": 50 }, " +
            "{ \"CardDataCode\": \"View2\", \"Color\": \"#797979\", \"Font\": \"supermarket_font\", \"Left\": 247, \"PicHigh\": 0, \"PicWide\": 0, \"ShowDataText\": true, \"ShowImage\": false, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 24, \"Top\": 111 }," +
            " { \"CardDataCode\": \"View3\", \"Color\": \"#\", \"Font\": \"?\", \"Left\": 320, \"PicHigh\": 25, \"PicWide\": 25, \"ShowDataText\": false, \"ShowImage\": true, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 0, \"Top\": 147 }, " +
            "{ \"CardDataCode\": \"View4\", \"Color\": \"#\", \"Font\": \"?\", \"Left\": 365, \"PicHigh\": 25, \"PicWide\": 25, \"ShowDataText\": false, \"ShowImage\": true, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 0, \"Top\": 147 }, " +
            "{ \"CardDataCode\": \"View5\", \"Color\": \"#\", \"Font\": \"?\", \"Left\": 415, \"PicHigh\": 25, \"PicWide\": 25, \"ShowDataText\": false, \"ShowImage\": true, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 0, \"Top\": 147 }, " +
            "{ \"CardDataCode\": \"View6\", \"Color\": \"#\", \"Font\": \"?\", \"Left\": 460, \"PicHigh\": 25, \"PicWide\": 25, \"ShowDataText\": false, \"ShowImage\": true, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 0, \"Top\": 147 }, " +
            "{ \"CardDataCode\": \"View7\", \"Color\": \"#666666\", \"Font\": \"supermarket_font\", \"Left\": 265, \"PicHigh\": 0, \"PicWide\": 0, \"ShowDataText\": true, \"ShowImage\": false, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 30, \"Top\": 206 }," +
            "{ \"CardDataCode\": \"View8\", \"Color\": \"#666666\", \"Font\": \"supermarket_font\", \"Left\": 320, \"PicHigh\": 0, \"PicWide\": 0, \"ShowDataText\": true, \"ShowImage\": false, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 24, \"Top\": 239 }," +
            "{ \"CardDataCode\": \"View9\", \"Color\": \"#FFFFFF\", \"Font\": \"supermarket_font\", \"Left\": 30, \"PicHigh\": 0, \"PicWide\": 0, \"ShowDataText\": true, \"ShowImage\": false, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 24, \"Top\": 310 }, " +
            "{ \"CardDataCode\": \"View10\", \"Color\": \"#FFFFFF\", \"Font\": \"supermarket_font\", \"Left\": 30, \"PicHigh\": 0, \"PicWide\": 0, \"ShowDataText\": true, \"ShowImage\": false, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 18, \"Top\": 350 }, " +
            "{ \"CardDataCode\": \"View11\", \"Color\": \"#FFFFFF\", \"Font\": \"supermarket_font\", \"Left\": 440, \"PicHigh\": 30, \"PicWide\": 0, \"ShowDataText\": true, \"ShowImage\": true, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 20, \"Top\": 310 }, " +
            "{ \"CardDataCode\": \"View12\", \"Color\": \"#FFFFFF\", \"Font\": \"supermarket_font\", \"Left\": 440, \"PicHigh\": 30, \"PicWide\": 0, \"ShowDataText\": true, \"ShowImage\": true, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 20, \"Top\": 350 }, " +
            "{ \"CardDataCode\": \"View13\", \"Color\": \"#FFFFFF\", \"Font\": \"supermarket_font\", \"Left\": 440, \"PicHigh\": 30, \"PicWide\": 0, \"ShowDataText\": true, \"ShowImage\": true, \"ShowTitleText\": false, \"Style\": \"!\", \"TextSize\": 20, \"Top\": 390 } ] }";

    private BUSIAuthMdl busiAuthMdl = new BUSIAuthMdl("", "http://mbox.co.th/", "http://192.168.0.3/card/img/auth_logo_mbox.png", "", "");
    private CardThemeMdl cardThemeMdl = new CardThemeMdl("1a6ce534-013e-4714-84d7-d07af8c21c0e",
            "", "", "http://192.168.0.3/card/img/card_themes/F_1a6ce534-013e-4714-84d7-d07af8c21c0e.jpg", "", "", "");

    public List<CardMdl> mockupListCardAll() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        CardMdl card = gson.fromJson(CARD_1, CardMdl.class);
        CardMdl card2 = gson.fromJson(CARD_2, CardMdl.class);
        CardMdl card3 = gson.fromJson(CARD_3, CardMdl.class);
        CardMdl card4 = gson.fromJson(tempCardMang, CardMdl.class);
        List<CardMdl> cardMdls = new ArrayList<>();
        cardMdls.add(card);
        cardMdls.add(card2);
        cardMdls.add(card3);
        cardMdls.add(card4);
        return cardMdls;
    }

    public List<CardMdl> mockupListCardIOKE() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        CardMdl card = gson.fromJson(CARD_1, CardMdl.class);
        CardMdl card2 = gson.fromJson(CARD_2, CardMdl.class);
        CardMdl card3 = gson.fromJson(CARD_3, CardMdl.class);
        List<CardMdl> cardMdls = new ArrayList<>();
        cardMdls.add(card);
        cardMdls.add(card2);
        cardMdls.add(card3);
        return cardMdls;
    }

    public List<CardMdl> mockupListCardM() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        CardMdl card2 = gson.fromJson(CARD_2, CardMdl.class);
        CardMdl card3 = gson.fromJson(CARD_3, CardMdl.class);
        CardMdl card4 = gson.fromJson(tempCardMang, CardMdl.class);
        List<CardMdl> cardMdls = new ArrayList<>();
        cardMdls.add(card2);
        cardMdls.add(card3);
        cardMdls.add(card4);
        return cardMdls;
    }

    public List<CardMdl> mockupLustSuggest(){
        Gson gson = new GsonBuilder().serializeNulls().create();
        CardMdl card = gson.fromJson(CARD_1, CardMdl.class);
        CardMdl card4 = gson.fromJson(tempCardMang, CardMdl.class);
        List<CardMdl> cardMdls = new ArrayList<>();
        cardMdls.add(card);
        cardMdls.add(card4);
        return cardMdls;
    }

}
