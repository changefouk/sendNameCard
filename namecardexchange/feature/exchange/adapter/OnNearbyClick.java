package com.mbox.administrator.namecardexchange.feature.exchange.adapter;

import android.view.View;

import com.mbox.administrator.namecardexchange.feature.exchange.model.ExchangeNearByModel;

/**
 * Created by Administrator on 15/12/2560.
 */

public interface OnNearbyClick {
    void onItemClick(View view, int position, ExchangeNearByModel model);
}
