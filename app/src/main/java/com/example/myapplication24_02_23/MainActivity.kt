package com.example.myapplication24_02_23

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var admin = AdminBD(this)
   // lateinit var tvBien : TextView
    private lateinit var rvProdList : RecyclerView
    private lateinit var viewAdapter : ProductoAdapter
    private  lateinit var viewManager : RecyclerView.LayoutManager
    private var productoLista : List<Producto> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val query = "select correo,nomUsr,contraseña From Registro"
        val result : Cursor? = admin.consultar(query)

        if (result != null && result.moveToFirst()){
            //tvBien.text=result.getString(1).toString()
            result.close()
        }
        else{
            var actividad = Intent(this, activity_register::class.java)
            startActivity(actividad)
        }
        //--------------------------------------------------inicia recycler
            rvProdList = findViewById<RecyclerView>(R.id.rvListaProductos)
            viewManager = LinearLayoutManager(this)
            viewAdapter = ProductoAdapter(productoLista, this, {prod : Producto -> onItemClickListener(prod)})

            rvProdList.apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter

                addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            }

            //desplazar a la derecha para que se elimine registro
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val indice = viewHolder.adapterPosition
                    val prod = viewAdapter.getTasks()

                    if(admin.Ejecuta("Delete from Producto where idProd = " + prod[indice].idProd)){
                        retriveProductos()
                    }
                }

            }).attachToRecyclerView(rvProdList)


        //--------------------------------------------------termina
    }



    private fun onItemClickListener(prod: Producto) { //cuando damos click en un elemento del recyclre view
       // Toast.makeText(this, "diste click a:" + prod.nomProd, Toast.LENGTH_SHORT).show()
        var acti = Intent(this,MainActivityProd::class.java)
        acti.putExtra("idprod", prod.idProd)
        acti.putExtra("nomprod", prod.nomProd)
        acti.putExtra("exis", prod.existen)
        acti.putExtra("precio", prod.prec)
        startActivity(acti)
    }

    override fun onResume() {
        super.onResume()
        retriveProductos()
    }

    private fun retriveProductos() {
        val ListaProd = llenaListaProductos()
        viewAdapter.setTask(ListaProd)
    }

    fun llenaListaProductos() : MutableList<Producto> {
        var listProd: MutableList<Producto> = ArrayList()
        val sentencia: String =
            "Select idProd,nomProd,existencia,precio from Producto Order by nomProd"
        val result: Cursor? = admin.consultar(sentencia)



        if (result != null) {
            while (result!!.moveToNext()) {
                val idp = result.getInt(0).toInt()
                val nomp = result.getString(1).toString()
                val exi = result.getInt(2). toInt()
                val pre = result.getDouble(3).toDouble()
                listProd.add(Producto(idp, nomp, exi, pre))
            }

        }

        return listProd

    }

    fun btnAgregar(view: View) {
        var acti = Intent(this,MainActivityProd::class.java)
        acti.putExtra("idprod", 0)
        acti.putExtra("nomprod", "")
        acti.putExtra("exis", 0)
        acti.putExtra("precio", 0.0)
        startActivity(acti)
    }
}