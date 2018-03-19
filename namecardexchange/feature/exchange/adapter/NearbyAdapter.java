package com.mbox.administrator.namecardexchange.feature.exchange.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.interfaces.OnitemClick;
import com.mbox.administrator.namecardexchange.feature.exchange.model.ExchangeNearByModel;
import com.mbox.administrator.namecardexchange.util.ConvertPxtoDp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 19/12/2560.
 */

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.ViewHolder>  {

    public final List<ExchangeNearByModel> list = new ArrayList<>();
    private Context mContext;
    private OnNearbyClick mOnitemClick;

    public NearbyAdapter(Context mContext, OnNearbyClick onitemClick) {
        this.mContext = mContext;
        this.mOnitemClick = onitemClick;
    }

    @Override
    public NearbyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.custom_list_detail_card_preview, parent, false);
        return new ViewHolder(itemView);
    }

    public void nearbyLost(ExchangeNearByModel model) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCardId().equals(model.getCardId())) {
                list.remove(i);
            }
        }
        notifyDataSetChanged();
    }

    public void nearbyFound(ExchangeNearByModel model) {
        this.list.add(model);
        notifyDataSetChanged();
    }

    public void nearbyClose(){
        this.list.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(NearbyAdapter.ViewHolder holder, int position) {
        ExchangeNearByModel model = list.get(position);
        Glide.with(mContext).load(model.getImgurlCard()).into(holder.imageView);
        holder.setupCardPreview(model.getCardOrientation());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView cardView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_namecard);
            imageView = itemView.findViewById(R.id.image_previewcard);
            imageView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (mOnitemClick != null) mOnitemClick.onItemClick(view,getAdapterPosition(),list.get(getAdapterPosition()));
        }
        public void setupCardPreview(int oritationCard) {
            switch (oritationCard) {
                case 1:
                    imageView.getLayoutParams().height = (int) ConvertPxtoDp.convertDpToPixel(270, mContext);
                    imageView.getLayoutParams().width = (int) ConvertPxtoDp.convertDpToPixel(160, mContext);
                    imageView.requestLayout();
                    break;
                case 2:
                    imageView.getLayoutParams().height = (int) ConvertPxtoDp.convertDpToPixel(160, mContext);
                    imageView.getLayoutParams().width = (int) ConvertPxtoDp.convertDpToPixel(270, mContext);
                    imageView.requestLayout();
            }
        }

    }
}
