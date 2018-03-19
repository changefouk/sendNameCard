package com.mbox.administrator.namecardexchange.feature.cards.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mbox.administrator.namecardexchange.feature.cards.functionHepler.CardAttributeDefault;
import com.mbox.administrator.namecardexchange.feature.cards.functionHepler.ManageViewFiles;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardViewMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.MockUpCard;

import java.util.List;

/**
 * Created by Mangkorn Supparphai on 12/15/2017 AD.
 */

public class CardActivityViewModel extends ViewModel implements CardAttributeDefault {

    private static final String TAG = CardActivityViewModel.class.getSimpleName();
    private MutableLiveData<CardMdl> cardMdl;
    private MutableLiveData<CardViewMdl> cardViewMdl;
    private MutableLiveData<Integer> cardSide;

    private CardMdl cardDatamodel;
    private CardViewMdl cardDataViewmodel;

    public LiveData<CardMdl> getCardMdl() {
        if (cardMdl == null) {
            cardMdl = new MutableLiveData<>();
//            setUpValueCardMdl();
        }

        return cardMdl;
    }

    public LiveData<CardViewMdl> getCardViewMdl() {
        if (cardViewMdl == null) {
            cardViewMdl = new MutableLiveData<>();
//            setValueUpCardViewMdl();
        }

        return cardViewMdl;
    }

    public LiveData<Integer> getCardSide() {

        Log.d(TAG, "getCardSide = ");
        if (cardSide == null) {
            cardSide = new MutableLiveData<>();
            setUpValueCardSide();
        }

        return cardSide;
    }

    public Integer getCardSideInteger() {
        return FRONT_SIDE;
    }

    private void setUpValueCardMdl(String idPreviewCard) {
        try {
            List<CardMdl> card = new MockUpCard().mockupListCardAll();
            for (int i = 0; i < card.size(); i++) {
                if (card.get(i).getCardID().equals(idPreviewCard)) {
                    if (cardMdl == null) {cardMdl = new MutableLiveData<>();}
                    this.cardMdl.setValue(card.get(i));
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "setUpValueUpCardViewMdl = " + e.getMessage());
        }

    }

    public void setValueUpCardViewMdl(CardMdl cardmodel) {
        try {
            this.cardViewMdl.setValue(new ManageViewFiles().readJsonFiles(cardmodel.getCardViewFile()));
        } catch (Exception e) {
            Log.d(TAG, "setValueUpCardViewMdl = " + e.getMessage());
        }

    }

    private void setUpValueCardSide() {
        try {
            Log.d(TAG, "setUpValueCardSide = ");
            this.cardSide.setValue(FRONT_SIDE);
        } catch (Exception e) {
            Log.d(TAG, "setUpValueUpCardViewMdl = " + e.getMessage());
        }
    }

    public void updateValueCardSide(int sideCard) {
        try {
//            getCardSide();
            this.cardSide.setValue(sideCard);
        } catch (Exception e) {
            Log.d(TAG, "setUpValueUpCardViewMdl = " + e.getMessage());
        }
    }

    public void setUpDataBegin(String idpreviewCard) {
        setUpValueCardMdl(idpreviewCard);
    }

    public void callServiceTest() {

    }

    public void setCardDatamodel(CardMdl cardDatamodel) {
        this.cardDatamodel = cardDatamodel;
    }

    public CardMdl getCardDatamodel() {
        return cardDatamodel;
    }

    public void setCardDataViewmodel(CardViewMdl cardDataViewmodel) {
        this.cardDataViewmodel = cardDataViewmodel;
    }

    public CardViewMdl getCardDataViewmodel() {
        return cardDataViewmodel;
    }

    private void setupValueCard() {
        cardMdl = new MutableLiveData<>();
    }

}
