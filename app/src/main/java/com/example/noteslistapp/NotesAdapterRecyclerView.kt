package com.example.noteslistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class NotesAdapterRecyclerView(var listNotes: MutableList<ModelNote> = arrayListOf(), val onTapItem: OnTapItem, val onDelete: OnDelete) : RecyclerView.Adapter<NotesAdapterRecyclerView.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.text_notes)
        val background = itemView.findViewById<CardView>(R.id.background_notes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rec, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = listNotes[position].title
        holder.itemView.setOnClickListener {
            onTapItem.onClick(position)
        }
        holder.itemView.setOnLongClickListener {
            listNotes.removeAt(position)
            notifyItemRemoved(position)
            onDelete.onDelete(position)
            return@setOnLongClickListener true
        }
        holder.background.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, listNotes[position].colorId))
    }

    override fun getItemCount(): Int = listNotes.size

    fun setNotes(data: MutableList<ModelNote>){
        listNotes = data
        notifyDataSetChanged()
    }
}
