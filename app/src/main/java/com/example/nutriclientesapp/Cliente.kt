package com.example.nutriclientesapp

// Cliente.kt
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clientes")
data class Cliente(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val celular: String,
    val correo: String,
    val direccion: String
)
