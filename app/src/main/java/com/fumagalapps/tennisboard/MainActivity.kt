package com.fumagalapps.tennisboard

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.fumagalapps.tennisboard.fragmentos.InicioFragment
import com.fumagalapps.tennisboard.fragmentos.InicioFragment.*
import com.fumagalapps.tennisboard.interfaces.IComunicaFragmentos
import kotlinx.android.synthetic.main.fragment_inicio.*

class MainActivity : AppCompatActivity(), IComunicaFragmentos {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // asignacion de fragmento al contenedor

        var contenedor = R.id.frl_ContenedorFrag
        supportFragmentManager.beginTransaction().replace(contenedor, InicioFragment()).commit()

    }

    // funcion para crear un AlertDialogo y regresarlo
    fun dialogoJugadores(): AlertDialog {
        var builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.ald_jugadoresTit)
                .setMessage(R.string.ald_jugadoresMes)
                .setNegativeButton(R.string.ald_jugadoresNeg,
                        DialogInterface.OnClickListener { dialog,
                                                          which ->
                            Toast.makeText(this, "Registrar Jugador ",
                                    Toast.LENGTH_SHORT).show()})
                .setPositiveButton(R.string.ald_jugadoresPos,
                        DialogInterface.OnClickListener { dialog, which ->
                            Toast.makeText(this, "Seleccionar Jugador ",
                                    Toast.LENGTH_SHORT).show()})
        return builder.create()
    }

    // se definen la funcion para cada uno de los botones
    override fun juegoPend() {
        Toast.makeText(this, "Juego Pendiente ", Toast.LENGTH_SHORT).show()
    }

    override fun juegoNuevo() {
        Toast.makeText(this, "Juego Nuevo ", Toast.LENGTH_SHORT).show()
    }

    override fun jugadores() {
        dialogoJugadores().show()
    }

    override fun ajustes() {
        Toast.makeText(this, "Ajuste ", Toast.LENGTH_SHORT).show()
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


}