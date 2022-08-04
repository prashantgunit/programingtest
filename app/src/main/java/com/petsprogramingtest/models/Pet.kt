package com.petsprogramingtest.models


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Pet(
    @SerializedName("content_url")
    @Expose
    val contentUrl: String,
    @SerializedName("date_added")
    @Expose
    val dateAdded: String,
    @SerializedName("image_url")
    @Expose
    val imageUrl: String,
    @SerializedName("title")
    @Expose
    val title: String
)