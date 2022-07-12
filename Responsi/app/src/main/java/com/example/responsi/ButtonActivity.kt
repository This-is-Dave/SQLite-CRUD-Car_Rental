package com.example.responsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)
    }

    fun btnIntent(view : View){
        val intent = android.content.Intent(this, MainActivity::class.java)
        startActivity(intent)
    }    }