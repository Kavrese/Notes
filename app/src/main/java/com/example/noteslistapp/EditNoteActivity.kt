package com.example.noteslistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_edit_note.*
import kotlinx.android.synthetic.main.activity_view_note.*
import java.util.*

class EditNoteActivity: AppCompatActivity() {
    var NOW_REQUEST: Int? = null
    lateinit var note: ModelNote
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        back_edit.requestFocus()

        back_edit.setOnClickListener {
            finish()
        }

        intent.extras.let {
            NOW_REQUEST = it!!.getInt("code")
            note = if (NOW_REQUEST == CREATE_NOTE)
                ModelNote(UUID.randomUUID().toString(), "", "", R.color.colorRed)
            else
                ToEditScreen.model
        }

        title_edit.setText(note.title)
        message_edit.setText(note.message)

        save.setOnClickListener {
            Snackbar.make(save, "Save", Snackbar.LENGTH_LONG).show()
            note.title = title_edit.text.toString()
            note.message = message_edit.text.toString()
            ToEditScreen.model = note
            setResult(RESULT_OK)
        }
    }
}