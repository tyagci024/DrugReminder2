package com.example.apptez.view.model

import com.google.gson.annotations.SerializedName

data class Pharmacy(
    @SerializedName("name")
    val  countryCapital: String,
    @SerializedName("adress")
    val  countryName:String,
    val number : String)



