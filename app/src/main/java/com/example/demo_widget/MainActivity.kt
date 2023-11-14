package com.example.demo_widget

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val serviceIntent = Intent(this, ForegroundService::class.java)
        //startForegroundService(serviceIntent)

        val serviceIntent2 = Intent(this, MyService::class.java)
        startForegroundService(serviceIntent2)

    }
}