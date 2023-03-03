package com.example.guidomia.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guidomia.R
import com.example.guidomia.app.adapter.CarItemAdapter
import com.example.guidomia.app.viewModel.MainViewModel
import com.example.guidomia.data.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var adapter : CarItemAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editModel: EditText = findViewById(R.id.editModel)
        val editMake: EditText = findViewById(R.id.editMake)

        adapter = CarItemAdapter(this)

        val rvCarList: RecyclerView = findViewById(R.id.rvCarList)
        rvCarList.layoutManager = LinearLayoutManager(this)
        rvCarList.adapter = adapter

        viewModel = MainViewModel(Repository(this))
        viewModel.carList.observe(this) {
            adapter.setCarList(it)
        }
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.getCarList()

        editModel.addTextChangedListener {
            if (it != null) {
                if (it.isEmpty() && editMake.text.isEmpty()) {
                    adapter.nonFilter()
                }
                else {
                    adapter.filter(it.toString(), editMake.text.toString())
                }
            }
        }

        editMake.addTextChangedListener {
            if (it != null) {
                if (it.isEmpty() && editModel.text.isEmpty()) {
                    adapter.nonFilter()
                }
                else {
                    adapter.filter(editModel.text.toString(), it.toString())
                }
            }
        }
    }


}