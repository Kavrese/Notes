package com.example.noteslistapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Insert
    fun insert(note: ModelNote)

    @get:Query("SELECT * FROM notes")
    val allNotes: LiveData<List<ModelNote>>

    @Update
    fun update(modelNote: ModelNote)

    @Delete
    fun delete(modelNote: ModelNote)

    @Query("SELECT * FROM notes WHERE id =:id")
    fun getNote(id: String): ModelNote
}