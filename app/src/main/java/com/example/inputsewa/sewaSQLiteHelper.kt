package com.example.inputsewa

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID

class sewaSQLiteHelper(context: Context) :
    SQLiteOpenHelper (context, DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "sewa.db"
        private const val TBL_SEWA = "tbl_sewa"
        private const val ID = "id"
        private const val PENYEWA = "penyewa"
        private const val MOBIL = "mobil"
        private const val LAMASEWA = "lamasewa"
        private const val BIAYA = "biaya"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblsewa = ("CREATE TABLE " + TBL_SEWA + "("
                + ID + " INTEGER PRIMARY KEY," + PENYEWA + " TEXT,"
                + MOBIL + " TEXT," + LAMASEWA + " TEXT," + BIAYA + " TEXT" + ")")
        db?.execSQL(createTblsewa)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion : Int, newVersion : Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_SEWA")
        onCreate(db)
    }

    fun insertSewa(frc : sewaModel ): Long {
        val db = this.writableDatabase

        val  contentValues = ContentValues()
        contentValues.put(ID, frc.id)
        contentValues.put(PENYEWA, frc.penyewa)
        contentValues.put(MOBIL, frc.mobil)
        contentValues.put(LAMASEWA, frc.lamasewa)
        contentValues.put(BIAYA, frc.biaya)

        val success = db.insert(TBL_SEWA,null, contentValues)
        db.close()
        return success
    }

    fun updateSewa(frc: sewaModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, frc.id)
        contentValues.put(PENYEWA, frc.penyewa)
        contentValues.put(MOBIL, frc.mobil)
        contentValues.put(LAMASEWA, frc.lamasewa)
        contentValues.put(BIAYA, frc.biaya)

        val success = db.update(TBL_SEWA, contentValues, "id=" + frc.id, null)
        db.close()
        return success
    }

    fun deleteSewa(id: Int): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_SEWA, "id=$id", null)
        db.close()
        return success
    }

    fun getallSewa():ArrayList<sewaModel>{
        val frcList: ArrayList<sewaModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_SEWA"
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
         var penyewa : String
         var mobil : String
         var lamasewa : String
         var biaya : String

        if (cursor.moveToFirst()) {
            do {
                id =cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                penyewa = cursor.getString(cursor.getColumnIndexOrThrow("penyewa"))
                mobil = cursor.getString(cursor.getColumnIndexOrThrow("mobil"))
                lamasewa = cursor.getString(cursor.getColumnIndexOrThrow("lamasewa"))
                biaya = cursor.getString(cursor.getColumnIndexOrThrow("biaya"))

                   val frc = sewaModel(id = id, penyewa = penyewa,
                       mobil = mobil, lamasewa = lamasewa, biaya = biaya)
                frcList.add(frc)
            } while (cursor.moveToNext())
        }
        return frcList
        }
}