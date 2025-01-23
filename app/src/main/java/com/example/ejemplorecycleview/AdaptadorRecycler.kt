package com.example.ejemplorecycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorRecycler(private val data: List<Coche>, val onClick_item: (Int) -> Unit) :
    RecyclerView.Adapter<AdaptadorRecycler.MiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(
            R.layout.elemento_recycler, parent,
            false
        )
        val objeto_viewHolder = MiViewHolder(vista)

        return objeto_viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        // vinculo cada una de los elementos visuales (vistas) del holder con los datos
        //holder.itemView.findViewById<TextView>(R.id.texto1).text = data.get(position)
        holder.render(data[position])
        holder.itemView.setOnClickListener {
            onClick_item(position)
        }
    }

    class MiViewHolder(val vista: View) : RecyclerView.ViewHolder(vista) {
        // la propiedad item view representa la vista individual y tiene la misma referencia que vista
        fun render(dato: Coche) {
            //itemView.findViewById<>()
            vista.findViewById<TextView>(R.id.texto).text = dato.marca
            if (dato.seleccionado) {
                vista.setBackgroundColor(vista.context.getColor(R.color.grey))
            } else {
                vista.setBackgroundColor(vista.context.getColor(R.color.black))
            }
        }

    }
}