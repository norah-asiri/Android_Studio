package com.example.studyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val bu1=findViewById<Button>(R.id.button)
        val bu2=findViewById<Button>(R.id.button2)

        bu1.setOnClickListener {
            var intent= Intent(this,kotlin::class.java)
            startActivity(intent)
        }
        bu2.setOnClickListener {
            var intent= Intent(this,AndroidActivity::class.java)
            startActivity(intent)
        }



    }
}