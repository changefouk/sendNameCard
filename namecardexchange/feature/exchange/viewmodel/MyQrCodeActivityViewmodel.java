package com.mbox.administrator.namecardexchange.feature.exchange.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.MockUpCard;

import java.util.List;

/**
 * Created by Administrator on 15/2/2561.
 */

public class MyQrCodeActivityViewmodel extends ViewModel {

    private static final String TAG = MyQrCodeActivityViewmodel.class.getSimpleName();
    public MutableLiveData<List<CardMdl>> cardlist;

    private static final int PORTAIT = 1;
    private static final int LANDSCAPE = 2;

    public LiveData<List<CardMdl>> getPreviewCard() {
        if (cardlist == null) {
            cardlist = new MutableLiveData<>();
            setMockDataCard();
        }
        return cardlist;
    }

    private void setMockDataCard() {
        try {
            List<CardMdl> list = new MockUpCard().mockupListCardAll();
            cardlist.setValue(list);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}
