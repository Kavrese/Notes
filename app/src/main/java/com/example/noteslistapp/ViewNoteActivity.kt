package com.example.noteslistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_edit_note.*
import kotlinx.android.synthetic.main.activity_edit_note.back_edit
import kotlinx.android.synthetic.main.activity_view_note.*
import java.util.*

class ViewNoteActivity: AppCompatActivity() {
    var note: ModelNote = ToEditScreen.model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_note)
        back_view.requestFocus()

        back_view.setOnClickListener {
            finish()
        }

        edit.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            intent.putExtra("code", EDIT_NOTE)
            startActivityForResult(intent, EDIT_NOTE)
        }

        initData()
    }

    private fun initData(){
        title_view.text = note.title
        message_view.text = note.message
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            initData()
            setResult(RESULT_OK)
        }
    }
}