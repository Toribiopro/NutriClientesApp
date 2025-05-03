package com.example.nutriclientesapp

// AppDatabase.kt
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cliente::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clienteDao(): ClienteDao

    companion object {
        @Volatile private var instancia: AppDatabase? = null

        fun obtenerBaseDatos(context: Context): AppDatabase {
            return instancia ?: synchronized(this) {
                val nuevaInstancia = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "clientes_db"
                ).build()
                instancia = nuevaInstancia
                nuevaInstancia
            }
        }
    }
}