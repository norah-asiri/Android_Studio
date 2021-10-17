package com.example.studyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class kotlin : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        val rv=findViewById<RecyclerView>(R.id.rv)

        var contant= arrayListOf( arrayListOf("var and val", "Declaring variables"),
            arrayListOf("User Input", "Getting user input"),
            arrayListOf("Strings", "String concatenations interpolation, and methods"),
            arrayListOf("Data Types", "Understanding data types"),
            arrayListOf("Basic Operations", "Performing math operations in Kotlin"))


        rv.adapter= recycler(this,contant)
        rv.layoutManager= LinearLayoutManager(this)
    }
}