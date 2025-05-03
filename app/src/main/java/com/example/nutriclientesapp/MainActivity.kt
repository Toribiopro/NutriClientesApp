package com.example.nutriclientesapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nutriclientesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.obtenerBaseDatos(this)

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.editNombre.text.toString()
            val celular = binding.editCelular.text.toString()
            val correo = binding.editCorreo.text.toString()
            val direccion = binding.editDireccion.text.toString()

            if (nombre.isNotBlank() && celular.isNotBlank() && correo.isNotBlank() && direccion.isNotBlank()) {
                val cliente = Cliente(
                    nombre = nombre,
                    celular = celular,
                    correo = correo,
                    direccion = direccion
                )

                lifecycleScope.launch {
                    db.clienteDao().insertar(cliente)
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Cliente guardado", Toast.LENGTH_SHORT).show()
                        limpiarCampos()
                    }
                }
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun limpiarCampos() {
        binding.editNombre.text.clear()
        binding.editCelular.text.clear()
        binding.editCorreo.text.clear()
        binding.editDireccion.text.clear()
    }
}
