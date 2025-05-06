package com.example.nutriclientesapp

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class EliminarClienteActivity : AppCompatActivity() {
    lateinit var dbHelper: DatabaseHelper
    lateinit var listaEliminar: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_cliente)

        dbHelper = DatabaseHelper(this)
        listaEliminar = findViewById(R.id.listaEliminar)

        val clientes = dbHelper.obtenerTodosLosClientes()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, clientes)
        listaEliminar.adapter = adapter

        listaEliminar.setOnItemClickListener { _, _, position, _ ->
            val clienteSeleccionado = clientes[position]
            val nombre = clienteSeleccionado.toString().substringAfter("Nombre: ").substringBefore("\n")

            val eliminado = dbHelper.eliminarCliente(nombre)
            if (eliminado) {
                Toast.makeText(this, "Cliente eliminado", Toast.LENGTH_SHORT).show()
                recreate()
            } else {
                Toast.makeText(this, "No se pudo eliminar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
