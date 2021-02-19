package com.fumagalapps.tennisboard.Helper

interface IItemTouchHelperAdaptador {
    fun onItemMove(dePosicion: Int, aPosicion:Int):Boolean
    fun onItemDismiss(posicion:Int)
}