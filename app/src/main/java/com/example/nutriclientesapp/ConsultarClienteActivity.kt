package com.example.nutriclientesapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ConsultarClienteActivity : AppCompatActivity() {
    lateinit var dbHelper: DatabaseHelper  // tu clase de SQLite
    lateinit var listaClientes: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_cliente)

        listaClientes = findViewById(R.id.listaClientes)
        dbHelper = DatabaseHelper(this)

        val clientes = dbHelper.obtenerTodosLosClientes()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, clientes)
        listaClientes.adapter = adapter
    }
}
