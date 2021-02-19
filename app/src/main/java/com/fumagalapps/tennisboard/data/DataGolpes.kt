package com.fumagalapps.tennisboard.data

import com.fumagalapps.tennisboard.R
import com.fumagalapps.tennisboard.model.TipoGolpes

class DataGolpes {
    fun cargaGolpes(): MutableList<TipoGolpes> {
        return listOf<TipoGolpes>(
            TipoGolpes(R.string.hit_derecha, R.mipmap.ic_drive, 1),
            TipoGolpes(R.string.hit_reves, R.mipmap.ic_reves, 2),
            TipoGolpes(R.string.hit_Passhot, R.mipmap.ic_passhot, 3),
            TipoGolpes(R.string.hit_volea, R.mipmap.ic_volea, 4),
            TipoGolpes(R.string.hit_saque, R.mipmap.ic_saque, 5)
        ).toMutableList()
    }
}