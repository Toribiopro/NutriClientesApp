package com.example.nutriclientesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormularioEditarClienteActivity : AppCompatActivity() {
    lateinit var dbHelper: DatabaseHelper
    lateinit var editNombre: EditText
    lateinit var editEdad: EditText
    lateinit var editPeso: EditText
    lateinit var editCorreo: EditText
    lateinit var btnGuardarCambios1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_editar_cliente)

        dbHelper = DatabaseHelper(this)

        editNombre = findViewById(R.id.editNombre)
        editEdad = findViewById(R.id.editEdad)
        editPeso = findViewById(R.id.editPeso)
        editCorreo = findViewById(R.id.editCorreo)
        btnGuardarCambios1 = findViewById(R.id.btnGuardarCambios1)

        val nombreCliente = intent.getStringExtra("nombre_cliente")

        if (nombreCliente != null) {
            val cliente = dbHelper.obtenerClientePorNombre(nombreCliente)
            if (cliente != null) {
                editNombre.setText(cliente.nombre)
                editEdad.setText(cliente.edad.toString())
                editPeso.setText(cliente.peso.toString())
                editCorreo.setText(cliente.correo)
            }
        }

        btnGuardarCambios1.setOnClickListener {
            val nuevoNombre = editNombre.text.toString()
            val edad = editEdad.text.toString().toIntOrNull()
            val peso = editPeso.text.toString().toDoubleOrNull()
            val correo = editCorreo.text.toString()

            if (edad != null && peso != null && nombreCliente != null) {
                dbHelper.actualizarCliente(nombreCliente, nuevoNombre, edad, peso, correo)
                Toast.makeText(this, "Cliente actualizado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Campos inválidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
