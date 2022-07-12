package com.example.responsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var btnButton : android.widget.Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bukulist = listOf<Buku>(
            Buku(
                R.drawable.black,
                "Black Clover",
                "Mengisahkan dua anak kecil bernama Asta dan Yuno yang mencari Kekuatan Sihir"
            ),
            Buku(
                R.drawable.aot,
                "Shingeki No Kyojin",
                "Cerita dimana manusia hidup didalam tembok karena kehancuran dunia luar yang disebabkan oleh raksasa"
            ),
            Buku(
                R.drawable.kny,
                "Kimetsu No Yaiba",
                "Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko Nezuko "
            ),
            Buku(
                R.drawable.kuroko,
                "Kuroko No Basket",
                "Diceritakan bahwa ada lima pemain basket SMP yang sangat kuat dan disebut Kiseki No Sedai atau Generasi Keajaiban. Lima pemain itu adalah Kise Ryouta, Murasakibara Atsushi, Aomine Daiki, Midorima Shintaro dan Akashi Seijuro."
            )
        )
        val recyclerView = findViewById<RecyclerView>(R.id.buku)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = BukuAdapter(this, bukulist) {

        }

        btnButton = findViewById(R.id.btnHome)

        btnButton.setOnClickListener {
            val intent = android.content.Intent(this, ButtonActivity::class.java)
            startActivity(intent)
        }
    }
}