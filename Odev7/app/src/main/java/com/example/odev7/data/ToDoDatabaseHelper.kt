package com.example.odev7.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.odev7.data.entity.ToDo


class ToDoDatabaseHelper(private val context: Context) :
    SQLiteOpenHelper(context, "toDos.sqlite", null, 1) {

        init {
            copyDatabaseIfNeeded()
        }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE todos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS todos")
        onCreate(db)
    }

    private fun copyDatabaseIfNeeded() {
        val dbPath = context.getDatabasePath("toDos.sqlite")

        if (!dbPath.exists()) {
            try {
                context.assets.open("toDos.sqlite").use { inputStream ->
                    dbPath.parentFile?.mkdirs()
                    dbPath.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
                println("Veritabanı başarıyla kopyalandı.")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            println("Veritabanı zaten mevcut, kopyalanmadı.")
        }
    }


    fun insertToDo(toDo: ToDo) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", toDo.name)
        }
        db.insert("todos", null, values)
    }

    fun getAllToDos(): List<ToDo> {
        val list = mutableListOf<ToDo>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM todos", null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            list.add(ToDo(id, name))
        }
        cursor.close()
        return list
    }

    fun updateToDo(toDo: ToDo) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", toDo.name)
        }
        db.update("todos", values, "id = ?", arrayOf(toDo.id.toString()))
    }

    fun deleteToDo(toDo: ToDo) {
        val db = writableDatabase
        db.delete("todos", "id = ?", arrayOf(toDo.id.toString()))
    }
}
