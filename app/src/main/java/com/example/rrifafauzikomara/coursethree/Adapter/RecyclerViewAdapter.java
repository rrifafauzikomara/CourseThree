package com.example.rrifafauzikomara.coursethree.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rrifafauzikomara.coursethree.Holder.RecyclerViewHolder;
import com.example.rrifafauzikomara.coursethree.ItemObject;
import com.example.rrifafauzikomara.coursethree.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by R Rifa Fauzi Komara on 10/02/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    public Context context;
    public List<ItemObject.Results> itemList;

    public RecyclerViewAdapter(Context context, List<ItemObject.Results> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, null);
        RecyclerViewHolder holder = new RecyclerViewHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Picasso.with(context).load("https://image.tmdb.org/t/p/w185" + itemList.get(position).poster_path)
                .placeholder(R.drawable.star).into(holder.img);
        holder.original_title.setText(itemList.get(position).original_title);
        holder.overview = itemList.get(position).overview;
        holder.thn = itemList.get(position).release_date;
        holder.backdrop = "https://image.tmdb.org/t/p/w780" + itemList.get(position).backdrop_path;
        holder.vote = itemList.get(position).vote_average;
        holder.id_film = itemList.get(position).id;
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
