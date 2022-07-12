package com.example.inputsewa

import java.util.*

data class sewaModel(
    var id:Int = getAutoId(),
    var penyewa: String = "",
    var mobil: String = "",
    var lamasewa: String = ""
) {
    companion object {

        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(100)

        }
    }
}