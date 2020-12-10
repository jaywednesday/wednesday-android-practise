package com.wednesdaykotlinpractise

import android.app.Application
import com.wednesdaykotlinpractise.api.ApiInterface
import com.wednesdaykotlinpractise.api.RetrofitBuilder
import retrofit2.create

class AppController : Application() {
    lateinit var apiInterface: ApiInterface
    companion object {
        lateinit var instance: AppController
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        apiInterface = RetrofitBuilder.getRetrofit().create(ApiInterface::class.java)
    }
}