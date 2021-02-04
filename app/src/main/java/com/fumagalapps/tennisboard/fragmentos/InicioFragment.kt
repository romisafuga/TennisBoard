package com.fumagalapps.tennisboard.fragmentos

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.fumagalapps.tennisboard.R
import com.fumagalapps.tennisboard.interfaces.IComunicaFragmentos
import kotlinx.android.synthetic.main.fragment_inicio.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InicioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InicioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var comunica : IComunicaFragmentos
    lateinit var actividad :Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val vista = inflater.inflate(R.layout.fragment_inicio, container, false)
        // programar la acción de cada boton
        // boton de juego pendiente
        val crvOptJuePend = vista.findViewById<View>(R.id.crv_OptJuePend)
        crvOptJuePend.setOnClickListener{
            comunica.juegoPend()
        }
        // boton de juego nuevo
        val crvOptJueNuevo = vista.findViewById<View>(R.id.crv_OptJueNuevo)
        crvOptJueNuevo.setOnClickListener {
            comunica.juegoNuevo()
        }
        // boton de jugadores
        val crvOptJuegadores = vista.findViewById<View>(R.id.crv_OptJugadores)
        crvOptJuegadores.setOnClickListener{
            comunica.jugadores()
        }
        // boton de ajustes
        val crvOptAjustes = vista.findViewById<View>(R.id.crv_OptAjustes)
        crvOptAjustes.setOnClickListener {
            comunica.ajustes()
        }
        // boton de estadisticas de juego
        val crvOptEstJuego = vista.findViewById<View>(R.id.crv_OptEstJuego)
        crvOptEstJuego.setOnClickListener{
            comunica.estadJuego()
        }
        // boton de estadistican de jugadores
        val crvOptEstJugador = vista.findViewById<View>(R.id.crv_OptEstJugador)
        crvOptEstJugador.setOnClickListener {
            comunica.estadJugador()
        }
        // boton de Ayuda
        val crvOptAyuda = vista.findViewById<View>(R.id.crv_OptAyuda)
        crvOptAyuda.setOnClickListener{
            comunica.ayuda()
        }
        // boton de Acerca de
         val crvOptAcerca = vista.findViewById<View>(R.id.crv_OptAcerca)
        crvOptAcerca.setOnClickListener{
            comunica.acerca()
        }
        /* crvOptAcerca.setOnClickListener {
            Toast.makeText(context, "Acerca de ", Toast.LENGTH_SHORT).show()
        } */


        // Inflate the layout for this fragment
        return vista
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Activity){
            actividad = context
            comunica = actividad as IComunicaFragmentos
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InicioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InicioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}