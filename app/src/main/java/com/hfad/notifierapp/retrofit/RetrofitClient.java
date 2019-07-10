package com.hfad.notifierapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://h-jebudsu-app.herokuapp.com";
//    private static final String BASE_URL = "http://192.168.0.200:8080";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return  mInstance;
    }



    public ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }
}

