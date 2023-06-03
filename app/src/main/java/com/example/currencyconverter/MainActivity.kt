package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.text.FieldPosition
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var sourceSpinner : Spinner
    lateinit var targetSpinner : Spinner
    lateinit var currencyToBeConverted : EditText
    lateinit var resultCurrency : TextView
    lateinit var button: Button
    var baseCurrency = "EUR"
    var targetCurrency = "USD"
    var conversionRate = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sourceSpinner = findViewById(R.id.sourceCurrencySpinner)
        targetSpinner = findViewById(R.id.targetCurrencySpinner)
        currencyToBeConverted = findViewById(R.id.amountEditText)
        resultCurrency = findViewById(R.id.resultTextView)
        button = findViewById(R.id.convertButton)

        setSpinnerItems()

        button.setOnClickListener {
            if(currencyToBeConverted.text.isEmpty()){
                Toast.makeText(applicationContext  , "Please Enter Valid Amount" , Toast.LENGTH_SHORT ).show()
            }
           else if(baseCurrency == targetCurrency){
                Toast.makeText(applicationContext  , "Cannot Convert The Same Currency" , Toast.LENGTH_SHORT ).show()
            }
            else {
                fetchApiData()
            }
        }

    }

    private fun setSpinnerItems(){
        val currencies = resources.getStringArray(R.array.source_currencies)
        val adapter = ArrayAdapter<String>(this ,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item ,
            currencies)
        sourceSpinner.adapter = adapter
        targetSpinner.adapter = adapter
        sourceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                baseCurrency = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        targetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                targetCurrency = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private  fun fetchApiData(){
        val currency = currencyToBeConverted.text.toString().toDouble()
        val call = RetrofitHelper.instance.getExchangeRates(baseCurrency, targetCurrency)
        call.enqueue(object : retrofit2.Callback<ExchangeRateResponse> {
            override fun onResponse(
                call: Call<ExchangeRateResponse>,
                response: Response<ExchangeRateResponse>
            ) {
                val result = response.body().toString()
                val currency = currencyToBeConverted.text.toString().toDouble()
                if(result != null){
                    Log.d("success" , result.toString())
                }
                val rates = JSONObject(result).getJSONObject("rates")
                conversionRate = rates.get(targetCurrency) as Float
                val convertedCurrency = currency * conversionRate
                resultCurrency.text =   convertedCurrency.toString() + " $targetCurrency"

            }

            override fun onFailure(call: Call<ExchangeRateResponse>, t: Throwable) {
                Log.d("message" , "Error 404" , t)
            }

        })
    }
}


