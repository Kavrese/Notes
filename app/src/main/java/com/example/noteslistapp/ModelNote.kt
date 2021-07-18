package com.example.noteslistapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data  class ModelNote(
    @PrimaryKey
    val id: String,
    var title: String,
    var message: String,
    var colorId: Int,
)
