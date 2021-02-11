package com.fumagalapps.tennisboard.data

import android.provider.BaseColumns

class Tablas {
    // se define los campos de la tablas de las bases de datos
    class TbJugadores: BaseColumns {
        companion object{
            val INTID = "intId"
            val STRNOMBRE = "strNombre"
            val CHRGENERO = "chrGenero"
            val CHRBRAZO = "chrBrazo"
            val LNKFOTO = "lnkFoto"
        }
    }
}