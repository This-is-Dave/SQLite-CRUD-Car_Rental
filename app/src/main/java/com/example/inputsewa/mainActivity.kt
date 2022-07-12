package com.example.inputsewa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class mainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnIntent: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnIntent = findViewById(R.id.Blogin)
        btnIntent.setOnClickListener(this)

    }
        override fun onClick(v: View) {
            when (v.id) {
                R.id.Blogin -> {
                    val intentBiasa = Intent(this@mainActivity,Dashboard::class.java)
                    startActivity(intentBiasa)
                }
            }
        }
    }

