package com.example.nutriclientesapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "NutricionDB.db"
        const val DATABASE_VERSION = 1

        const val TABLE_CLIENTES = "clientes"
        const val COL_NOMBRE = "nombre"
        const val COL_EDAD   = "edad"
        const val COL_PESO = "peso"
        const val COL_CORREO = "correo"
    }

    data class Cliente(val nombre: String, val edad: Int, val peso: Double, val correo: String)

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE clientes (
                nombre TEXT PRIMARY KEY,
                edad INTEGER,
                peso REAL,
                correo TEXT
            );
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CLIENTES")
        onCreate(db)
    }

    // Función para insertar un cliente
    fun insertarCliente(nombre: String, edad: Int, peso: Double, correo: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_NOMBRE, nombre)
            put(COL_EDAD, edad)
            put(COL_PESO, peso)
            put(COL_CORREO, correo)
        }
        val result = db.insert(TABLE_CLIENTES, null, values)
        db.close()
        return result != -1L
    }


    fun obtenerTodosLosClientes(): List<Cliente> {
        val lista = mutableListOf<Cliente>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM clientes", null)

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val edad = cursor.getInt(cursor.getColumnIndexOrThrow("edad"))
                val peso = cursor.getDouble(cursor.getColumnIndexOrThrow("peso"))
                val correo = cursor.getString(cursor.getColumnIndexOrThrow("correo"))
                lista.add(Cliente(nombre, edad, peso, correo))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    fun eliminarCliente(nombre: String): Boolean {
        val db = this.writableDatabase
        val resultado = db.delete("clientes", "nombre=?", arrayOf(nombre))
        db.close()
        return resultado > 0
    }

    fun obtenerClientePorNombre(nombre: String): Cliente? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM clientes WHERE nombre = ?", arrayOf(nombre))
        var cliente: Cliente? = null
        if (cursor.moveToFirst()) {
            cliente = Cliente(
                cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                cursor.getInt(cursor.getColumnIndexOrThrow("edad")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("peso")),
                cursor.getString(cursor.getColumnIndexOrThrow("correo"))
            )
        }
        cursor.close()
        return cliente
    }

    fun actualizarCliente(nombreOriginal: String, nuevoNombre: String, edad: Int, peso: Double, correo: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nuevoNombre)
            put("edad", edad)
            put("peso", peso)
            put("correo", correo)
        }
        val resultado = db.update("clientes", values, "nombre = ?", arrayOf(nombreOriginal))
        return resultado > 0
    }

}


