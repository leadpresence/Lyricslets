package com.leadpresence.lyricslet

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v1/{artist}/{title}")
    fun loadLyrics(@Path("artist") artist:String,@Path("title") title:String,): Call<LyricsModel>

}