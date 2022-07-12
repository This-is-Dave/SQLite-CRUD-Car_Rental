package com.example.inputsewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class sewa : AppCompatActivity() {


    private lateinit var id_etNamaCustomer: EditText
    private lateinit var mobil: EditText
    private lateinit var id_etlamasewa :EditText
    private lateinit var checkBox1 : CheckBox
    private lateinit var checkBox2 : CheckBox
    private lateinit var btn_tambah_sewa : Button
    private lateinit var btn_view_sewa : Button
    private lateinit var btn_hapus_sewa : Button

    private  lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_sewa)

        initView()
        sqLiteHelper = SQLiteHelper(this)
        btn_tambah_sewa.setOnClickListener{addsewa()}
        btn_view_sewa.setOnClickListener{getsewa()}

    }
    private fun getsewa(){

      }

    private fun addsewa(){
        val mobil = mobil.text.toString()
        val lamasewa = id_etlamasewa.text.toString()
        val ya =checkBox1.text.toString()
        val tidak = checkBox2.text.toString()


        if (mobil.isEmpty() || lamasewa.isEmpty() || ya.isEmpty() || tidak.isEmpty()) {
            Toast.makeText(this, "please enter requiruid field", Toast.LENGTH_SHORT).show()
        }else {

        }

    }
    private fun clearEditText(){
        mobil.setText("")
        id_etlamasewa.setText("")
        checkBox1.setText("")
        checkBox2.setText("")
        mobil.requestFocus()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sewa)
    }

    private fun initView(){
        mobil =findViewById(R.id.mobil)
        id_etlamasewa = findViewById(R.id.id_etlamasewa)
        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
    }
}