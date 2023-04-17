package com.example.myapplication24_02_23

class Producto(idProd : Int, nomProd : String, existencia : Int, precio : Double) {
    var idProd : Int = 0
    var nomProd : String = ""
    var existen :  Int = 0
    var prec : Double = 0.0

    init {
        this.idProd = idProd
        this.nomProd = nomProd
        this.existen = existencia
        this.prec= precio
    }
}