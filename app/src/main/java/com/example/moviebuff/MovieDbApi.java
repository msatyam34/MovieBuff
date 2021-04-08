package com.example.moviebuff;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieDbApi {

    @GET("/3/movie/popular?api_key=da069e3970819cec25856ebe20aae42c")
    Call<MoviePojo> getMoviePojo();


}
