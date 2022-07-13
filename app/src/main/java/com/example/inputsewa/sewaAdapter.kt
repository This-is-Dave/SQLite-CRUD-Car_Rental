package com.example.inputsewa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class sewaAdapter: RecyclerView.Adapter<sewaAdapter.SewaViweHolder>() {

    private var frcList: ArrayList<sewaModel> = ArrayList()
    private var onClickItem: ((sewaModel) -> Unit)? = null
    private var onClickDeleteItem: ((sewaModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SewaViweHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_sewa, parent, false)
    )

    override fun onBindViewHolder(holder: SewaViweHolder, position: Int) {
        val frc = frcList[position]
        holder.bindView(frc)
        holder.itemView.setOnClickListener { onClickItem?.invoke(frc) }
        holder.btn_delete.setOnClickListener { onClickDeleteItem?.invoke(frc) }
    }

    override fun getItemCount(): Int {
        return frcList.size
    }

    class SewaViweHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.tvIDSewa)
        private var penyewa = view.findViewById<TextView>(R.id.tvPenyewa)
        private var mobil = view.findViewById<TextView>(R.id.tvMobil)
        private var lamasewa = view.findViewById<TextView>(R.id.tvLamaSewa)
        private var biaya = view.findViewById<TextView>(R.id.tvBiaya)
        var btn_delete = view.findViewById<Button>(R.id.btn_delete)

        fun bindView(frc: sewaModel) {
            id.text = frc.id.toString()
            penyewa.text = frc.penyewa
            mobil.text = frc.mobil
            lamasewa.text = frc.lamasewa
            biaya.text = frc.biaya
        }
    }

    fun addItem(item: ArrayList<sewaModel>) {
        this.frcList = item
        notifyDataSetChanged()
    }

    fun setOnclickItem(callback: (sewaModel) -> Unit) {
        this.onClickItem = callback
    }

    fun setOnclickDeleteItem(callback: (sewaModel) -> Unit) {
        this.onClickDeleteItem = callback
    }
}


