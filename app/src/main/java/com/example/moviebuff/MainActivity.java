package com.example.moviebuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    // results will contain all the list of the movies with all the properties like overview, rating etc.
    private List<Result> results = new ArrayList<>();

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    List<ItemClass> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        movieDbApi = RetrofitClient.getClient();

        setResults();

        initItemListData();
        initRecyclerView();


    }


    private void setResults(){
        Call<MoviePojo> call = movieDbApi.getMoviePojo();
        call.enqueue(new Callback<MoviePojo>() {
            @Override
            public void onResponse(Call<MoviePojo> call, Response<MoviePojo> response) {


                MoviePojo moviePojo = response.body();
                results = moviePojo.getResults();
                Log.i("info", results.size()+"");

            }
            @Override
            public void onFailure(Call<MoviePojo> call, Throwable t) {

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
//        itemList.add(new ItemClass("https://image.tmdb.org/t/p/w500/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg","Godzilla vs Kong"));
//        itemList.add(new ItemClass("https://image.tmdb.org/t/p/w500/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg","ic launcher"));
//        itemList.add(new ItemClass("https://image.tmdb.org/t/p/w500/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg","Godzilla vs Kong"));
//        itemList.add(new ItemClass("https://image.tmdb.org/t/p/w500/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg","ic launcher"));
//        itemList.add(new ItemClass("https://image.tmdb.org/t/p/w500/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg","Godzilla vs Kong"));
//        itemList.add(new ItemClass("https://image.tmdb.org/t/p/w500/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg","ic launcher"));
//        itemList.add(new ItemClass("https://image.tmdb.org/t/p/w500/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg","Godzilla vs Kong"));
//        itemList.add(new ItemClass("https://image.tmdb.org/t/p/w500/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg","ic launcher"));

        for(Result result : results){
            String posterPath = result.getPosterPath();
            String title = result.getOriginalTitle();
            itemList.add(new ItemClass(posterPath,title));
        }


    }
}