package com.app.infyfour.data.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    private const val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

    val client = OkHttpClient.Builder()
    val loggingInterceptor = HttpLoggingInterceptor()


    public fun getRetrofit() : Retrofit{

        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}