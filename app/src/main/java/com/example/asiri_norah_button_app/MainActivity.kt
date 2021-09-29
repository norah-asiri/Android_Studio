package com.example.asiri_norah_button_app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var Button1: Button
    lateinit var Button2: Button
    lateinit var number: TextView
    val color= listOf("black","white","red","pink","yellow","green","gray","purple","blue","orange")
    val myRV = findViewById<RecyclerView>(R.id.rvMain)
    myRV.adapter=RecyclerViewAdapter(colors)
    myRV.layoutManager= LinearLayoutManager(this)
    var x=0
    var line=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myLayout= findViewById<ConstraintLayout>(R.id.clMain)
        Snackbar.make(myLayout, "Hello there, weleome to my app!", Snackbar.LENGTH_LONG).show()

        Button1 = findViewById<Button>(R.id.button1)
        Button2 = findViewById<Button>(R.id.button2)
        number = findViewById<TextView>(R.id.number)

        Button2.setOnClickListener { num('+') }
        Button1.setOnClickListener { num('-') }
    }
     fun num(op : Char){
         if(op =='+'){
             line++
             x++
             number.setLines(line)}
         else{
             line--
             x--
             number.setLines(line)}
         number.text=x.toString()
         if(x>0)
             number.setTextColor(Color.argb(255, 0, 200, 0))
         else if (x<0)
             number.setTextColor(Color.argb(255, 200, 0, 0))
         else
             number.setTextColor(Color.argb(255,0, 0, 0))


     }
}