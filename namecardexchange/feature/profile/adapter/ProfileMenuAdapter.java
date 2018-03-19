package com.mbox.administrator.namecardexchange.feature.profile.adapter;

import android.content.Context;
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
import com.mbox.administrator.namecardexchange.feature.profile.model.ProfileMenuModel;

import java.util.List;

/**
 * Created by Administrator on 11/1/2561.
 */

public class ProfileMenuAdapter extends RecyclerView.Adapter<ProfileMenuAdapter.Viewholder> implements View.OnClickListener {

    private Context mContext;
    private List<ProfileMenuModel> list;
    private OnitemClick onitemClick;

    public ProfileMenuAdapter(Context mContext, List<ProfileMenuModel> list, OnitemClick onitemClick) {
        this.mContext = mContext;
        this.list = list;
        this.onitemClick = onitemClick;
    }

    @Override
    public ProfileMenuAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.custom_menu_profile, parent, false);
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileMenuAdapter.Viewholder holder, int position) {
        ProfileMenuModel model = list.get(position);
        holder.menuName.setText(model.getMenuName());
        holder.setImageIcon(model.getMenuIconUrl());
    }

    @Override
    public void onClick(View view) {

    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView menuName;
        public ImageView menuIcon;
        public LinearLayout linearButton;

        public Viewholder(View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.txt_menu_name_profile);
            menuIcon = itemView.findViewById(R.id.img_menu_icon_profile);
            linearButton = itemView.findViewById(R.id.linear_profile_menu);
            linearButton.setOnClickListener(this);
        }

        public void setImageIcon(String iconURL){
            Glide.with(mContext)
                    .load(iconURL)
                    .into(menuIcon);
        }

        @Override
        public void onClick(View view) {
            if (onitemClick != null) onitemClick.onItemClick(view,getAdapterPosition());
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
