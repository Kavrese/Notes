package com.example.noteslistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

const val CREATE_NOTE = 100
const val EDIT_NOTE = 101

class MainActivity : AppCompatActivity() {
    lateinit var noteViewModel: NoteViewModel
    var listNotes: MutableList<ModelNote> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        rec_main.apply{
            adapter = NotesAdapterRecyclerView(listNotes, object: OnTapItem{
                override fun onClick(position: Int) {
                    ToEditScreen.model = listNotes[position]
                    val intent = Intent(this@MainActivity, ViewNoteActivity::class.java)
                    intent.putExtra("code", EDIT_NOTE)
                    startActivityForResult(intent, EDIT_NOTE)
                }
            }, object: OnDelete{
                override fun onDelete(position: Int) {
                    noteViewModel.delete(listNotes[position])
                }
            })
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        add_note.setOnClickListener {
            val intent = Intent(this@MainActivity, EditNoteActivity::class.java)
            intent.putExtra("code", CREATE_NOTE)
            startActivityForResult(intent, CREATE_NOTE)
        }

        initNotesToRecyclerView()
    }

    private fun initNotesToRecyclerView(){
        noteViewModel.allNotes.observe(this, { notes ->
            notes.let {
                listNotes = notes.toMutableList()
                (rec_main.adapter!! as NotesAdapterRecyclerView).setNotes(notes.toMutableList())
                rec_main.adapter!!.notifyDataSetChanged()
                if (notes.isEmpty()){
                    empty.visibility = View.VISIBLE
                }else{
                    empty.visibility = View.INVISIBLE
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            if (requestCode == CREATE_NOTE){
                noteViewModel.insert(ToEditScreen.model)
            }else{
                noteViewModel.update(ToEditScreen.model)
            }
        }
    }
}