package com.example.guidomia.app.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
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
        addExpandedView(holder.prosLayout, holder.cons_layout, carItem)

        holder.expandedLayout.visibility = if (carItem.expanded) View.VISIBLE else View.GONE
      //  holder.expandedLayout.visibility = if (carItem.expanded) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            carItem.expanded = !carItem.expanded
            holder.expandedLayout.visibility = if (carItem.expanded) View.VISIBLE else View.GONE
        }
    }

    override fun getItemCount(): Int {
       return carList.size
    }

    fun setCarList(carList: ArrayList<CarItem>) {
        this.carList = carList
        notifyDataSetChanged()
    }

    private fun addExpandedView(prosLayout: ConstraintLayout, cons_layout: ConstraintLayout,car: CarItem) {

        if (car.prosList.isNotEmpty()) {

            val tvTitle = TextView(context)
            val arrayTextView = Array(car.prosList.size){ TextView(context) }
            tvTitle.id = View.generateViewId()
            tvTitle.text = "Pos:"
           tvTitle.setTextAppearance(context,R.style.title_text_appearance)
            prosLayout.addView(tvTitle)
            for (i in  car.prosList.indices) {
                arrayTextView[i].id = View.generateViewId()
                arrayTextView[i].text = car.prosList[i]
                arrayTextView[i].setTextAppearance(context, R.style.text_appearance)
                prosLayout.addView(arrayTextView[i])
            }
            val constraintSet = ConstraintSet()
            constraintSet.clone(prosLayout)
            constraintSet.connect(tvTitle.id, ConstraintSet.TOP, prosLayout.id, ConstraintSet.TOP, 18)
            constraintSet.connect(tvTitle.id, ConstraintSet.START, prosLayout.id, ConstraintSet.START, 18)


            for (i in  car.prosList.indices) {
                if (i == 0) {
                    constraintSet.connect(arrayTextView[i].id, ConstraintSet.TOP, tvTitle.id, ConstraintSet.BOTTOM, 18)
                }
                else {
                    constraintSet.connect(arrayTextView[i].id, ConstraintSet.TOP, arrayTextView[i-1].id, ConstraintSet.BOTTOM, 18)
                }
                constraintSet.connect(arrayTextView[i].id, ConstraintSet.START, prosLayout.id, ConstraintSet.START, 32)
            }
            constraintSet.applyTo(prosLayout)
        }

        if (car.consList.isNotEmpty()) {
            val tvTitle = TextView(context)
            val arrayTextView = Array(car.consList.size){ TextView(context) }
            tvTitle.id = View.generateViewId()
            tvTitle.text = "Cons:"
            tvTitle.setTextAppearance(context,R.style.title_text_appearance)
            cons_layout.addView(tvTitle)
            for (i in  car.consList.indices) {
                arrayTextView[i].id = View.generateViewId()
                arrayTextView[i].text = car.consList[i]
                arrayTextView[i].setTextAppearance(context, R.style.text_appearance)
                cons_layout.addView(arrayTextView[i])
            }
            val constraintSet = ConstraintSet()
            constraintSet.clone(cons_layout)
            constraintSet.connect(tvTitle.id, ConstraintSet.TOP, cons_layout.id, ConstraintSet.TOP, 18)
            constraintSet.connect(tvTitle.id, ConstraintSet.START, cons_layout.id, ConstraintSet.START, 18)


            for (i in  car.consList.indices) {
                if (i == 0) {
                    constraintSet.connect(arrayTextView[i].id, ConstraintSet.TOP, tvTitle.id, ConstraintSet.BOTTOM, 18)
                }
                else {
                    constraintSet.connect(arrayTextView[i].id, ConstraintSet.TOP, arrayTextView[i-1].id, ConstraintSet.BOTTOM, 18)
                }
                constraintSet.connect(arrayTextView[i].id, ConstraintSet.START, cons_layout.id, ConstraintSet.START, 32)
            }
            constraintSet.applyTo(cons_layout)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCar = itemView.findViewById<ImageView>(R.id.imgCar)
        val tvCarModel = itemView.findViewById<TextView>(R.id.tvCarName)
        val tvCarPrice = itemView.findViewById<TextView>(R.id.tvCarPrice)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
        val expandedLayout = itemView.findViewById<ConstraintLayout>(R.id.expanded_layout)
        val prosLayout = itemView.findViewById<ConstraintLayout>(R.id.pros_layout)
        val cons_layout = itemView.findViewById<ConstraintLayout>(R.id.cons_layout)
    }
}