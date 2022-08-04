package com.petsprogramingtest.models


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Pets(
    @SerializedName("pets")
    @Expose
    val petList: List<Pet>
)