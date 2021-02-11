package com.fumagalapps.tennisboard.data

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class InitDB :
    SQLiteOpenHelper(AppDBTennisBoard.CONTEXTO, AppDBTennisBoard.DB_Name, null, AppDBTennisBoard.DB_Version) {
    val qryCreaTabla = "CREATE TABLE ${AppDBTennisBoard.TB_Jugadores}(" +
            "${Tablas.TbJugadores.INTID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${Tablas.TbJugadores.STRNOMBRE} TEXT NOT NULL," +
            "${Tablas.TbJugadores.CHRGENERO} TEXT NOT NULL," +
            "${Tablas.TbJugadores.CHRBRAZO} TEXT NOT NULL," +
            "${Tablas.TbJugadores.LNKFOTO} TEXT NOT NULL); "

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(qryCreaTabla)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
