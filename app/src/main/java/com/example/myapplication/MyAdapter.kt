package com.example.myapplication

import android.app.Activity
import android.media.MediaPlayer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Runnable

class MyAdapter(val context:Activity, val dataList:List<Data>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(context).inflate(R.layout.each_item,parent,false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=dataList[position]

        holder.singerName.text=currentItem.artist.name

        holder.songName.text=currentItem.title
        Picasso.get().load(currentItem.album.cover).into(holder.image)
        var media=MediaPlayer.create(context,currentItem.preview.toUri())
        var totalTime=media.duration
        holder.seekBar.max=totalTime
        holder.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {


            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if(fromUser){
                    media.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        val handler=Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try{
                    holder.seekBar.progress=media.currentPosition
                    handler.postDelayed(this,100)

                }
                catch (exception:java.lang.Exception){
                    holder.seekBar.progress=0

                }

            }
        },100 )



        holder.play.setOnClickListener{
            media.start()
        }
        holder.pause.setOnClickListener {
            media.pause()
        }
    }


    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var image:ShapeableImageView
        var songName:TextView
        var pause:ImageButton
        var play:ImageButton
        var seekBar:SeekBar
        var singerName:TextView

        init{
            image=itemView.findViewById(R.id.musicImage)
            songName=itemView.findViewById(R.id.musicTitle)
            pause=itemView.findViewById(R.id.btnPause)
            play=itemView.findViewById(R.id.btnPlay)
            seekBar=itemView.findViewById(R.id.seekbar)
            singerName=itemView.findViewById(R.id.singerName)

        }



    }


}