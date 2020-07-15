package com.line.recyclerviewfactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by chenliu on 2020/7/14.
 */
public class ItemSeaBinder extends ItemViewBinder<ItemSea, ItemSeaBinder.SeaViewHolder> {

    @NonNull
    @Override
    protected SeaViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_sea, parent, false);
        return new SeaViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull SeaViewHolder seaViewHolder, @NonNull ItemSea itemSea) {
        seaViewHolder.setItemSea(itemSea);
    }

    public static class SeaViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePic;
        TextView tvName;
        TextView tvDesc;

        public SeaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePic = itemView.findViewById(R.id.iv_pic);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }

        public void setItemSea(ItemSea itemSea) {
            Picasso.get().load(itemSea.res).into(imagePic);
            tvName.setText(itemSea.name);
            tvDesc.setText(itemSea.desc);
        }

    }

}

