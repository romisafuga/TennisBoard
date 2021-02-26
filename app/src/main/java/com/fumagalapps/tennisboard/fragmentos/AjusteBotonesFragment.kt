package com.fumagalapps.tennisboard.fragmentos

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fumagalapps.tennisboard.Helper.MyItemTouchHelperCallBack
import com.fumagalapps.tennisboard.Helper.OnEmpezarDragListener
import com.fumagalapps.tennisboard.R
import com.fumagalapps.tennisboard.adapter.DragAdaptadorGolpes
import com.fumagalapps.tennisboard.data.DataGolpes
import com.fumagalapps.tennisboard.interfaces.IComunicaFragmentos
import com.fumagalapps.tennisboard.model.TipoGolpes



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AjusteBotonesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AjusteBotonesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var itemTouchHelper: ItemTouchHelper?=null

    lateinit var comunica: IComunicaFragmentos
    lateinit var btnAtras: ImageButton
    lateinit var actividad: Activity
    lateinit var areaReciclada: RecyclerView
    lateinit var listHits : MutableList<TipoGolpes>

    var ordenBotones : String? = ""



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
        var vista = inflater.inflate(R.layout.fragment_ajuste_botones, container, false)

        btnAtras = vista.findViewById(R.id.imb_Atras)
        areaReciclada = vista.findViewById(R.id.rcv_golpes)

        val manager  = LinearLayoutManager(actividad)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        areaReciclada.layoutManager = manager
        areaReciclada.setHasFixedSize(true)
        // recuperar el orden de los golpes de la pantalla principal
        ordenBotones= arguments?.getString("ordenBotones", "-1")

        listHits = DataGolpes().cargaGolpes(ordenBotones!!)

        val itemAdapter = DragAdaptadorGolpes(actividad, listHits, object : OnEmpezarDragListener {
            override fun onEmpezarDrag(viewHolder: RecyclerView.ViewHolder?) {
                itemTouchHelper!!.startDrag(viewHolder!!)
            }
        })


        areaReciclada.adapter = itemAdapter

        val dividerItemDecoration = DividerItemDecoration(actividad , manager.orientation)
        areaReciclada.addItemDecoration(dividerItemDecoration)

// Setup ItemTouchHelper
        val callback = MyItemTouchHelperCallBack(itemAdapter,actividad)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper!!.attachToRecyclerView(areaReciclada)

        btnAtras.setOnClickListener {
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
         * @return A new instance of fragment AjusteBotonesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AjusteBotonesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




}