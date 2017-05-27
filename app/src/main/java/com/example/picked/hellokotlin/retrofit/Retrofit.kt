package com.example.picked.hellokotlin.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun <T> createRetrofitService(x: Class<T>): T {
    return Retrofit.Builder()
            .baseUrl("http://api.androidhive.info/json/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(x)
}