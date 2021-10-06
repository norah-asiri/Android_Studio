package com.example.w3d4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        var st1 = intent.getStringExtra("info")
        var tv = findViewById(R.id.tvInfo) as TextView
        tv.text =st1

    }
}