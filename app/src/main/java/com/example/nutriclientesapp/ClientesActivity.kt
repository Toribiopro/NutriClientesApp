package com.example.nutriclientesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class ClientesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.clientes_activity)

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)

        btnAgregar.setOnClickListener {
            val intent = Intent(this, AgregarClienteActivity::class.java) // Aquí irá tu formulario
            startActivity(intent)
        }

        val btnEditar = findViewById<Button>(R.id.btnEditar)
        btnEditar.setOnClickListener {
            val intent = Intent(this, EditarClienteActivity::class.java)
            startActivity(intent)
        }

        val btnConsultar = findViewById<Button>(R.id.btnConsultar)
        btnConsultar.setOnClickListener {
            val intent = Intent(this, ConsultarClienteActivity::class.java)
            startActivity(intent)
        }

        val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        btnEliminar.setOnClickListener {
            val intent = Intent(this, EliminarClienteActivity::class.java)
            startActivity(intent)
        }

    }
}
