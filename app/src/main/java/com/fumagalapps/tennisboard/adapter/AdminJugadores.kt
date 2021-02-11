package com.fumagalapps.tennisboard.adapter

import android.database.DatabaseUtils
import android.widget.Toast
import com.fumagalapps.tennisboard.data.AppDBTennisBoard
import com.fumagalapps.tennisboard.data.Tablas
import com.fumagalapps.tennisboard.model.TbJugadores
import org.jetbrains.annotations.Contract

class AdminJugadores {
    // Qry para regresar los nombres de los contactos
    fun getAllNames(): ArrayList<TbJugadores> {
        val nombres = arrayListOf<TbJugadores>()
        try {
            val db = AppDBTennisBoard.DB!!.readableDatabase
            // checamos si hay datos guardados
            val numDatos = DatabaseUtils.queryNumEntries(db, AppDBTennisBoard.TB_Jugadores).toInt()
            if (numDatos > 0) {
                val qry =
                    "SELECT ${AppDBTennisBoard.TB_Jugadores}.* FROM ${AppDBTennisBoard.TB_Jugadores}" +
                            " ORDER BY ${Tablas.TbJugadores.STRNOMBRE};"
                val registros = db.rawQuery(qry, null)
                // nos ponemos al inicio de la tabla
                registros.moveToFirst()
                // llenamos el arreglo con los nombres obtenidos
                do {
                    // cualquiera de la 2 maneras sigueintes es correcta
                    var regId = registros.getInt(0)
                    var regNombre = registros.getString(1)
                    var regGenero = registros.getString(2)[0]
                    var regBrazo = registros.getString(3)[0]
                    var regFoto = registros.getString(4)
                    nombres.add(TbJugadores(regId, regNombre, regGenero, regBrazo, regFoto))
                    /*
                    nombres.add(registros.getString(registros.
                                getColumnIndex(Contract.Contacto.NOMBRE))) */
                } while (registros.moveToNext())
                registros.close()
            } else {
                Toast.makeText(
                    AppDBTennisBoard.CONTEXTO, "No hay datos en la tabla",
                    Toast.LENGTH_SHORT
                ).show()
            }
            return nombres
        } catch (ex: Exception) {
            Toast.makeText(
                AppDBTennisBoard.CONTEXTO, "No se pudo recuperar la lista",
                Toast.LENGTH_SHORT
            ).show()
            return nombres
        }
    }

    // Qry para insertar un contacto
    fun addJugador(jugador: TbJugadores) {
        try {
            val db = AppDBTennisBoard.DB!!.writableDatabase
            var qry = "INSERT INTO ${AppDBTennisBoard.TB_Jugadores} (" +
                    "${Tablas.TbJugadores.STRNOMBRE},${Tablas.TbJugadores.CHRGENERO}," +
                    "${Tablas.TbJugadores.CHRBRAZO},${Tablas.TbJugadores.LNKFOTO}) " +
                    "VALUES('${jugador.strNombre}','${jugador.chrGenero}'," +
                    "'${jugador.chrBrazo}','${jugador.lnkFoto}');"
            db.execSQL(qry)
            db.close()
        } catch (ex: Exception) {
            Toast.makeText(
                AppDBTennisBoard.CONTEXTO!!, "No se pudo guardar el contacto",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Qry para actualizar jugadores
    fun updateJugador(jugador: TbJugadores) {
        try {
            val db = AppDBTennisBoard.DB!!.writableDatabase
            var qry = "UPDATE ${AppDBTennisBoard.TB_Jugadores} SET " +
                    "${Tablas.TbJugadores.STRNOMBRE} = '${jugador.strNombre}'," +
                    "${Tablas.TbJugadores.CHRGENERO} = '${jugador.chrGenero}'," +
                    "${Tablas.TbJugadores.CHRBRAZO} = '${jugador.chrBrazo}'," +
                    "${Tablas.TbJugadores.LNKFOTO} = '${jugador.lnkFoto}' " +
                    "WHERE ${jugador.intId} = ${jugador.intId};"
            db.execSQL(qry)
            db.close()
        } catch (ex: Exception) {
            Toast.makeText(
                AppDBTennisBoard.CONTEXTO!!, "No se pudo guardar el contacto",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun deleteContacto(numero: Int) {
        try {
            val db = AppDBTennisBoard.DB!!.readableDatabase
            // checamos si hay datos guardados
            val qry =
                "SELECT COUNT(*) FROM ${AppDBTennisBoard.TB_Jugadores}" +
                        " WHERE ${Tablas.TbJugadores.INTID} = ${numero};"
            val registros = db.rawQuery(qry, null)
            // nos ponemos al inicio de la tabla
            registros.moveToFirst()
            var cuantos = registros.getInt(0)
            db.close()
            if (cuantos > 0) {
                try {
                    val db = AppDBTennisBoard.DB!!.writableDatabase
                    var qry = "DELETE FROM ${AppDBTennisBoard.TB_Jugadores} " +
                            "WHERE ${Tablas.TbJugadores.INTID} = $numero"
                    db.execSQL(qry)
                    db.close()

                } catch (ex: Exception) {
                    Toast.makeText(
                        AppDBTennisBoard.CONTEXTO, "No se pudo eliminar el contacto",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    AppDBTennisBoard.CONTEXTO, "El jugador no existe",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (ex: Exception) {
            Toast.makeText(
                AppDBTennisBoard.CONTEXTO, "No se pudo encontrar el contacto",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}