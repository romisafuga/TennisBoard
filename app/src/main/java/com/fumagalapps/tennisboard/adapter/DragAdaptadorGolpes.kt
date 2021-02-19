package com.fumagalapps.tennisboard.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.DragStartHelper
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.fumagalapps.tennisboard.Helper.IItemTouchHelperAdaptador
import com.fumagalapps.tennisboard.Helper.OnEmpezarDragListener
import com.fumagalapps.tennisboard.R
import com.fumagalapps.tennisboard.interfaces.IComunicaFragmentos
import com.fumagalapps.tennisboard.model.TipoGolpes
import kotlinx.android.synthetic.main.fragment_ajuste_botones.view.*
import kotlinx.android.synthetic.main.item_lista_golpes.view.*
import java.util.*


class DragAdaptadorGolpes(
    private val context: Context,
    private var datos: MutableList<TipoGolpes>,
    private var listener: OnEmpezarDragListener
) : RecyclerView.Adapter<DragAdaptadorGolpes.ItemViewHolder>(), IItemTouchHelperAdaptador {

    private lateinit var comunicador: IComunicaFragmentos

    class ItemViewHolder(private val itemview: View) : RecyclerView.ViewHolder(itemview) {
        var imagboton: ImageButton = itemview.findViewById(R.id.imb_Letra)
        var textGolpe: TextView = itemview.findViewById(R.id.txv_TipoGolpe)
        var textclave: TextView = itemview.findViewById(R.id.txv_ClaveGolpe)
        var crdview: CardView = itemview.findViewById(R.id.crv_Golpes)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DragAdaptadorGolpes.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lista_golpes, parent, false)
        comunicador = context as IComunicaFragmentos
        return DragAdaptadorGolpes.ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = datos[position]

        with(holder) {
            imagboton.setImageDrawable(context.resources.getDrawable(item.letraImagen))
            textGolpe.text = context.resources.getString(item.golpe)
            textclave.text = item.idGolpe.toString()
        }
        // Evento
        holder.crdview.setOnLongClickListener {
            listener.onEmpezarDrag(holder)
            false
        }
    }

    override fun getItemCount(): Int {
        return datos.size
    }


    override fun onItemMove(dePosicion: Int, aPosicion: Int): Boolean {
        Collections.swap(datos, dePosicion, aPosicion)
        notifyItemMoved(dePosicion, aPosicion)
        return true
    }

    override fun onItemDismiss(posicion: Int) {
        datos.removeAt(posicion)
        notifyItemRemoved(posicion)
    }

    fun swapItems(adapterPosition: Int, adapterPosition1: Int) {

    }
}


