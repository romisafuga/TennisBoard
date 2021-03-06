package com.fumagalapps.tennisboard.interfaces

import com.fumagalapps.tennisboard.model.JuegoNuevo

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
    open fun pasaJugador(idJug : String, JugPosicion:Int, nombre : String, lnFoto : String){
    }
    open fun muestraMenu(){
    }
    open fun seleccionaJugador(jugParticipante : Int, juegoNuevo: JuegoNuevo) {
     }
}