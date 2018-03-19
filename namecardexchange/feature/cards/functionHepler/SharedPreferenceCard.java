package com.mbox.administrator.namecardexchange.feature.cards.functionHepler;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Mangkorn Supparphai on 11/1/2018 AD.
 */

public class SharedPreferenceCard {

    public final String VALUE_EMPTY = "";
    private final String SP_NAME_SETTING_FOR_CARD = "SP_NAME_SETTING_FOR_CARD";
    private final String SP_KEY_PATH_FOLDER_VIEW_FILE = "SP_KEY_PATH_FOLDER_VIEW_FILE";

    //path view File
    public void setSpKeyParthViewFile(Context context, String VALUE){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME_SETTING_FOR_CARD, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_KEY_PATH_FOLDER_VIEW_FILE, VALUE);
        editor.apply();
        editor.commit();
        Log.d("setSpIsOnLine", "" + VALUE);

    }

    public String getSpKeyScreenDiagonal(Context context){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME_SETTING_FOR_CARD, Context.MODE_PRIVATE);
        return sp.getString(SP_KEY_PATH_FOLDER_VIEW_FILE, VALUE_EMPTY);
    }
}
