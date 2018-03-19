package com.mbox.administrator.namecardexchange.feature.collection.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.mbox.administrator.namecardexchange.base.BaseViewModel;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.MockUpCard;

import java.util.List;

/**
 * Created by Administrator on 10/1/2561.
 */

public class FragmentFriendcardViewModel extends BaseViewModel {

    private static final String TAG = FragmentFriendcardViewModel.class.getSimpleName();
    public MutableLiveData<List<CardMdl>> cardlist;

    private static final int PORTAIT = 1;
    private static final int LANDSCAPE = 2;

    public LiveData<List<CardMdl>> getPreviewCard(String memberID,int valueAdd) {
        setMockDataCard(memberID,valueAdd);
        return cardlist;
    }

    private void setMockDataCard(String memberID,int valueAdd) {
        try {
            cardlist = new MutableLiveData<>();
            int value = getMockValue(memberID,valueAdd);
            List<CardMdl> list = getMockData(value);
            cardlist.setValue(list);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}
