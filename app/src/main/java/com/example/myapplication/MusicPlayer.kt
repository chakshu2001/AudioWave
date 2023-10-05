package com.example.myapplication

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MusicPlayer : AppCompatActivity() {
    lateinit var myRecyclerView:RecyclerView
    lateinit var myAdapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)
        myRecyclerView=findViewById(R.id.recyclerView)



        val  retrofitBuilder=Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData=retrofitBuilder.getData("eminem")

        //ctrl + shift+ space
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                val dataList = response.body()?.data!!
                myAdapter=MyAdapter(this@MusicPlayer,dataList)
                myRecyclerView.adapter=myAdapter
                myRecyclerView.layoutManager=LinearLayoutManager(this@MusicPlayer)

                Log.d("Tag:OnResponse", "onResponse" + response.body())
            }

                override fun onFailure(call: Call<MyData?>, t: Throwable) {
                    Log.d("Taf:OnFailure","onFailure"+t.message);
                }
            })


        }
        }


