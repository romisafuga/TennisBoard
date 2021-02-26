package com.fumagalapps.tennisboard

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.fumagalapps.tennisboard.fragmentos.*
import com.fumagalapps.tennisboard.fragmentos.InicioFragment.*
import com.fumagalapps.tennisboard.interfaces.IComunicaFragmentos
import com.fumagalapps.tennisboard.model.JuegoNuevo
import kotlinx.android.synthetic.main.fragment_inicio.*

class MainActivity : AppCompatActivity(), IComunicaFragmentos {

    var contenedor = R.id.frl_ContenedorFrag
    lateinit var sharPref: SharedPreferences
    var ordenBotones: String = "12345"

    var JugPosicion: Int = 0
    var idMJug1: String = ""
    var nomMJug1: String = ""
    var idMJug2: String = ""
    var nomMJug2: String = ""
    var sets: Int = 0
    var tiebreak: Int = 0
    var lnkFoto1: String = ""
    var lnkFoto2:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // asignacion de fragmento al contenedor


        cargarPreferencia()
        supportFragmentManager.beginTransaction().replace(contenedor, InicioFragment()).commit()

    }

    // cargar las preferencias del orden de los botones
    fun cargarPreferencia() {
        sharPref = getSharedPreferences("TennisBoardPreferences", Context.MODE_PRIVATE)
        ordenBotones = sharPref.getString("ordenBotones", "12345").toString()
    }

    // funcion para crear un AlertDialogo y regresarlo
    fun dialogoJugadores(): AlertDialog {

        var builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.ald_jugadoresTit)
            .setMessage(R.string.ald_jugadoresMes)
            .setNegativeButton(R.string.ald_jugadoresNeg,
                DialogInterface.OnClickListener { dialog,
                                                  which ->
                    supportFragmentManager.beginTransaction()
                        .replace(contenedor, RegistroJugadorFragment()).commit()
                })
            .setPositiveButton(R.string.ald_jugadoresPos,
                DialogInterface.OnClickListener { dialog,
                                                  which ->
                    supportFragmentManager.beginTransaction()
                        .replace(contenedor, ListadoJugadorFragment()).commit()
                })
        return builder.create()
    }

    // se definen la funcion para cada uno de los botones
    override fun juegoPend() {
        Toast.makeText(this, "Juego Pendiente ", Toast.LENGTH_SHORT).show()
    }

    override fun juegoNuevo() {
        supportFragmentManager.beginTransaction()
            .replace(contenedor, JuegoNuevoFragment()).commit()
    }

    override fun jugadores() {
        dialogoJugadores().show()
    }

    override fun ajustes() {
        val bundle = Bundle()
        bundle.putString("ordenBotones", ordenBotones)
        val transAjus = this.supportFragmentManager.beginTransaction()
        val AjusteFrag = AjusteBotonesFragment()
        AjusteFrag.arguments = bundle
        transAjus.replace(contenedor, AjusteFrag).commit()

    }


    override fun estadJuego() {
        Toast.makeText(this, "estadisticas Juego ", Toast.LENGTH_SHORT).show()
    }

    override fun estadJugador() {
        Toast.makeText(this, "Estadisticas Jugador ", Toast.LENGTH_SHORT).show()
    }

    override fun ayuda() {
        Toast.makeText(this, "Ayuda ", Toast.LENGTH_SHORT).show()
    }

    override fun acerca() {
        Toast.makeText(this, "acerca ", Toast.LENGTH_SHORT).show()
    }

    override fun pasaJugador(idJug: String, JugPosicion: Int, nombre:String, lnFoto: String) {
        val transEditJug = this.supportFragmentManager.beginTransaction()
        val RegJugFrag = RegistroJugadorFragment()
        val transJuegoNuevo = this.supportFragmentManager.beginTransaction()
        val JuegoNuevoFrag = JuegoNuevoFragment()
        val bundle = Bundle()
        bundle.putString("idJug", idJug)
        when (JugPosicion) {
            0 -> {
                RegJugFrag.arguments = bundle
                transEditJug.replace(contenedor, RegJugFrag).commit()
            }
            else -> {
                bundle.putInt("JugPosicion", JugPosicion)
                if(JugPosicion == 1){
                    bundle.putString("idJug1", idJug)
                    bundle.putString("nomJug1", nombre)
                    bundle.putString("idJug2", idMJug2)
                    bundle.putString("nomJug2", nomMJug2)
                    bundle.putString("lnkFoto1", lnFoto)
                    bundle.putString("lnkFoto2", lnkFoto2)
                } else {
                    bundle.putString("idJug2", idJug)
                    bundle.putString("nomJug2", nombre)
                    bundle.putString("idJug1", idMJug1)
                    bundle.putString("nomJug1", nomMJug1)
                    bundle.putString("lnkFoto1", lnkFoto1)
                    bundle.putString("lnkFoto2", lnFoto)
                }
                bundle.putInt("sets", sets)
                bundle.putInt("tiebreak", tiebreak)
                JuegoNuevoFrag.arguments = bundle
                transJuegoNuevo.replace(contenedor, JuegoNuevoFrag).commit()
            }
        }
    }

    override fun muestraMenu() {
        cargarPreferencia()
        supportFragmentManager.beginTransaction().replace(contenedor, InicioFragment()).commit()
    }

    override fun seleccionaJugador(jugador: Int, juegoNuevo: JuegoNuevo) {
        val bundle=Bundle()

        JugPosicion = jugador
        bundle.putInt("JugPosicion", JugPosicion)
        bundle.putString("idJug1",juegoNuevo.idJug1)
        bundle.putString("idJug2",juegoNuevo.idJug2)
        idMJug1 = juegoNuevo.idJug1
        nomMJug1 = juegoNuevo.nomJug1
        idMJug2 = juegoNuevo.idJug2
        nomMJug2 = juegoNuevo.nomJug2
        sets = juegoNuevo.sets
        tiebreak = juegoNuevo.tiebreak
        lnkFoto1 = juegoNuevo.lnkfoto1
        lnkFoto2 = juegoNuevo.lnkfoto2
        val transEditJug = this.supportFragmentManager.beginTransaction()
        val LisJugFrag = ListadoJugadorFragment()
        LisJugFrag.arguments = bundle
        transEditJug.replace(contenedor, LisJugFrag).commit()

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}