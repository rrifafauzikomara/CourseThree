package com.example.rrifafauzikomara.coursethree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView txtJudul, txtDeskripsi, txtVote, txtTahun;
    private ImageView imgMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtJudul = findViewById(R.id.movie_title_dt1);
        txtDeskripsi = findViewById(R.id.movie_desc);
        imgMovie = findViewById(R.id.movie_poster);
        txtVote = findViewById(R.id.move_rate);
        txtTahun = findViewById(R.id.movie_date);

        String latar = getIntent().getStringExtra("latar");
        String jud = getIntent().getStringExtra("judul");
        String des = getIntent().getStringExtra("deskripsi");
        String thn = getIntent().getStringExtra("tahun");
        String vote = getIntent().getStringExtra("vote");

        Picasso.with(this).load(latar).placeholder(R.drawable.star).into(imgMovie);
        txtJudul.setText(jud);
        txtDeskripsi.setText(des);
        txtTahun.setText("Release date : " + thn);
        txtVote.setText("Rating : " + vote + " /10");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(intent);
        return true;
    }

}
