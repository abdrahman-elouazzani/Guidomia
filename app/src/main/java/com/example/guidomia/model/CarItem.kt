package com.example.guidomia.model

import com.google.gson.annotations.SerializedName

data class CarItem(
   @SerializedName("consList")
    val consList : ArrayList<String>,
   @SerializedName("customerPrice")
   val customerPrice: Double,
   @SerializedName("marketPrice")
   val marketPrice: Double,
   @SerializedName("model")
   val model: String,
   @SerializedName("prosList")
   val prosList: ArrayList<String>,
   @SerializedName("rating")
   val rating: Int,
   var expanded: Boolean = false
)
