package com.example.retrofitmovie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {

    private static Retrofit zretrofit = null;

//접속하고자하는 서버 주소
    static Retrofit getClient(){

        zretrofit = new Retrofit.Builder()
                .baseUrl("http://mellowcode.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return zretrofit;

    }
}
