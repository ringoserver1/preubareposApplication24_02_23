package com.example.myapplication24_02_23

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class activity_register : AppCompatActivity() {
    var admin=AdminBD(this)

     lateinit var correo:TextInputEditText
     lateinit var usua:TextInputEditText
     lateinit var conUsu:TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        correo =findViewById<TextInputEditText>(R.id.txtNom)
        usua = findViewById<TextInputEditText>(R.id.txtUsuario)
        conUsu = findViewById<TextInputEditText>(R.id.txtContraseña)

        var R1 = admin.Ejecuta("insert Into Producto(nomProd,existencia,precio) values('ataladro', 8, 850.00)")
        var R2 = admin.Ejecuta("insert Into Producto(nomProd,existencia,precio) values('desarmador', 8, 850.00)")
        var R3 = admin.Ejecuta("insert Into Producto(nomProd,existencia,precio) values('martillo', 9, 950.00)")
        var R4 = admin.Ejecuta("insert Into Producto(nomProd,existencia,precio) values('fred', 8, 850.00)")
        var R5 = admin.Ejecuta("insert Into Producto(nomProd,existencia,precio) values('siiiii', 8, 850.00)")
        var R6 = admin.Ejecuta("insert Into Producto(nomProd,existencia,precio) values('noooo', 8, 850.00)")
        var R7 = admin.Ejecuta("insert Into Producto(nomProd,existencia,precio) values('talvez,sss', 8, 850.00)")


    }

    override fun onBackPressed() {}

    fun btnRegister(view: View) {
        if(correo.text.isNullOrEmpty()){
            correo.setError("error se requiere el correo")
            correo.requestFocus()
        }
        else{
            if (usua.text.isNullOrEmpty()){
                usua.setError("error")
                usua.requestFocus()
            }
            else{
                if (conUsu.text.isNullOrEmpty()){
                    conUsu.setError("error")
                    conUsu.requestFocus()
                }
                else{
                    val corr : String = correo.text.toString()
                    val usuario : String = usua.text.toString()
                    val contra : String = conUsu.text.toString()
                    val sentencia : String ="Insert into Registro(correo,NomUsr,contraseña) values('$corr', '$usuario', '$contra')"
                    if (admin.Ejecuta(sentencia)){
                        var actividad = Intent(this, MainActivity::class.java)
                        startActivity(actividad)
                    }
                    else{
                        Toast.makeText( this, "error al registrar", Toast.LENGTH_SHORT).show()
                        correo.requestFocus()
                    }
                }
            }
        }
    }
}