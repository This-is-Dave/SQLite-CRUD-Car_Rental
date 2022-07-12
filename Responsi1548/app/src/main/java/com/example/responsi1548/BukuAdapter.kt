package com.example.responsi1548

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BukuAdapter(private val context: Context, private val buku: List<Buku>, val listerner: (Buku) -> Unit)
    : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>(){

    class BukuViewHolder(view: View): RecyclerView.ViewHolder(view) {


        val imgbuku = view.findViewById<ImageView>(R.id.img_item_photo)
        val judul = view.findViewById<TextView>(R.id.tv_item_name)
        val deskripsi = view.findViewById<TextView>(R.id.tv_item_description)

        fun bindView(buku: Buku, listener: (Buku) -> Unit) {
            imgbuku.setImageResource(buku.imgbuku)
            judul.text = buku.judul
            deskripsi.text = buku.deskripsi
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        return BukuViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_buku, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
       holder.bindView(buku[position], listerner)
    }

    override fun getItemCount(): Int = buku.size

    }
}