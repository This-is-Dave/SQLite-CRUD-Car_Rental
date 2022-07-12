package com.example.inputsewa


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class mobilAdapter (private val context: Context, private val buku: List<mobilModel>, val listerner: (mobilModel) -> Unit)
    : RecyclerView.Adapter<mobilAdapter.BukuViewHolder>(){

    class BukuViewHolder(view: View): RecyclerView.ViewHolder(view) {


        val imgmobil = view.findViewById<ImageView>(R.id.img_item_photo)
        val judul = view.findViewById<TextView>(R.id.tv_item_name)
        val deskripsi = view.findViewById<TextView>(R.id.tv_item_description)

        fun bindView(buku: mobilModel, listener: (mobilModel) -> Unit) {
            imgmobil.setImageResource(buku.imgmobil)
            judul.text = buku.judul
            deskripsi.text = buku.deskripsi
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        return BukuViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_mobil, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        holder.bindView(buku[position], listerner)
    }

    override fun getItemCount(): Int = buku.size

}
