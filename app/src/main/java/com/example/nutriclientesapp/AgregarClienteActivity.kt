package com.example.nutriclientesapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nutriclientesapp.databinding.ActivityAgregarClienteBinding

class AgregarClienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgregarClienteBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val edad = binding.etEdad.text.toString().toIntOrNull()
            val peso = binding.etPeso.text.toString().toFloatOrNull()
            val correo = binding.etCorreo.text.toString()

            if (nombre.isNotEmpty() && edad != null && peso != null && correo.isNotEmpty()) {
                val resultado = dbHelper.insertarCliente(nombre, edad.toInt(), peso.toDouble(), correo)
                if (resultado) {
                    Toast.makeText(this, "Cliente guardado", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
