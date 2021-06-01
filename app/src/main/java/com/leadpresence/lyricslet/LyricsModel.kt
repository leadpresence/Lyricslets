package com.leadpresence.lyricslet

import com.google.gson.annotations.SerializedName

class LyricsModel {
    @SerializedName("lyrics")
    var lyrics: String? = null

    @SerializedName("error")
    var error: String? = null
}