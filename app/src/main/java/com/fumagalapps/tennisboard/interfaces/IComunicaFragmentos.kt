package com.fumagalapps.tennisboard.interfaces

interface IComunicaFragmentos {
    // se definen las funciones para cada boton del fragment para tener el control
    // del el MainActiviti atravez de esta insterface
    open  fun juegoPend(){
    }
    open  fun juegoNuevo(){
    }
    open  fun jugadores(){
    }
    open  fun ajustes(){
    }
    open  fun estadJuego(){
    }
    open  fun estadJugador(){
    }
    open  fun ayuda(){
    }
    open  fun acerca(){
    }
    open fun pasaJugador(idJug : String){
    }
}