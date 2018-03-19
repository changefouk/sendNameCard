package com.mbox.administrator.namecardexchange.feature.profile.model;

/**
 * Created by Administrator on 11/1/2561.
 */

public class ProfileMenuModel {

    private int menuID;
    private String menuName;
    private String menuIconUrl;

    public ProfileMenuModel(int menuID, String menuName, String menuicon) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.menuIconUrl = menuicon;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIconUrl() {
        return menuIconUrl;
    }

    public void setMenuIconUrl(String menuIconUrl) {
        this.menuIconUrl = menuIconUrl;
    }
}
