package com.example.post_requests_asiri

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

//https://dojo-recipes.herokuapp.com/recipes/test/


interface APIInterface {
    //@Headers("Content-Type: application/json")
    @GET("test/")
    fun getUser(): Call<List<Users.UserDetails>>

    //@Headers("Content-Type: application/json")
    @POST("test/")
    fun addUser(@Body userData: Users.UserDetails): Call<Users.UserDetails>


}


/*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIInterface {

    @POST("/books/")
    fun addBook(@Body bookData: Book): Call<Book>?
}

 */