package com.wednesdaykotlinpractise.api

import com.wednesdaykotlinpractise.models.Bank
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/{ifsc}")
    fun searchBank(@Path("ifsc") ifsc : String) : Call<Bank>
}