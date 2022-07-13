package com.example.inputsewa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Dashboard : AppCompatActivity(), View.OnClickListener {
    private lateinit var btn_sewa : Button
    private lateinit var btn_customer : Button
    private lateinit var btn_mobil : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btn_customer = findViewById(R.id.btn_customer)
        btn_customer.setOnClickListener(this)

        btn_sewa = findViewById(R.id.btn_sewa)
        btn_sewa.setOnClickListener(this)

        btn_mobil = findViewById(R.id.mobil)
        btn_mobil.setOnClickListener(this)

    }

    override fun onClick(a: View?) {
        when (a!!.id) {
            R.id.btn_sewa -> {
                val intentBiasa1 = Intent(this@Dashboard, sewa::class.java)
                startActivity(intentBiasa1)
            }
            R.id.btn_customer -> {
                val intentBiasa2 = Intent(this@Dashboard, cost::class.java)
                startActivity(intentBiasa2)
            }
            R.id.mobil -> {
                val intentBiasa3 = Intent(this@Dashboard, mobil::class.java)
                startActivity(intentBiasa3)
            }
        }
    }


    }


