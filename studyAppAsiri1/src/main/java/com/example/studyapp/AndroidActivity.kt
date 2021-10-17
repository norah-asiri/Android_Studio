package com.example.studyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_kotlin.*

class AndroidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)

        var contant= arrayListOf(arrayListOf("Project Setup", "Setting up an Android Studio Project."),
            arrayListOf("Console", "Printing to the console."),
            arrayListOf("Resource Files", "Identifying, editing, and using resource files."),
            arrayListOf("UI Elements", "Creating, modifying, and initializing UI Elements."))



        rv.adapter= recycler(this,contant)
        rv.layoutManager= LinearLayoutManager(this)
    }

}