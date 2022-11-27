package com.kodego.inventory.app.mytodoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ToDo::class],
    version = 1
)
abstract class ToDoDatabase :RoomDatabase(){
    abstract fun getToDos() :ToDoDao

    companion object {
        @Volatile
        private var instance: ToDoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java,
            "todo"
        ).build()
    }
}