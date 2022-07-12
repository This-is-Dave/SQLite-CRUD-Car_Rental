package com.example.inputsewa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class mobilInput : AppCompatActivity() {
    private lateinit var btnButton: android.widget.Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_mobil)
        val bukulist = listOf<mobilModel>(
            mobilModel(
                R.drawable.xenia,
                "XENIA",
                "Rp.200.000 / hari"
            ),
            mobilModel(
                R.drawable.agya,
                "TOYOTA AGYA",
                "Rp.230.000 / hari"
            ),
            mobilModel(
                R.drawable.pajero,
                "MITSUBISHI PAJERO",
                "Rp.300.000 / hari "
            ),
            mobilModel(
                R.drawable.inova,
                "TOYOTA INOVA",
                "Rp.250.000 / hari"
            )
        )
        val recyclerView = findViewById<RecyclerView>(R.id.mobil)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mobilAdapter(this, bukulist) {

        }
    }
}
