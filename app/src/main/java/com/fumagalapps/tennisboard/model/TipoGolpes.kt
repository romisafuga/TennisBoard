package com.fumagalapps.tennisboard.model

import android.annotation.SuppressLint
import android.media.Image
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

@SuppressLint("SupportAnnotationUsage")
data class TipoGolpes(
    @StringRes var golpe: Int,
    @DrawableRes var letraImagen: Int,
    var idGolpe: Int
) {

}