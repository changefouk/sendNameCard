package com.mbox.administrator.namecardexchange.feature.exchange.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mbox.administrator.namecardexchange.R;
import com.mbox.administrator.namecardexchange.feature.exchange.model.ExchangemenuModel;
import com.mbox.administrator.namecardexchange.feature.exchange.viewmodel.FragmentExchangeViewmodel;

import java.util.List;

/**
 * Created by Administrator on 15/12/2560.
 */

public class ExchangeMenuAdapter extends RecyclerView.Adapter<ExchangeMenuAdapter.Viewholders> {

    private List<ExchangemenuModel> list;
    private Context mContext;
    private OnItemMenuClick onItemClick;
    private int suggestadd;

    public ExchangeMenuAdapter(List<ExchangemenuModel> list, Context mContext,int suggestadd, OnItemMenuClick onItemClick) {
        this.list = list;
        this.mContext = mContext;
        this.onItemClick = onItemClick;
        this.suggestadd = suggestadd;
    }

    @Override
    public Viewholders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.custom_function_exchange, parent, false);
        return new Viewholders(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholders holder, int position) {
        ExchangemenuModel model = list.get(position);
        holder.name.setText(model.getMenuName());
        holder.description.setText(model.getMenuDescription());
        holder.setIconUrl(model.getIconUrl());

        if (model.getModelID() == FragmentExchangeViewmodel.menuID.FIND_SUGGEST.ordinal()) {
            /**
             TODO : notification dot if someone send card
             */
            if (suggestadd >0){
                holder.dot.setVisibility(View.VISIBLE);
                holder.setsuggesttext(Integer.toString(suggestadd));
                Log.d("FLUKEY","number suggest"+suggestadd);
            }else if (suggestadd == 0){
               holder.dot.setVisibility(View.GONE);
            }
        }
    }

    public void setsuggestValue(int value){
        this.suggestadd = value;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setClickListener(OnItemMenuClick clickListener) {
        this.onItemClick = clickListener;
    }

    public class Viewholders extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView icon;
        public TextView name, description, txtdot;
        public LinearLayout dot, buttonLayout;


        public Viewholders(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.img_menu_icon);
            name = itemView.findViewById(R.id.txt_menu_name);
            description = itemView.findViewById(R.id.txt_menu_description);
            dot = itemView.findViewById(R.id.linear_dot);
            txtdot = itemView.findViewById(R.id.txt_dot);
            buttonLayout = itemView.findViewById(R.id.linear_exchange_btn);
            buttonLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClick != null) onItemClick.onItemClick(view, getAdapterPosition());
        }

        public void setsuggesttext(String suggest){
            txtdot.setText(suggest);
        }

        public void setIconUrl(String url) {
            Glide.with(mContext)
                    .load(url)
                    .into(icon);
        }
    }
}
