package com.mbox.administrator.namecardexchange.feature.exchange.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.mbox.administrator.namecardexchange.MyApplication;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.BaseViewModel;
import com.mbox.administrator.namecardexchange.feature.exchange.model.ExchangemenuModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 15/12/2560.
 */

public class FragmentExchangeViewmodel extends BaseViewModel {

    private static final String TAG = "FragmentExchange";
    public MutableLiveData<List<ExchangemenuModel>> menu;

    public enum menuID {FIND_NEAR, FIND_QR_CODE, FIND_ID, FIND_SUGGEST}

    public LiveData<List<ExchangemenuModel>> getMenuList() {
        if (menu == null) {
            menu = new MutableLiveData<>();
            setvalueModel();
        }
        return menu;
    }

    private void setvalueModel() {
        try {
            List<ExchangemenuModel> modelList = new ArrayList<>();
            modelList.add(new ExchangemenuModel(menuID.FIND_NEAR.ordinal()
                    , MyApplication.getContext().getString(R.string.exchange_title_find_near_card)
                    , MyApplication.getContext().getString(R.string.exchange_detail_find_near_card)
                    , "http://192.168.0.3/card/img/ic_radar_nearby.png"));
            modelList.add(new ExchangemenuModel(menuID.FIND_QR_CODE.ordinal()
                    , MyApplication.getContext().getString(R.string.exchange_title_qr_code)
                    , MyApplication.getContext().getString(R.string.exchange_detail_qr_code)
                    , "http://192.168.0.3/card/img/ic_qr_code.png"));
            modelList.add(new ExchangemenuModel(menuID.FIND_ID.ordinal()
                    , MyApplication.getContext().getString(R.string.exchange_title_find_with_id)
                    , MyApplication.getContext().getString(R.string.exchange_detail_find_with_id)
                    , "http://192.168.0.3/card/img/ic_search_id.png"));
            modelList.add(new ExchangemenuModel(menuID.FIND_SUGGEST.ordinal()
                    , MyApplication.getContext().getString(R.string.exchange_title_suggest_card)
                    , MyApplication.getContext().getString(R.string.exchange_detail_suggest_card)
                    , "http://192.168.0.3/card/img/ic_suggest.png"));
            menu.setValue(modelList);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }
}
