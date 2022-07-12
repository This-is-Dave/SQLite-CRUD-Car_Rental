package com.example.responsi1548

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Buku(

val imgbuku: Int,
val judul : String,
val deskripsi : String

) :Parcelable