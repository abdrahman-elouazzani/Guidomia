package com.example.guidomia.data

import android.content.Context
import android.util.Log.d
import com.example.guidomia.model.CarItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type
import java.util.logging.Logger

class Repository(private val context: Context) {

    suspend fun getCarList() : ArrayList<CarItem>? {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("car_list.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {

        }
        val carListType = object : TypeToken<ArrayList<CarItem>>() {}.type
        return Gson().fromJson(jsonString,carListType )
    }
}