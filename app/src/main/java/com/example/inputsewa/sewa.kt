package com.example.inputsewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class sewa : AppCompatActivity() {


    private lateinit var penyewa: EditText
    private lateinit var mobil: EditText
    private lateinit var lamasewa :EditText
    private lateinit var biaya: EditText
    private lateinit var tambah : Button
    private lateinit var view : Button
    private lateinit var update : Button
    private var btn_delete: Button? = null

    private  lateinit var sqLiteHelper: sewaSQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: sewaAdapter? = null
    private var frc: sewaModel? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sewa)

        initView()
        initRecycleView()
        sqLiteHelper = sewaSQLiteHelper(this)

        tambah.setOnClickListener{addsewa() }
        view.setOnClickListener{getsewa() }
        update.setOnClickListener { updatesewa() }

        adapter?.setOnclickItem {
            Toast.makeText(this, it.penyewa, Toast.LENGTH_SHORT).show()

            penyewa.setText(it.penyewa)
            mobil.setText(it.mobil)
            lamasewa.setText(it.lamasewa)
            biaya.setText(it.biaya)
            frc = it
        }

        adapter?.setOnclickDeleteItem {
            deletesewa(it.id)
        }
    }

    private fun initRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = sewaAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView(){
        penyewa = findViewById(R.id.etPenyewa)
        mobil =findViewById(R.id.etMobil)
        lamasewa = findViewById(R.id.etLamasewa)
        biaya = findViewById(R.id.etBiaya)
        tambah = findViewById(R.id.tambah)
        update = findViewById(R.id.update)
        view = findViewById(R.id.view)
        btn_delete = findViewById(R.id.btn_delete)
        recyclerView = findViewById(R.id.recyclerView_sewa)

    }

    private fun getsewa(){
        val frcList = sqLiteHelper.getallSewa()
        Log.e("pppp","${frcList.size}")

        adapter?.addItem(frcList)
      }

    private fun addsewa(){
        val penyewa = penyewa.text.toString()
        val mobil = mobil.text.toString()
        val lamasewa = lamasewa.text.toString()
        val biaya = biaya.text.toString()

        if (penyewa.isEmpty() || mobil.isEmpty() || lamasewa.isEmpty() || biaya.isEmpty()) {
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        } else {
            val frc = sewaModel(penyewa = penyewa, mobil = mobil, lamasewa = lamasewa, biaya = biaya)
            val status = sqLiteHelper.insertSewa(frc)

            if (status > -1) {
                Toast.makeText(this, "Penyewa Added", Toast.LENGTH_SHORT).show()
                clearEditText()
                getsewa()
            }else{
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updatesewa() {
        val penyewa = penyewa.text.toString()
        val mobil = mobil.text.toString()
        val lamasewa = lamasewa.text.toString()
        val biaya = biaya.text.toString()

        if (penyewa == frc?.penyewa && mobil == frc?.mobil && lamasewa == frc?.lamasewa && biaya == frc?.biaya) {
            Toast.makeText(this, "Data not matched", Toast.LENGTH_SHORT).show()
            return
        }

        if (frc == null) return

        val frc = sewaModel(id = frc!!.id, penyewa = penyewa, mobil = mobil, lamasewa = lamasewa, biaya = biaya)
        val status = sqLiteHelper.updateSewa(frc)
        if(status > -1) {
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
            clearEditText()
            getsewa()
        } else {
            Toast.makeText(this, "Data failed to added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deletesewa(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are You Sure ?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") {
                dialog, _ ->
            sqLiteHelper.deleteSewa(id)
            getsewa()
            dialog.dismiss()
            Toast.makeText(this, "Data deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") {
                dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this, "Data failed get deleted", Toast.LENGTH_SHORT).show()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun clearEditText(){
        penyewa.setText("")
        mobil.setText("")
        lamasewa.setText("")
        biaya.setText("")
        penyewa.requestFocus()
    }
}