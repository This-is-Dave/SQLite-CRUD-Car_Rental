package com.example.inputsewa

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class mobilModel(
    val imgmobil: Int,
    val judul : String,
    val deskripsi : String

) :Parcelable