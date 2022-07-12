package com.example.inputsewa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class costAdapter : RecyclerView.Adapter<costAdapter.CostViewHolder>(){

    private var frcList: ArrayList<costModel> = ArrayList()
    private var onClickItem:((costModel) -> Unit)? = null
    private var onClickDeleteItem:((costModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CostViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_costumer, parent, false)
    )

    override fun onBindViewHolder(holder: CostViewHolder, position: Int) {
        val frc = frcList[position]
        holder.bindView(frc)
        holder.itemView.setOnClickListener{ onClickItem?.invoke(frc) }
        holder.btn_delete.setOnClickListener{ onClickDeleteItem?.invoke(frc) }
    }

    override fun getItemCount(): Int {
        return frcList.size
    }

    class CostViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.tvID)
        private var nama = view.findViewById<TextView>(R.id.tvName)
        private var nik = view.findViewById<TextView>(R.id.tvNIK)
        private var email = view.findViewById<TextView>(R.id.tvEmail)
        private var noHP = view.findViewById<TextView>(R.id.tvNoHP)
        var btn_delete = view.findViewById<Button>(R.id.btn_delete)

        fun bindView(frc: costModel) {
            id.text = frc.id.toString()
            nama.text = frc.nama
            nik.text = frc.nik
            email.text = frc.email
            noHP.text = frc.noHp
        }
    }

    fun addItem(item : ArrayList<costModel>) {
        this.frcList = item
        notifyDataSetChanged()
    }

    fun setOnclickItem(callback: (costModel) -> Unit) {
        this.onClickItem = callback
    }

    fun setOnclickDeleteItem(callback: (costModel) -> Unit) {
        this.onClickDeleteItem = callback
    }


}