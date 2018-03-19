package com.mbox.administrator.namecardexchange.base;

import android.arch.lifecycle.ViewModel;

import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.MockUpCard;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 27/11/2560.
 */

public abstract class BaseViewModel extends ViewModel {

    private static final int MOCK_MEMBER1 = 0;
    private static final int MOCK_MEMBER2 = 1;
    private static final int MOCK_ALL = 2;
    private static final int MOCK_SUGGEST = 3;

    public final String memberID = "memberID-bbbf-4bac-be5d-9e0723002a52";
    public final String memberID2 = "7964efe0-6976-4522-8d82-1de12b36f846";

    public List<CardMdl> getMockData(int valueMock) {
        List<CardMdl> mockData;
        switch (valueMock) {
            case MOCK_MEMBER1:
                mockData = new MockUpCard().mockupListCardIOKE();
                return mockData;
            case MOCK_MEMBER2:
                mockData = new MockUpCard().mockupListCardM();
                return mockData;
            case MOCK_ALL:
                mockData = new MockUpCard().mockupListCardAll();
                return mockData;
            default:
                return null;
        }
    }

    public int getMockValue(String memberId, int mockValue) {
        if (memberId.equals(memberID)) {
            if (mockValue == 0) {
                return MOCK_MEMBER1;
            } else {
                return MOCK_ALL;
            }
        } else {
            if (mockValue == 0) {
                return MOCK_MEMBER2;
            } else {
                return MOCK_ALL;
            }
        }
    }

}
