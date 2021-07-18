package com.example.noteslistapp

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel (application: Application): AndroidViewModel(application) {
    private val noteDao: NotesDao
    internal val allNotes: LiveData<List<ModelNote>>

    init {
        val noteDB = NotesDatabase.getDatabase(application)
        noteDao = noteDB!!.noteDao()
        allNotes = noteDao.allNotes
    }

    fun insert(modelNote: ModelNote){
        InsertAsync(noteDao).execute(modelNote)
    }

    fun update(modelNote: ModelNote){
        UpdateAsync(noteDao).execute(modelNote)
    }

    fun delete(modelNote: ModelNote){
        DeleteAsync(noteDao).execute(modelNote)
    }

    companion object {
        private class InsertAsync(private val notesDao: NotesDao): AsyncTask<ModelNote, Void, Void>(){
            override fun doInBackground(vararg params: ModelNote): Void? {
                notesDao.insert(params[0])
                return null
            }
        }

        private class UpdateAsync(private val notesDao: NotesDao): AsyncTask<ModelNote, Void, Void>(){
            override fun doInBackground(vararg params: ModelNote): Void? {
                notesDao.update(params[0])
                return null
            }
        }

        private class DeleteAsync(private val notesDao: NotesDao): AsyncTask<ModelNote, Void, Void>(){
            override fun doInBackground(vararg params: ModelNote): Void? {
                notesDao.delete(params[0])
                return null
            }
        }
    }

}