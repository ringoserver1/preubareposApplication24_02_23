//holaaa
package com.example.myapplication24_02_23

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivityProd : AppCompatActivity() {
    lateinit var txtidP : TextInputEditText
    lateinit var txtnom : TextInputEditText
    lateinit var txtexi : TextInputEditText
    lateinit var txtpre : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_prod)

        txtidP = findViewById<TextInputEditText>(R.id.txtidProd)
        txtnom = findViewById<TextInputEditText>(R.id.txtnomProd)
        txtexi = findViewById<TextInputEditText>(R.id.txtExistencia)
        txtpre = findViewById<TextInputEditText>(R.id.txtPrecio)
        var actividad = intent
        if (actividad.getIntExtra("idprod", 0).toString() == "0") {
            txtidP.setText("")
        } else {
            txtidP.setText(actividad.getIntExtra("idprod", 0).toString())
        }
        txtnom.setText(actividad.getStringExtra("nomprod").toString())
        txtexi.setText(actividad.getIntExtra("exis", 0).toString())
        txtpre.setText(actividad.getDoubleExtra("precio", 0.0).toString())
    }




        fun btnGuardar(view: View) {
            var sentencia: String = ""
            var admin = AdminBD(this)
            if (txtnom.text.isNullOrEmpty()) {
                txtnom.setError("error se requiere el nombre")
                txtnom.requestFocus()
            } else {
                if (txtexi.text.isNullOrEmpty()) {
                    txtexi.setError("error se requiere la existencia")
                    txtexi.requestFocus()
                } else {
                    if (txtpre.text.isNullOrEmpty()) {
                        txtpre.setError("error se requier el precio")
                        txtpre.requestFocus()
                    } else {
                        val nom: String = txtnom.text.toString()
                        val exis: Int = txtexi.text.toString().toInt()
                        val pre: Double = txtpre.text.toString().toDouble()
                        if (txtidP.text.isNullOrEmpty()) {//insertar
                            sentencia =
                                "Insert into Producto(nomProd,existencia,precio) values('$nom', $exis, $pre)"
                        } else {//actualizar
                            sentencia =
                                "Update Producto Set nomProd='$nom',existencia=$exis, precio=$pre Where idProd=" + txtidP.text.toString()
                        }
                        if (admin.Ejecuta(sentencia)) {
                            //Toast.makeText(this, "guardadoooo", Toast.LENGTH_SHORT).show()
                            val actividad = Intent(this, MainActivity::class.java)
                            startActivity(actividad)
                        } else {
                            Toast.makeText(this, "error no se guardo", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }