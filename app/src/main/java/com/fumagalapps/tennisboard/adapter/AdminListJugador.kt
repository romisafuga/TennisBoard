package com.fumagalapps.tennisboard.adapter

import android.app.Activity
import android.app.ActivityManager
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fumagalapps.tennisboard.R
import com.fumagalapps.tennisboard.data.Tablas
import com.fumagalapps.tennisboard.fragmentos.RegistroJugadorFragment
import com.fumagalapps.tennisboard.model.TbJugadores
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.fumagalapps.tennisboard.fragmentos.InicioFragment
import com.fumagalapps.tennisboard.interfaces.IComunicaFragmentos


class AdminListJugador(
    private val context: Context,
    private val dataset: List<TbJugadores>
) : RecyclerView.Adapter<AdminListJugador.ItemViewHolder>(), View.OnClickListener {
    // referncia a las vistas de cada elemento

    private lateinit var comunicador: IComunicaFragmentos

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.txt_Id)
        val foto: ImageView = itemView.findViewById(R.id.imv_ItemFoto)
        val nombre: TextView = itemView.findViewById(R.id.txv_ItemNombre)
        val genero: TextView = itemView.findViewById(R.id.txv_ItemGenero)
        val brazo: TextView = itemView.findViewById(R.id.txv_ItemBrazo)

        val item: View = itemView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdminListJugador.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_jugador, parent, false)
        comunicador = context as IComunicaFragmentos
        return AdminListJugador.ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AdminListJugador.ItemViewHolder, position: Int) {
        val item = dataset[position]

        with(holder) {
            id.text = item.intId.toString()
            nombre.text = item.strNombre
            genero.text = context.resources.getString(R.string.rdb_masculino)
            if (item.chrGenero == 'F') genero.text =
                context.resources.getString(R.string.rdb_femenino)
            brazo.text = context.resources.getString(R.string.rdb_derecho)
            if (item.chrBrazo == 'I') brazo.text = context.getString(R.string.rdb_izquierdo)
            foto.setImageURI(Uri.parse(item.lnkFoto))
        }

        holder.itemView.setOnClickListener { v: View ->
            comunicador.pasaJugador(holder.id.text.toString())
        }
    }


    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onClick(v: View?) {
        TODO("Not implemented")
    }


}


