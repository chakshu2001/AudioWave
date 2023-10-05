package com.example.myapplication

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-RapidAPI-Key: e08e6d2be7mshbe5c26a13547420p125b85jsn7456cce21422",
        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query ("q") query:String):Call<MyData>
}