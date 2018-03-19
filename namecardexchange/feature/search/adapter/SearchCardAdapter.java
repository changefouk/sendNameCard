package com.mbox.administrator.namecardexchange.feature.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.interfaces.OnitemClick;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardMdl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 4/1/2561.
 */

public class SearchCardAdapter extends RecyclerView.Adapter<SearchCardAdapter.ViewHolder> implements Filterable,View.OnClickListener {

    private List<CardMdl> mList;
    private List<CardMdl> mFilter;

    private Context mContext;
    private OnFilterClick onitemClick;
    private int position;

    private static final int CARD_ORIENTATION_VERTICAL = 1;
    private static final int CARD_ORIENTATION_HORIZONTAL = 2;

    public SearchCardAdapter(List<CardMdl> mList, Context mContext, OnFilterClick onitemClick) {
        this.mList = mList;
        this.mContext = mContext;
        this.onitemClick = onitemClick;
    }

    @Override
    public SearchCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case CARD_ORIENTATION_VERTICAL:
                return new SearchCardAdapter.ViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_preview_card_vertical
                                , parent
                                , false));
            case CARD_ORIENTATION_HORIZONTAL:
                return new SearchCardAdapter.ViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_preview_card_horizontal
                                , parent
                                , false));
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mFilter.get(position).getCardOrientation() == CARD_ORIENTATION_VERTICAL) {
            return CARD_ORIENTATION_VERTICAL;
        } else {
            return CARD_ORIENTATION_HORIZONTAL;
        }
    }

    @Override
    public void onBindViewHolder(SearchCardAdapter.ViewHolder holder, int position) {
        this.position = position;
        CardMdl model = mFilter.get(position);
        String url = "http://192.168.0.3/card/img/card_preview/" + model.getCardID() + ".png";
        Glide.with(mContext)
                .load(url)
                .into(holder.previewCard);

        holder.previewCard.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mFilter == null ? 0 : mFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilter = mList;
                } else {
                    List<CardMdl> filterList = new ArrayList<>();
                    for (int j = 0; j < mList.size(); j++) {
                        CardMdl cardMdl = mList.get(j);
                        for (int i = 0; i < cardMdl.getCardDataMdlArrayList().size(); i++) {
                            if (cardMdl.getCardDataMdlArrayList().get(i).getDataText().contains(charString)) {
                                filterList.add(cardMdl);
                            }
                        }
                    }
                    mFilter = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilter = (List<CardMdl>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onClick(View view) {
        if (onitemClick != null) onitemClick.onItemClick(view, position,mFilter.get(position).getCardID());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView previewCard;

        public ViewHolder(View itemView) {
            super(itemView);
            previewCard = itemView.findViewById(R.id.preview_card_image);
        }

    }
}
