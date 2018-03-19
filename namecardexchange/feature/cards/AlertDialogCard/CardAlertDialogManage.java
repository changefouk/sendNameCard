package com.mbox.administrator.namecardexchange.feature.cards.AlertDialogCard;

import android.content.Context;

import com.mbox.administrator.namecardexchange.feature.cards.AlertDialogCard.contact.AlertDialogContact;
import com.mbox.administrator.namecardexchange.feature.cards.AlertDialogCard.facebook.AlertDialogSNS;
import com.mbox.administrator.namecardexchange.feature.cards.functionHepler.CardAttributeDefault;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardDataMdl;

/**
 * Created by Mangkorn Supparphai on 1/4/2018 AD.
 */

public class CardAlertDialogManage implements CardAttributeDefault {

    private Context context;
    private CardDataMdl cardDataMdl;

    public CardAlertDialogManage(Context context, CardDataMdl cardDataMdl){
        this.context = context;
        this.cardDataMdl = cardDataMdl;
        initInstance();
    }

    private void initInstance(){
        chooseDialog();
    }

    private void chooseDialog(){
        switch (cardDataMdl.getTypeData()){
            case TELEPHONE_TYPE:
                new AlertDialogContact(context, cardDataMdl).show();
                break;
            case FACEBOOK__TYPE:
            case TWITTER_TYPE:
            case YOUTUBE_TYPE:
            case GMAIL__TYPE:
                new AlertDialogSNS(context,cardDataMdl).show();
                break;
        }

    }


}
