package com.mbox.administrator.namecardexchange.feature.collection.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.interfaces.OnitemClick;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;
import com.mbox.administrator.namecardexchange.feature.collection.model.CardPreviewModel;

import java.util.List;

/**
 * Created by Administrator on 10/1/2561.
 */

public class PreviewCardAdapter extends RecyclerView.Adapter<PreviewCardAdapter.ViewHolder> {

    private List<CardMdl> list;
    private Context mContext;
    private OnitemClick onitemClick;

    private static final int CARD_ORIENTATION_VERTICAL = 1;
    private static final int CARD_ORIENTATION_HORIZONTAL = 2;

    public PreviewCardAdapter(List<CardMdl> list, Context mContext, OnitemClick onitemClick) {
        this.list = list;
        this.mContext = mContext;
        this.onitemClick = onitemClick;
    }

    @Override
    public PreviewCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case CARD_ORIENTATION_VERTICAL:
                return new PreviewCardAdapter.ViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_preview_card_vertical
                                , parent
                                , false));
            case CARD_ORIENTATION_HORIZONTAL:
                return new PreviewCardAdapter.ViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_preview_card_horizontal
                                , parent
                                , false));
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getCardOrientation() == CARD_ORIENTATION_VERTICAL) {
            return CARD_ORIENTATION_VERTICAL;
        } else {
            return CARD_ORIENTATION_HORIZONTAL;
        }
    }

    @Override
    public void onBindViewHolder(PreviewCardAdapter.ViewHolder holder, int position) {
        CardMdl model = list.get(position);
//        String url = "http://192.168.0.3/card/img/card_preview/"+model.getCardId()+".png";
        Glide.with(mContext)
                .load(model.getCardURLPreview())
                .into(holder.previewCard);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView previewCard;

        public ViewHolder(View itemView) {
            super(itemView);
            previewCard = itemView.findViewById(R.id.preview_card_image);
            previewCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onitemClick != null) onitemClick.onItemClick(view, getAdapterPosition());
        }
    }

    public void updateData(List<CardMdl> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}