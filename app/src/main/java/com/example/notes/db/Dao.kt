package com.example.notes.db


import androidx.lifecycle.LiveData

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@androidx.room.Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)
    @Update
    fun update(note: Note)
    @Delete
    fun delete(note: Note)

    @Query("select * from noteTable")
    fun getAllNotes():LiveData<List<Note>>
}