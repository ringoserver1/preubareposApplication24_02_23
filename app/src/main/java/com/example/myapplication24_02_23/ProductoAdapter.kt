package com.example.myapplication24_02_23

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//-----------------------------------------------------------
//    Adaptador del RecyclerView
//    Es la clase que hace de puente entre la vista
//    (el recyclerview) y los datos
//
//-----------------------------------------------------------
class ProductoAdapter(private var mLista:List<Producto>,
                      private val mContext: Context, private val clickListener: (Producto) -> Unit)
    : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    /**
     * El layout manager invoca este método para renderizar cada elemento
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        return ProductoViewHolder(layoutInflater.inflate(R.layout.celda_prototipo, parent, false))
    }

    /**
     * Este método asigna valores para cada elemento de la lista
     *
     * @param holder   Vincular los datos del cursor al ViewHolder
     * @param position La posición de los datos en la lista
     */
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.bind(mLista[position], mContext, clickListener)
    }

    /**
     * El método getItemCount() Cantidad de elementos del RecyclerView
     */
    override fun getItemCount(): Int = mLista.size

    /**
     * Cuando los datos cambian, este metodo actualiza la lista de Productos
     * y notifica al adaptador a usar estos nuevos valores
     */
    fun setTask(lista: List<Producto>){
        mLista = lista
        notifyDataSetChanged()
    }

    fun getTasks(): List<Producto> = mLista

    /*
     * Clase interna para asignar los valores a los textView definidos en la celda_prototipo
     */
    class ProductoViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val subTitulo: TextView = itemView.findViewById(R.id.txtSubtitulo)

        fun bind (prod:Producto, context: Context, clickListener: (Producto) -> Unit){
            //Asigna los valores a los elementos del la celda_prototipo_estudiante
            titulo.text = prod.nomProd.toString()
            subTitulo.text = prod.prec.toString()

            itemView.setOnClickListener{ clickListener(prod)}
        }
    }
}
