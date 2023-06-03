package com.example.currencyconverter

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "c62c4fc1e77a66eff3efa30dd4e779c9"

interface ExchangeRateApi{

    @GET("latest?access_key=c62c4fc1e77a66eff3efa30dd4e779c9")
     fun getExchangeRates(
        @Query("base") baseCurrency: String,
        @Query("symbols") targetCurrency: String
     ): Call<ExchangeRateResponse>
}




