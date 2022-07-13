package com.example.inputsewa

import java.util.*

    class costModel(
        var id : Int = getAutoId(),
        var nama : String = "",
        var nik : String = "",
        var email : String = "",
        var noHp : String = "") {
        companion object {
            fun getAutoId(): Int {
                val random = Random()
                return random.nextInt(100)
            }
        }
    }
