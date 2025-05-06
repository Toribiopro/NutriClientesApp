package com.example.nutriclientesapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView


class EditarClienteActivity : AppCompatActivity() {
    lateinit var dbHelper: DatabaseHelper
    lateinit var listaEditar: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cliente)

        listaEditar = findViewById(R.id.listaEditar)
        dbHelper = DatabaseHelper(this)

        val clientes = dbHelper.obtenerTodosLosClientes()
        val nombresClientes = clientes.map { it.nombre }  // Mostrar solo nombres en el ListView

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresClientes)
        listaEditar.adapter = adapter

        listaEditar.setOnItemClickListener { _, _, position, _ ->
            val clienteSeleccionado = clientes[position]

            val intent = Intent(this, FormularioEditarClienteActivity::class.java).apply {
                putExtra("nombre", clienteSeleccionado.nombre)
                putExtra("edad", clienteSeleccionado.edad)
                putExtra("peso", clienteSeleccionado.peso)
                putExtra("correo", clienteSeleccionado.correo)
            }
            startActivity(intent)
        }
    }
}
