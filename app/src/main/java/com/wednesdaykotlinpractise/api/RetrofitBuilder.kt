package com.wednesdaykotlinpractise.api

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    private val BASE_URL = "https://ifsc.razorpay.com/"
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    fun getRetrofit() : Retrofit {
        return retrofit
    }

}