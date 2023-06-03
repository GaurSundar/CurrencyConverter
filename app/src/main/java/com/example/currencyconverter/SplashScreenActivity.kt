package com.example.currencyconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        Splash Screen Timer
        Handler(Looper.getMainLooper()).postDelayed(1200){
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        }
    }
}