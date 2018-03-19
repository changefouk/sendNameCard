package com.mbox.administrator.namecardexchange.feature.collection.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mbox.administrator.namecardexchange.feature.cards.functionHepler.CardAttributeDefault;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardDataMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.cards.models.MockUpCard;

import java.util.List;

/**
 * Created by Administrator on 11/1/2561.
 */

public class FragmentCardDetailViewmodel extends ViewModel implements CardAttributeDefault {

    private static final String TAG = FragmentCardDetailViewmodel.class.getSimpleName();

    private MutableLiveData<List<CardDataMdl>> cardDataDetail;
    private CardMdl cardMdl;

    public LiveData<List<CardDataMdl>> getPreviewCard() {
        if (cardDataDetail == null) {
            cardDataDetail = new MutableLiveData<>();
        }
        return cardDataDetail;
    }

    public void setDetailData(String cardID) {
        if (cardDataDetail == null) {
            cardDataDetail = new MutableLiveData<>();
        }
        List<CardMdl> card = new MockUpCard().mockupListCardAll();
        for (int i = 0; i < card.size(); i++) {
            if (card.get(i).getCardID().equals(cardID)) {
                this.cardMdl = card.get(i);
                List<CardDataMdl> cardDataMdls = card.get(i).getCardDataMdlArrayList();
                CardDataMdl preview = new CardDataMdl(card.get(i).getCardID(), PREVIEW_CARD_TYPE, card.get(i).getCardURLPreview());
                if (!cardDataMdls.get(0).getTypeData().equals(PREVIEW_CARD_TYPE)) {
                    cardDataMdls.add(0, preview);
                }
                cardDataDetail.setValue(cardDataMdls);
            }
        }
    }

    public CardMdl getCardMdl() {
        return cardMdl;
    }
}
