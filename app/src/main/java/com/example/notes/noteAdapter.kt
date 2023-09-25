package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.db.Note

class noteAdapter(
    private val m:List<Note>,
    private val click:Click

):RecyclerView.Adapter<noteAdapter.viewHolder>() {
    class viewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView){
        val title:TextView=ItemView.findViewById(R.id.title_note)
        val del:ImageView=ItemView.findViewById(R.id.delete)
        val noteContent:TextView=ItemView.findViewById(R.id.noteContent)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.note_layout,parent,false)
        return viewHolder(v)
    }

    override fun getItemCount(): Int {
        return m.size
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item=m[position]
        holder.title.text=item.noteName
        holder.noteContent.text=item.content
        holder.itemView.setOnClickListener {
            click.updateNote(item)

        }


        holder.del.setOnClickListener {
            click.deleteNote(item)
        }

    }

    interface Click{
        fun updateNote(note: Note)
        fun deleteNote(note:Note)
    }



}