package com.fumagalapps.tennisboard.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fumagalapps.tennisboard.R
import com.fumagalapps.tennisboard.model.TipoGolpes
import kotlinx.android.synthetic.main.lista_golpes.view.*

class DragAdaptadorGolpes(
    private val context: Context,
    private val datos: List<TipoGolpes>
) : RecyclerView.Adapter<DragAdaptadorGolpes.ItemViewHolder>() {

    class ItemViewHolder(private val view:View):RecyclerView.ViewHolder(view){
        val imagboton: ImageButton = view.findViewById(R.id.imb_Letra)
        val textGolpe : TextView = view.findViewById(R.id.txv_TipoGolpe)
        val textclave : TextView = view.findViewById(R.id.txv_ClaveGolpe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}