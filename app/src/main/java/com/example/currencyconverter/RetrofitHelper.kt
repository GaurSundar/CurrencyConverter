package com.example.currencyconverter

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
const val BASE_URL = "https://api.exchangeratesapi.io/"
object RetrofitHelper {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val instance = retrofit.create(ExchangeRateApi::class.java)

}