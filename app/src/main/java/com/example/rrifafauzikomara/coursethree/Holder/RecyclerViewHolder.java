package com.example.rrifafauzikomara.coursethree.Holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rrifafauzikomara.coursethree.DetailActivity;
import com.example.rrifafauzikomara.coursethree.R;

/**
 * Created by R Rifa Fauzi Komara on 10/02/2018.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView original_title;
    public ImageView img;
    public String overview, backdrop, thn, vote, id_film;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        img  = itemView.findViewById(R.id.movie_thumbnail);
        original_title = itemView.findViewById(R.id.movie_title);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra("id", id_film);
        intent.putExtra("judul", original_title.getText().toString());
        intent.putExtra("deskripsi", overview);
        intent.putExtra("tahun", thn);
        intent.putExtra("latar", backdrop);
        intent.putExtra("vote", vote);
        view.getContext().startActivity(intent);
    }

}
