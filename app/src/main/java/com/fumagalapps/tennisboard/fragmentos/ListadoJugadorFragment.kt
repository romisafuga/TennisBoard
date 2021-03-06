package com.fumagalapps.tennisboard.fragmentos

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fumagalapps.tennisboard.R
import com.fumagalapps.tennisboard.adapter.AdminJugadores
import com.fumagalapps.tennisboard.adapter.AdminListJugador
import com.fumagalapps.tennisboard.interfaces.IComunicaFragmentos
import kotlinx.android.synthetic.main.fragment_listado_jugador.*
import kotlinx.android.synthetic.main.fragment_registro_jugadores.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListadoJugadorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListadoJugadorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var comunica: IComunicaFragmentos
    lateinit var btnAtras: ImageButton
    lateinit var actividad: Activity
    lateinit var areaReciclada: RecyclerView

    var JugPosicion: Int? = 0
    var idJug1: String? = ""
    var idJug2: String? = ""

    val adminJugador = AdminJugadores()

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

        //recupero valor de posicion de jugador para saber si viene de juego nuevo
        JugPosicion = arguments?.getInt("JugPosicion", 0)
        idJug1 = arguments?.getString("idJug1", "")
        idJug2 = arguments?.getString("idJug2", "")

        if (JugPosicion == null) JugPosicion = 0
        // Inflate the layout for this fragment

        var vista = inflater.inflate(R.layout.fragment_listado_jugador, container, false)
        btnAtras = vista.findViewById(R.id.imb_Atras)

        areaReciclada = vista.findViewById(R.id.rcv_ListadoJugador)
        var manager = LinearLayoutManager(actividad)
        areaReciclada.layoutManager = manager
        areaReciclada.setHasFixedSize(true)


        // llenar el adaptador con los jugadores
        val misDatos = adminJugador.getAllNames()
        // eliminar los jugadores ya seleccionados para jugar
        // esto aplica para la pantalla de juego nuevo solamente
        for (i in 0..misDatos.size - 1) {
            if ((JugPosicion == 1 && idJug2 == misDatos[i].intId.toString()) ||
                (JugPosicion == 2 && idJug1 == misDatos[i].intId.toString())
            ) {
                misDatos.removeAt(i)
                break
            }
        }

        areaReciclada.adapter = AdminListJugador(actividad, misDatos, JugPosicion!!)

        btnAtras.setOnClickListener {
            if (JugPosicion == 0)
                comunica.muestraMenu()
        }

        return vista
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
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
         * @return A new instance of fragment ListadoJugadorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListadoJugadorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}