package com.example.currencyconverter

data class ExchangeRateResponse(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)