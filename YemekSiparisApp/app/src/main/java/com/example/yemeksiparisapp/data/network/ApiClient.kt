package com.example.yemeksiparisapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL ="http://kasimadalan.pe.hu/yemekler/"

    val retrofit : YemeklerApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YemeklerApiService::class.java)
    }
}