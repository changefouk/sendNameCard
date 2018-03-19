package com.mbox.administrator.namecardexchange.feature.exchange.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.MockUpCard;

import java.util.List;

/**
 * Created by Administrator on 26/2/2561.
 */

public class FragmentSuggestViewmodel extends ViewModel {

    private static final String TAG = FragmentSuggestViewmodel.class.getSimpleName();

    private MutableLiveData<List<CardMdl>> cardList;

    public LiveData<List<CardMdl>> getSuggestCard() {
        if (cardList == null){
            cardList = new MutableLiveData<>();
            setMockDataCard();
        }
        return cardList;
    }

    private void setMockDataCard() {
        try {
            List<CardMdl> list = new MockUpCard().mockupLustSuggest();
            cardList.setValue(list);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

}
