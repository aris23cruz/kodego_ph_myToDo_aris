package com.kodego.inventory.app.mytodoapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDao {
    @Insert
    fun addToDo(toDo : ToDo)

    @Query("SELECT * FROM ToDo" )
    fun getAllToDo():MutableList<ToDo>

    @Query("DELETE FROM todo WHERE id = :id")
    fun deleteToDo(id:Int)

    @Query("DELETE FROM ToDo;")
    fun resetTodo()
}