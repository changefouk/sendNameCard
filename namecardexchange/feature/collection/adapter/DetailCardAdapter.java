package com.mbox.administrator.namecardexchange.feature.collection.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.base.interfaces.OnitemClick;
import com.mbox.administrator.namecardexchange.feature.cards.functionHepler.CardAttributeDefault;
import com.mbox.administrator.namecardexchange.feature.cards.models.CardDataMdl;
import com.mbox.administrator.namecardexchange.util.ConvertPxtoDp;

import java.util.List;

/**
 * Created by Administrator on 10/1/2561.
 */

public class DetailCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CardAttributeDefault {

    private static final String TAG = DetailCardAdapter.class.getSimpleName();

    private static final int VIEWTYPE_TEXT = 1;
    private static final int VIEWTYPE_FACEBOOK = 2;
    private static final int VIEWTYPE_TWITTER = 3;
    private static final int VIEWTYPE_YOUTUBE = 4;
    private static final int VIEWTYPE_GMAIL = 5;
    private static final int VIEWTYPE_ADDRESS = 6;
    private static final int VIEWTYPE_TELEPHONE = 7;
    private static final int VIEWTYPE_EMAIL = 8;
    private static final int VIEWTYPE_WEB = 9;
    private static final int VIEWTYPE_LINE = 10;
    private static final int VIEWTYPE_PREVIEW = 11;

    private int ORIENTATIONCARD;

    private List<CardDataMdl> list;
    private Context mContext;
    private OnitemClick onitemClick;

    public DetailCardAdapter(List<CardDataMdl> list, Context mContext, OnitemClick onitemClick) {
        this.list = list;
        this.mContext = mContext;
        this.onitemClick = onitemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEWTYPE_TEXT:
                return new DataViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_detail_card_text
                                , parent
                                , false));
            case VIEWTYPE_FACEBOOK:
                return new DataViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_detail_card_contact_facebook
                                , parent
                                , false));
            case VIEWTYPE_TWITTER:
                return new DataViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_detail_card_contact_twitter
                                , parent
                                , false));
            case VIEWTYPE_YOUTUBE:
                return new DataViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_detail_card_contact_youtube
                                , parent
                                , false));
            case VIEWTYPE_GMAIL:
                return new DataViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_detail_card_contact_email
                                , parent
                                , false));
            case VIEWTYPE_ADDRESS:
                return new DataViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_detail_card_address
                                , parent
                                , false));
            case VIEWTYPE_TELEPHONE:
                return new DataViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_detail_card_contact_phone
                                , parent
                                , false));
            case VIEWTYPE_EMAIL:
                return new DataViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_detail_card_contact_email
                                , parent
                                , false));
            case VIEWTYPE_WEB:
                return new DataViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_detail_card_contact_website
                                , parent
                                , false));
            case VIEWTYPE_PREVIEW:
                return new PreViewViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.custom_list_detail_card_preview
                                , parent
                                , false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEWTYPE_PREVIEW) {
            final PreViewViewHolder viewHolder = (PreViewViewHolder) holder;
            CardDataMdl model = list.get(position);

            viewHolder.setupCardPreview(getOrientationCard());
            Glide.with(mContext)
                    .load(model.getLinkURL())
                    .into(viewHolder.imgCardPreview);

            viewHolder.imgCardPreview.setOnClickListener(view -> onitemClick.onItemClick(view, position));

        } else {
            final DataViewHolder viewHolder = (DataViewHolder) holder;
            CardDataMdl model = list.get(position);

            viewHolder.value.setText(model.getDataText());
            viewHolder.linearLayout.setOnClickListener(view -> onitemClick.onItemClick(view, position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getTypeData().equals(LINE_TYPE)) {
            return VIEWTYPE_LINE;
        }
        if (list.get(position).getTypeData().equals(FACEBOOK__TYPE)) {
            return VIEWTYPE_FACEBOOK;
        }
        if (list.get(position).getTypeData().equals(TWITTER_TYPE)) {
            return VIEWTYPE_TWITTER;
        }
        if (list.get(position).getTypeData().equals(YOUTUBE_TYPE)) {
            return VIEWTYPE_YOUTUBE;
        }
        if (list.get(position).getTypeData().equals(GMAIL__TYPE)) {
            return VIEWTYPE_GMAIL;
        }
        if (list.get(position).getTypeData().equals(ADDRESS_TYPE)) {
            return VIEWTYPE_ADDRESS;
        }
        if (list.get(position).getTypeData().equals(TELEPHONE_TYPE)) {
            return VIEWTYPE_TELEPHONE;
        }
        if (list.get(position).getTypeData().equals(EMAIL_TYPE)) {
            return VIEWTYPE_EMAIL;
        }
        if (list.get(position).getTypeData().equals(WEB_TYPE)) {
            return VIEWTYPE_WEB;
        }
        if (list.get(position).getTypeData().equals(PREVIEW_CARD_TYPE)) {
            return VIEWTYPE_PREVIEW;
        } else {
            return VIEWTYPE_TEXT;
        }
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        public TextView value;
        public LinearLayout linearLayout;

        public DataViewHolder(View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.textview_value);
            linearLayout = itemView.findViewById(R.id.linear_list_detail);
        }
    }

    public class PreViewViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView imgCardPreview;

        public PreViewViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_namecard);
            imgCardPreview = itemView.findViewById(R.id.image_previewcard);
        }

        public void setupCardPreview(int oritationCard) {
            switch (oritationCard) {
                case 1:
                    imgCardPreview.getLayoutParams().height = (int) ConvertPxtoDp.convertDpToPixel(270, mContext);
                    imgCardPreview.getLayoutParams().width = (int) ConvertPxtoDp.convertDpToPixel(160, mContext);
                    imgCardPreview.requestLayout();
                    break;
                case 2:
                    imgCardPreview.getLayoutParams().height = (int) ConvertPxtoDp.convertDpToPixel(160, mContext);
                    imgCardPreview.getLayoutParams().width = (int) ConvertPxtoDp.convertDpToPixel(270, mContext);
                    imgCardPreview.requestLayout();
            }
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setOrientationCard(int i) {
        this.ORIENTATIONCARD = i;
    }

    public int getOrientationCard() {
        return ORIENTATIONCARD;
    }
}
