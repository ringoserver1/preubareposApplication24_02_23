package com.example.myapplication24_02_23

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import kotlin.math.log

class AdminBD(context : Context): SQLiteOpenHelper(context, "BDInventario",null,1)
{
    override fun onCreate(bd: SQLiteDatabase?) {
        if (bd!=null){
            bd.execSQL("Create Table Registro(correo text primary key," +
                    "NomUsr Text, contraseña Text)")
            //si quiero agregar otra:
            //bd.execSQL()
            //otra tabla
            bd.execSQL("create Table Producto(idProd Integer primary key AUTOINCREMENT," +
                    "nomProd text, existencia Integer, precio double)")
        }
    }

    fun Ejecuta(sentencia : String) : Boolean{
        try {
            val bd = this.writableDatabase
            bd.execSQL(sentencia)
            bd.close()
            return true
        }
        catch (ex : java.lang.Exception){
            Log.d("goose",ex.toString())
            return false
        }
    }
    fun consultar(query : String) : Cursor?{
        try {
            val bd = this.readableDatabase
            return bd.rawQuery(query,null)

        }
        catch (ex : java.lang.Exception){
            Log.d("gus", ex.message.toString() + "-->" + query)
            return null
        }
    }
    //----------------------------------------------------------------------
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}