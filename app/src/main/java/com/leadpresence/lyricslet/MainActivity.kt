package com.leadpresence.lyricslet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.gson.GsonConverterFactory.*


class MainActivity : AppCompatActivity() {
    var url: String = "https://api.lyrics.ovh/"

    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lyrics.movementMethod = ScrollingMovementMethod()
        searchbutton.setOnClickListener(View.OnClickListener {


            if (artist.text.isEmpty() || songtitle.text.isEmpty()) {
                val makeText = Toast.makeText(
                    applicationContext,
                    "Please provide Artist name and Song title",
                    Toast.LENGTH_SHORT
                ).show()
                makeText
            } else {
                val makeText = Toast.makeText(
                    applicationContext,
                    "Searching for  ${artist.text}'s ${songtitle.text}",
                    Toast.LENGTH_LONG
                ).show()
                makeText
                val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(ApiService::class.java)
                val serviceCall =
                    service.loadLyrics(artist.text.toString(), songtitle.text.toString())
                serviceCall.enqueue(object : Callback<LyricsModel> {
                    override fun onFailure(call: Call<LyricsModel>?, t: Throwable?) {
                        if (t != null) {
                            print(t.localizedMessage)
                        }
                    }

                    override fun onResponse(
                        call: Call<LyricsModel>?,
                        response: Response<LyricsModel>?
                    ) {
                        if (response != null) {
                            Log.d("LYRICS", "${response.body()?.lyrics}")
                            lyrics.text = response.body()?.lyrics //Add the lyrics in the textview
                        }

                    }
                })
            }
        })
    }
}





