package com.example.w3d4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

lateinit var et : EditText
lateinit var btGo: Button
lateinit var name:String

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et = findViewById(R.id.etName)
        btGo = findViewById(R.id.btGo)
        name = et.text.toString()

        btGo.setOnClickListener {
            var intint = Intent(this, info::class.java)
            intint.putExtra("info", "Hi $name")
            startActivity(intint)
        }
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(item.itemId== R.id.more){
           var r = R.id.more
           iam(item)
           return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(main: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, main)
        return true
    }

      fun  iam(more : android.view.MenuItem){
          Toast.makeText(applicationContext, "Welcome, I am Norah Asiri",Toast.LENGTH_SHORT).show();
      }

}