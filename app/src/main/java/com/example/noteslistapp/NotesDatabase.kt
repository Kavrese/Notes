package com.example.noteslistapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ModelNote::class], version = 1)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun noteDao(): NotesDao

    companion object{

        @Volatile
        private var noteRoomDatabaseInstance: NotesDatabase? = null

        internal fun getDatabase(context: Context): NotesDatabase? {
            if (noteRoomDatabaseInstance == null){
                synchronized(NotesDatabase::class.java){
                    noteRoomDatabaseInstance = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java, "note_db"
                    ).build()
                }
            }
            return noteRoomDatabaseInstance
        }
    }
}