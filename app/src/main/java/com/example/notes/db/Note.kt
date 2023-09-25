package com.example.notes.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteTable")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val noteName:String,
    val content:String

)