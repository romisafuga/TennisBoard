package com.fumagalapps.tennisboard.model

import android.annotation.SuppressLint
import android.media.Image
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

@SuppressLint("SupportAnnotationUsage")
data class TipoGolpes(
    @StringRes val golpe: Int,
    @DrawableRes val letraImagen: Int,
    val idGolpe: Int
) {

}