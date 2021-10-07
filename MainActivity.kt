package com.example.lambda_practice_asiri_norah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // See Hello , then calculate Square of number , finally calculate multiply

        val SeeHellow = { println("Hello Everybody")}
        SeeHellow()

        val Square = { num : Int -> num*num }
        println("${Square(9)}")

        val multiply = { num1: Int ,num2: Int -> num1 * num2 }
        println("${multiply(2,3)}")

        val list= listOf<Player>(
        Player("Ahmaed",15,156),
        Player("Ali",16,156),
        Player("Ahed",17,166),
        Player("Nor",18,166),
        Player("Hasa",19,176),
        Player("Elen",20,176),
        Player("Rola",21,146),
        Player("Wed",22,146),
        Player("Majed",23,156),
        Player("Roa",24,156)
        )

       // println(" Player Name is : ${it.name} , ${list.first { (it.age > 20)}}, height : ${it.height} ")
        val PlayerLambdaName = list.forEach {println(" Player Name is : ${it.name} ,age:  ${it.age}, height : ${it.height} ") }

            //ref: https://www.baeldung.com/kotlin/lambda-expressions

        //Bonus -> print list of player after filtering
        val PlayerLambdaFilter = list.filter{ it.height > 170}
        println("$PlayerLambdaFilter")

    }
}
data class Player (val name: String , val age : Int, val height:Int)

