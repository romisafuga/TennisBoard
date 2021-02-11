package com.fumagalapps.tennisboard.data

import android.app.Application
import android.content.Context

class AppDBTennisBoard: Application() {
    // la instruccion companion object es para hacer datos estaticos
    // *** OJO  ***  se debe poner el nombre de esta Application en el Manifiest.xml
    companion object{
        var CONTEXTO : Context? = null     // --> lateinit es para indicar que aqui se define
        // pero despues se inicializa
        var DB : InitDB? = null            // --> para inicializar la base de datos
        val DB_Name = "TennisBoardDB.db"      // --> nombre de la base de datos, no necesita
        // ponersele extension es opcional
        // la extension que quieras ponerle
        val DB_Version = 1                  // --> se empieza con el numero 1 y cuando se hagan
        // cambios a las estructuras de las bases de datos
        // se cambia la version
        val TB_Jugadores = "TbJugadores"    // --> sigue el nombre de la tabla(s) de la bdatos
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXTO = applicationContext
        DB = InitDB()
    }


}