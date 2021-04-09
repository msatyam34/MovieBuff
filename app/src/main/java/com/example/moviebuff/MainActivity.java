package com.example.moviebuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private MovieDbApi movieDbApi;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;

    //progressDialog is used to show the loading progress.
    private ProgressDialog progressDialog;

    // itemList will contain all the items for recyclerView.
    private List<ItemClass> itemList;

    // results will contain all the list of the movies with all the properties like overview, rating etc.
    private List<Result> results = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Making retrofit client
        movieDbApi = RetrofitClient.getClient();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        // setting or initializing the results.
        setResults();

    }



    private void setResults(){
        Call<MoviePojo> call = movieDbApi.getMoviePojo();
        call.enqueue(new Callback<MoviePojo>() {
            @Override
            public void onResponse(Call<MoviePojo> call, Response<MoviePojo> response) {
                progressDialog.dismiss();

                MoviePojo moviePojo = response.body();
                results = moviePojo.getResults();

                //initializing the item List.
                initItemListData();


                //initializing RecyclerView.
                initRecyclerView();

            }


            @Override
            public void onFailure(Call<MoviePojo> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Connect to a Network",Toast.LENGTH_SHORT).show();

            }
        });
    }



    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,itemList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();  //  ?

    }



    private void initItemListData(){
        itemList = new ArrayList<>();

        String URL_PREFIX = "https://image.tmdb.org/t/p/w500";
        for(Result result : results){
            String posterPath = URL_PREFIX + result.getPosterPath();
            String title = result.getOriginalTitle();
            String overView = result.getOverview();
            String releaseDate = result.getReleaseDate();
            String rating = result.getVoteAverage().toString();
            String reviews = result.getVoteCount().toString();
            itemList.add(new ItemClass(posterPath,title,overView,releaseDate,rating,reviews));

        }

    }

}