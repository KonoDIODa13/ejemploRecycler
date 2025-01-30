package com.example.ejemplorecycleview

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplorecycleview.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

data class Coche(var marca: String, var seleccionado: Boolean = false)

class MainActivity : AppCompatActivity() {
    lateinit var mibinding: ActivityMainBinding

    lateinit var vistaRecycler: RecyclerView

    //crear el adaptador del recicler
    lateinit var adaptadorRecycler: AdaptadorRecycler
    lateinit var addButton: FloatingActionButton

    //crear lista du datos
    var data = mutableListOf(
        Coche("Mercedes"),
        Coche("Bmv"),
        Coche("Mg"),
        Coche("Toyota"),
        Coche("Nissan"),
        Coche("Tesla"),
        Coche("Hyundai"),
        Coche("Porche"),
        Coche("Lamborghini"),
        Coche("Mercedes"),
        Coche("Bmv"),
        Coche("Mg"),
        Coche("Toyota"),
        Coche("Nissan"),
        Coche("Tesla"),
        Coche("Hyundai"),
        Coche("Porche"),
        Coche("Lamborghini"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        iniciarComponentes()

    }

    fun iniciarComponentes() {
        mibinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mibinding.root)

        addButton = mibinding.fabAdd
        addButton.setOnClickListener {
            annadirCoche()
        }

        iniciarRecycler()
    }

    fun annadirCoche() {
        var textField = EditText(this)
        var builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setView(textField)
        textField.hint = "Escriba la marca del coche."
        builder.setPositiveButton("Añadir") { interf, id ->
            if (textField.text.isNotEmpty()) {
                data.add(Coche(textField.text.toString()))
                adaptadorRecycler.notifyItemInserted(data.size)
            }
            interf.dismiss()
        }
        builder.setNegativeButton("Cancelar") { interf, id ->
            interf.dismiss()
        }
        var alert = builder.create()
        alert.show()
    }

    fun iniciarRecycler() {
        vistaRecycler = mibinding.recycler
        vistaRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // el adaptador del recycler requiere de la lista de datos y de una funcion lambda.
        // la lambda consiste en cuando se pulse en uno de los elementos, se cambie el campo seleccionado.
        adaptadorRecycler = AdaptadorRecycler(data) { posicion ->
            data.get(posicion).seleccionado = !data.get(posicion).seleccionado
            adaptadorRecycler.notifyItemChanged(posicion)
        }
        vistaRecycler.adapter = adaptadorRecycler
    }


}