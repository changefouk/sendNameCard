package com.mbox.administrator.namecardexchange.feature.exchange.model;

/**
 * Created by Administrator on 15/12/2560.
 */

public class ExchangemenuModel {

    private int modelID;
    private String menuName;
    private String menuDescription;
    private String iconUrl;

    public ExchangemenuModel(int modelID, String menuName, String menuDescription,String iconUrl) {
        this.modelID = modelID;
        this.menuName = menuName;
        this.menuDescription = menuDescription;
        this.iconUrl = iconUrl;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}
