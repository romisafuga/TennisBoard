package com.fumagalapps.tennisboard.fragmentos

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.fumagalapps.tennisboard.R
import com.fumagalapps.tennisboard.adapter.AdminJugadores
import com.fumagalapps.tennisboard.interfaces.IComunicaFragmentos
import com.fumagalapps.tennisboard.model.JuegoNuevo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JuegoNuevoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JuegoNuevoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var btn_atras: ImageButton

    // views del primer jugador
    lateinit var btn_jugador1: Button
    lateinit var imv_jugador1: ImageView
    lateinit var txv_idjug1: TextView
    lateinit var txv_jugador1: TextView

    // views del segundo jugador
    lateinit var btn_jugador2: Button
    lateinit var imv_jugador2: ImageView
    lateinit var txv_idjug2: TextView
    lateinit var txv_jugador2: TextView

    // views de los settings
    lateinit var rdg_sets: RadioGroup
    lateinit var rdb_sets1: RadioButton
    lateinit var rdb_sets2: RadioButton
    lateinit var rdb_sets3: RadioButton
    lateinit var rdg_tiebreak: RadioGroup
    lateinit var rdb_tiebreak1: RadioButton
    lateinit var rdb_tiebreak2: RadioButton

    // para pasar al listado
    var idJNJug1: String? = null
    var idJNJug2: String? = null
    var nomJNJug1: String? = null
    var nomJNJug2: String? = null
    var set: Int? = 0
    var tiebreak: Int? = 0
    var lnkJNFoto1: String? = null
    var lnkJNFoto2: String? = null

    lateinit var comunica: IComunicaFragmentos
    lateinit var actividad: Activity

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
        // Inflate the layout for this fragment
        var vista = inflater.inflate(R.layout.fragment_juego_nuevo, container, false)
        btn_atras = vista.findViewById(R.id.imb_Atras)
        btn_jugador1 = vista.findViewById(R.id.btn_Jugador1)
        txv_idjug1 = vista.findViewById(R.id.txv_IdJug1)
        txv_jugador1 = vista.findViewById(R.id.txv_Jugador1)
        imv_jugador1 = vista.findViewById(R.id.imv_Jugador1)
        btn_jugador2 = vista.findViewById(R.id.btn_Jugador2)
        txv_idjug2 = vista.findViewById(R.id.txv_IdJug2)
        txv_jugador2 = vista.findViewById(R.id.txv_Jugador2)
        imv_jugador2 = vista.findViewById(R.id.imv_Jugador2)

        rdg_sets = vista.findViewById(R.id.rdg_Sets)
        rdb_sets1 = vista.findViewById(R.id.rdb_Set1)
        rdb_sets2 = vista.findViewById(R.id.rdb_Set2)
        rdb_sets3 = vista.findViewById(R.id.rdb_Set3)

        rdg_tiebreak = vista.findViewById(R.id.rdg_TieBreak)
        rdb_tiebreak1 = vista.findViewById(R.id.rdb_TieBreak1)
        rdb_tiebreak2 = vista.findViewById(R.id.rdb_TieBreak2)


        // recuperar valores del bundle

        idJNJug2 = arguments?.getString("idJug2", "")
        idJNJug1 = arguments?.getString("idJug1", "")
        nomJNJug1 = arguments?.getString("nomJug1", "")
        nomJNJug2 = arguments?.getString("nomJug2", "")
        lnkJNFoto1 = arguments?.getString("lnkFoto1", "")
        lnkJNFoto2 = arguments?.getString("lnkFoto2", "")

        set = arguments?.getInt("sets", 0)
        tiebreak = arguments?.getInt("tiebreak", 0)

        txv_idjug1.setText(idJNJug1)
        txv_jugador1.setText(nomJNJug1)
        txv_idjug2.setText(idJNJug2)
        txv_jugador2.setText(nomJNJug2)
        if(lnkJNFoto1!=null&&lnkJNFoto1?:""!="") imv_jugador1.setImageURI(
            Uri.parse(lnkJNFoto1!!))
        if(lnkJNFoto2!=null&&lnkJNFoto2?:""!="") imv_jugador2.setImageURI(
            Uri.parse(lnkJNFoto2!!))

        when (set) {
            1 -> rdg_sets.check(rdb_sets1.id)
            2 -> rdg_sets.check(rdb_sets2.id)
            3 -> rdg_sets.check(rdb_sets3.id)
            else -> rdg_sets.clearCheck()
        }

        when (tiebreak) {
            1 -> rdg_tiebreak.check(rdb_tiebreak1.id)
            2 -> rdg_tiebreak.check(rdb_tiebreak2.id)
            else -> rdg_tiebreak.clearCheck()
        }


        btn_atras.setOnClickListener {
            comunica.muestraMenu()
        }
        // ir a seleccionar el primer jugador
        btn_jugador1.setOnClickListener {
            seleccJugador(1)
        }
        btn_jugador2.setOnClickListener {
            seleccJugador(2)
        }


        // validar que existan mas de 2 o mas jugadores registrados para crear un juego
        if (adminJugador.cuentaJugadores() < 2) {
            dialogoValidaJugadores().show()
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

    // funcion para crear un AlertDialogo y regresarlo
    fun dialogoValidaJugadores(): AlertDialog {

        var builder = AlertDialog.Builder(actividad)
        builder.setTitle(R.string.ald_jugadoresTit)
            .setMessage(R.string.ald_JuegoMesFalta)
            .setNeutralButton(R.string.ald_Ok,
                DialogInterface.OnClickListener { dialog,
                                                  which ->
                    comunica.muestraMenu()
                })
        return builder.create()
    }

    fun seleccJugador(participa: Int) {
        idJNJug1 = txv_idjug1.text.toString()
        idJNJug2 = txv_idjug2.text.toString()
        nomJNJug1 = txv_jugador1.text.toString()
        nomJNJug2 = txv_jugador2.text.toString()
        set = when (rdg_sets.checkedRadioButtonId) {
            rdb_sets1.id -> 1
            rdb_sets2.id -> 2
            rdb_sets3.id -> 3
            else -> 0
        }
        tiebreak = when (rdg_tiebreak.checkedRadioButtonId) {
            rdb_tiebreak1.id -> 1
            rdb_tiebreak2.id -> 2
            else -> 0
        }
        val JuegoNuevo = com.fumagalapps.tennisboard.model.JuegoNuevo(
            idJNJug1!!,
            nomJNJug1!!,
            idJNJug2!!,
            nomJNJug2!!,
            set!!,
            tiebreak!!,
            lnkJNFoto1?:"",
            lnkJNFoto2?:""
        )
        comunica.seleccionaJugador(participa, JuegoNuevo)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JuegoNuevoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JuegoNuevoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}