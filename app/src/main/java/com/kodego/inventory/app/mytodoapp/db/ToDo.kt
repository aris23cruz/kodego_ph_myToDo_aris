package com.kodego.inventory.app.mytodoapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    var task : String,
    var isChecked: Boolean = false
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
