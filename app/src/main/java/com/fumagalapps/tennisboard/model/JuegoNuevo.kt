package com.fumagalapps.tennisboard.model

data class JuegoNuevo(
    var idJug1: String,
    var nomJug1: String,
    var idJug2: String,
    var nomJug2: String,
    var sets: Int,
    var tiebreak: Int,
    var lnkfoto1: String,
    var lnkfoto2: String
) {
}