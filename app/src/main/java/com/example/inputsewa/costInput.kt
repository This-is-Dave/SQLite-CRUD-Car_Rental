package com.example.inputsewa

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class costInput : AppCompatActivity() {

    private lateinit var nama: EditText
    private lateinit var nik: EditText
    private lateinit var email: EditText
    private lateinit var noHp: EditText
    private lateinit var btn_register: Button
    private lateinit var btn_view: Button
    private lateinit var btn_update: Button
    private var btn_delete: Button? = null

    private lateinit var sqliteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: costAdapter? = null
    private var frc: costModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costumer)

        initView()
        initRecycleView()
        sqliteHelper = SQLiteHelper(this)

        btn_register.setOnClickListener { addCost() }
        btn_view.setOnClickListener { getCost() }
        btn_update.setOnClickListener { updateCost() }

        adapter?.setOnclickItem {
            Toast.makeText(this, it.nama, Toast.LENGTH_SHORT).show()

            nama.setText(it.nama)
            nik.setText(it.nik)
            email.setText(it.email)
            noHp.setText(it.noHp)
            frc = it
        }

        adapter?.setOnclickDeleteItem {
            deleteCost(it.id)
        }
    }

    private fun initRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = costAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView() {
        nama = findViewById(R.id.nama)
        nik = findViewById(R.id.nik)
        email = findViewById(R.id.email)
        noHp = findViewById(R.id.noHp)
        btn_register = findViewById(R.id.btn_register)
        btn_view = findViewById(R.id.btn_view)
        btn_update = findViewById(R.id.btn_update)
        btn_delete = findViewById(R.id.btn_delete)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun getCost(){
        val frcList = sqliteHelper.getallCost()
        Log.e("pppp","${frcList.size}")

        adapter?.addItem(frcList)
    }

    private fun addCost() {
        val nama = nama.text.toString()
        val nik = nik.text.toString()
        val email = email.text.toString()
        val noHp = noHp.text.toString()

        if (nama.isEmpty() || nik.isEmpty() || email.isEmpty() || noHp.isEmpty()) {
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        } else {
            val frc = costModel(nama = nama, nik = nik, email = email, noHp = noHp)
            val status = sqliteHelper.insertCost(frc)

            if (status > -1) {
                Toast.makeText(this, "Nama Added", Toast.LENGTH_SHORT).show()
                clearEditText()
                getCost()
            }else{
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateCost() {
        val nama = nama.text.toString()
        val nik = nik.text.toString()
        val email = email.text.toString()
        val noHp = noHp.text.toString()

        if (nama == frc?.nama && nik == frc?.nik && email == frc?.email && noHp == frc?.noHp) {
            Toast.makeText(this, "Data not matched", Toast.LENGTH_SHORT).show()
            return
        }

        if (frc == null) return

        val frc = costModel(id = frc!!.id, nama = nama, nik = nik, email = email, noHp = noHp)
        val status = sqliteHelper.updateCost(frc)
        if(status > -1) {
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
            clearEditText()
            getCost()
        } else {
            Toast.makeText(this, "Data failed to added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteCost(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are You Sure ?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") {
                dialog, _ ->
            sqliteHelper.deleteCost(id)
            getCost()
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
        nama.setText("")
        nik.setText("")
        email.setText("")
        noHp.setText("")
        nama.requestFocus()
    }


}