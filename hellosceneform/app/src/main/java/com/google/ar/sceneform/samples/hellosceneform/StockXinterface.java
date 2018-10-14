package com.google.ar.sceneform.samples.hellosceneform;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.Converter;

public interface StockXinterface {

    @Headers("x-api-key: B1sR9t386d6UVO6aI7KRf91gLaUywqEK1TLBGsXv")
    @GET("browse?limit=10")
    Call<StockX> getShoes();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://gateway.stockx.com/public/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
