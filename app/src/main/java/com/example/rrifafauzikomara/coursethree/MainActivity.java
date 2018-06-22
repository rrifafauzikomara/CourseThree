package com.example.rrifafauzikomara.coursethree;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rrifafauzikomara.coursethree.Adapter.RecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends AppCompatActivity {

    private final String API_Key = "24d18ef569e3997bf2779d05440d3c6e";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerViewAdapter adapterData;
    private String FilmCategory;
    private ProgressDialog dialog;
    private AlertDialog.Builder alert;
    private ItemObject a;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alert = new AlertDialog.Builder(this);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(MainActivity.this, 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        requestJsonObject(0);

    }

    private void requestJsonObject(int i) {
        if (i == 0) {
            setTitle("Populer Movie");
            FilmCategory = "popular";
        } else if (i == 1) {
            setTitle("Top Rated Movie");
            FilmCategory = "top_rated";
        } else if (i == 2) {
            setTitle("Coming Soon");
            FilmCategory = "upcoming";
        }

        RequestParams params = new RequestParams();
        params.put("api_key", API_Key);
        String FullURL = "https://api.themoviedb.org/3/movie/" +FilmCategory;

        MyParsingGson(params, FullURL);
    }

    private void MyParsingGson (RequestParams params, String url) {
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading Movies. . .");
        dialog.setCancelable(false);
        dialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response) {
                dialog.hide();
                try {
                    GsonBuilder builder = new GsonBuilder();
                    Gson mGson = builder.create();
                    a = mGson.fromJson(response, ItemObject.class);
                    adapterData = new RecyclerViewAdapter(MainActivity.this, a.results);
                    recyclerView.setAdapter(adapterData);
                } catch (Exception e) {
                    e.printStackTrace();
                    alert.setTitle("Terjadi Kesalahan");
                    alert.setMessage("Request Time Out");
                    alert.show();
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error, String content) {
                dialog.hide();
                if (statusCode == 404) {
                    alert.setTitle("Terjadi Kesalahan");
                    alert.setMessage("404 Not Found");
                    alert.show();
                } else if (statusCode == 500 ) {
                    alert.setTitle("Terjadi Kesalahan");
                    alert.setMessage("500 Internal Server Error");
                    alert.show();
                } else {
                    alert.setTitle("Terjadi Kesalahan");
                    alert.setMessage("No Internet Connection");
                    alert.show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.populer:
                requestJsonObject(0);
                break;
            case R.id.toprated:
                requestJsonObject(1);
                break;
            case R.id.upcoming:
                requestJsonObject(2);
                break;
            case R.id.about:
                Toast.makeText(getApplicationContext(), "Di buat oleh saya", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Klik tombol back lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
