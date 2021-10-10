package com.example.weather_app_asiri_norah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var bt : Button
    lateinit var et : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt= findViewById(R.id.bt)
        et = findViewById(R.id.et)

        et.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity2::class.java)
            intent.putExtra("CITY", et.text.toString())
            startActivity(intent)
        }
    }
}