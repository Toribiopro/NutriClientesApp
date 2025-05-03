package com.example.nutriclientesapp

// ClienteDao.kt
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ClienteDao {
    @Insert
    suspend fun insertar(cliente: Cliente)

    @Update
    suspend fun actualizar(cliente: Cliente)

    @Delete
    suspend fun eliminar(cliente: Cliente)

    @Query("SELECT * FROM clientes ORDER BY nombre ASC")
    fun obtenerTodos(): LiveData<List<Cliente>>
}
