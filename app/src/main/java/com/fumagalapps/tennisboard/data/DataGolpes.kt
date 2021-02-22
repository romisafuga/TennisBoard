package com.fumagalapps.tennisboard.data

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.fumagalapps.tennisboard.R
import com.fumagalapps.tennisboard.model.TipoGolpes

class DataGolpes {
    var listaPaso: MutableList<TipoGolpes> = mutableListOf<TipoGolpes>()
    fun cargaGolpes(ordenBotones: String): MutableList<TipoGolpes> {
        var lista = listOf<TipoGolpes>(
            TipoGolpes(R.string.hit_derecha, R.mipmap.ic_drive, 1),
            TipoGolpes(R.string.hit_reves, R.mipmap.ic_reves, 2),
            TipoGolpes(R.string.hit_Passhot, R.mipmap.ic_passhot, 3),
            TipoGolpes(R.string.hit_volea, R.mipmap.ic_volea, 4),
            TipoGolpes(R.string.hit_saque, R.mipmap.ic_saque, 5)
        ).toMutableList()

        ordenBotones.map {
            val x = lista.size - 1
            for (y in 0..x)
                if (lista[y].idGolpe == it.toString().toInt()) {
                    listaPaso.add(lista[y])
                }
            }
        return listaPaso
    }
}