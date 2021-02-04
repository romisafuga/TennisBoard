package com.fumagalapps.tennisboard.fragmentos

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.Toast
import com.fumagalapps.tennisboard.R
import com.fumagalapps.tennisboard.interfaces.IComunicaFragmentos
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_jugadores.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JugadoresFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JugadoresFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var comunica: IComunicaFragmentos
    lateinit var actividad: Activity
    lateinit var edtNombre: TextInputEditText
    lateinit var rdbMasculino: RadioButton
    lateinit var rdbFemenino: RadioButton
    lateinit var rdbDerecho: RadioButton
    lateinit var rdbIzquierdo: RadioButton
    lateinit var btnGaleria: Button
    lateinit var btnFoto: Button
    lateinit var btnGrabar: ImageButton

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
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_inicio, container, false)
        edtNombre = vista.findViewById<TextInputEditText>(R.id.tie_Nombre)
        rdbMasculino = vista.findViewById<RadioButton>(R.id.rdb_Masculino)
        rdbFemenino = vista.findViewById<RadioButton>(R.id.rdb_Femenino)
        rdbDerecho = vista.findViewById<RadioButton>(R.id.rdb_Derecho)
        rdbIzquierdo = vista.findViewById<RadioButton>(R.id.rdb_Izquierdo)
        btnGaleria = vista.findViewById<Button>(R.id.btn_Galeria)
        btnFoto = vista.findViewById<Button>(R.id.btn_Foto)
        btnGrabar = vista.findViewById<ImageButton>(R.id.imb_grabar)

        btnGrabar.setOnClickListener {
            registrarTenista()
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

    fun registrarTenista() {
        var genero: String
        var brazo: String

        if (rdbMasculino.isChecked)
            genero = "M"
        else if (rdbFemenino.isChecked)
            genero = "F"
        else
            genero = "N"

        if (rdbDerecho.isChecked)
            brazo = "D"
        else if (rdbIzquierdo.isChecked)
            brazo = "I"
        else
            genero = "N"

        if (!genero.equals("N") && !genero.equals("N") && edtNombre.text.toString() != null
                && edtNombre.text.toString().trim().equals("")) {

        } else {
            Toast.makeText(actividad, getString(R.string.ald_AlertJugadorMes), Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JugadoresFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JugadoresFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}