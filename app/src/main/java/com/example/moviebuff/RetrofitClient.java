package com.example.moviebuff;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static MovieDbApi apiInterface;


        public static MovieDbApi getClient () {
            if(retrofit==null){


                retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.themoviedb.org")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .client(getOkHttpClient())
                        .build();

                apiInterface = retrofit.create(MovieDbApi.class);

            }
            return apiInterface;


        }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        okHttpClient.addInterceptor(getLoggingInterceptor());
        return okHttpClient.build();
    }
    private static Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

}
