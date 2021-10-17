package com.hedaia.omar.coroutinesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {

    // declare

    private var click=true
    private lateinit var tvadvice:TextView
    private lateinit var advicebtn:Button
    private lateinit var btnPause:Button

    val adviceUrl = "https://api.adviceslip.com/advice"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize
        tvadvice = findViewById(R.id.tv_advice)
        advicebtn = findViewById(R.id.btn_getadvice)
        btnPause = findViewById(R.id.btn_pause)

        advicebtn.setOnClickListener(){
            click = true
            requestApi()
           // call fun

        }

        btnPause.setOnClickListener(){
            click = false
        }
    }

    private fun requestApi() {

            CoroutineScope(Dispatchers.IO).launch {// CoroutineScope fun ()
                while (click ==true) { // Bonus
                val data = async {
                    fetchRandomAdvice() // call fun to fetch data
                }.await()
               // delay(10)
                if (data.isNotEmpty()) {
                    updateAdviceText(data) // call fun to display data
                }
            }
        }
    }

    private fun fetchRandomAdvice():String{
        var response=""
        try {
            response =URL(adviceUrl).readText(Charsets.UTF_8)
        }catch (e:Exception)
        {
            println("Error $e")
        }
        return response
    }

    private suspend fun updateAdviceText(data:String)
    {
        withContext(Dispatchers.Main)
        {
            val jsonObject = JSONObject(data)
            val slip = jsonObject.getJSONObject("slip") // id of object from here https://api.adviceslip.com/advice
            val id = slip.getInt("id") // get attribute1  (id) of object
            val advice = slip.getString("advice") // get att2 (advice) of object , that well be appear on tv
            tvadvice.text = advice
            CoroutineScope(Dispatchers.IO).cancel()
        }
    }
}