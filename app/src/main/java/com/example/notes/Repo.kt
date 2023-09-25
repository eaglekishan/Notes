package com.example.notes

import com.example.notes.db.Dao
import com.example.notes.db.Note

class Repo(private val dao:Dao) {

    fun insert(note: Note){
        dao.insert(note)
    }
    fun update(note: Note){
        dao.update(note)

    }

    fun delete(note: Note){
        dao.delete(note)
    }

    fun getAllNotes()=dao.getAllNotes()

}