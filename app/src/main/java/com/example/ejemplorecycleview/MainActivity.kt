package com.example.ejemplorecycleview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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
            data.add(Coche("mikasa"))
            adaptadorRecycler.notifyItemInserted(data.size)
        }
        iniciarRecycler()
    }

    fun iniciarRecycler() {
        vistaRecycler = mibinding.recycler
        vistaRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adaptadorRecycler = AdaptadorRecycler(data) { posicion ->
            data.get(posicion).seleccionado = !data.get(posicion).seleccionado
            adaptadorRecycler.notifyItemChanged(posicion)
        }
        vistaRecycler.adapter = adaptadorRecycler
    }


}