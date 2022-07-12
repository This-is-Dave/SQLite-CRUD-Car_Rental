package com.example.inputsewa

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper (context, DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "costumer.db"
        private const val TBL_COST = "tbl_cost"
        private const val ID = "id"
        private const val NAMA = "nama"
        private const val NIK = "nik"
        private const val EMAIL ="email"
        private const val NOHP = "noHp"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblcostumer = ("CREATE TABLE " + TBL_COST + "("
                + ID + " INTEGER PRIMARY KEY," + NAMA + " TEXT,"
                + NIK + " TEXT," + EMAIL + " TEXT," + NOHP + " TEXT" + ")")
        db?.execSQL(createTblcostumer)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion : Int, newVersion : Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_COST")
        onCreate(db)
    }

    fun insertCost(frc : costModel ): Long {
        val db = this.writableDatabase

        val  contentValues = ContentValues()
        contentValues.put(ID, frc.id)
        contentValues.put(NAMA, frc.nama)
        contentValues.put(NIK, frc.nik)
        contentValues.put(EMAIL, frc.email)
        contentValues.put(NOHP, frc.noHp)

        val success = db.insert(TBL_COST,null, contentValues)
        db.close()
        return success
    }

    fun updateCost(user: costModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, user.id)
        contentValues.put(NAMA, user.nama)
        contentValues.put(NIK, user.nik)
        contentValues.put(EMAIL, user.email)
        contentValues.put(NOHP, user.noHp)

        val success = db.update(TBL_COST, contentValues, "id=" + user.id, null)
        db.close()
        return success
    }

    fun deleteCost(id: Int): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_COST, "id=$id", null)
        db.close()
        return success
    }

    fun getallCost():ArrayList<costModel>{
        val frcList: ArrayList<costModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_COST"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

         var id : Int
         var nama : String
         var nik : String
         var email : String
         var noHp : String

        if (cursor.moveToFirst()) {
            do {
                id =cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                nama = cursor.getString(cursor.getColumnIndexOrThrow("nama"))
                nik = cursor.getString(cursor.getColumnIndexOrThrow("nik"))
                email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                noHp = cursor.getString(cursor.getColumnIndexOrThrow("noHp"))

                   val frc = costModel(id = id, nama = nama,
                       nik = nik, email = email, noHp = noHp)
                frcList.add(frc)
            } while (cursor.moveToNext())
        }
        return frcList
        }
}