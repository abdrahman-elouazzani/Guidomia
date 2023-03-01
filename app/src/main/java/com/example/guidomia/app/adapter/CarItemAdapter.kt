package com.example.guidomia.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guidomia.R
import com.example.guidomia.model.CarItem

class CarItemAdapter constructor(val context: Context) :
    RecyclerView.Adapter<CarItemAdapter.ItemViewHolder>() {

    private var carList = ArrayList<CarItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_car_item, parent, false
        )
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val carItem = carList[position]
        holder.tvCarModel.text = carItem.model
        holder.tvCarPrice.text = "Price: ${carItem.customerPrice}"
        holder.ratingBar.rating = carItem.rating.toFloat()
    }

    override fun getItemCount(): Int {
       return carList.size
    }

    fun setCarList(carList: ArrayList<CarItem>) {
        this.carList = carList
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCar = itemView.findViewById<ImageView>(R.id.imgCar)
        val tvCarModel = itemView.findViewById<TextView>(R.id.tvCarName)
        val tvCarPrice = itemView.findViewById<TextView>(R.id.tvCarPrice)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
    }
}