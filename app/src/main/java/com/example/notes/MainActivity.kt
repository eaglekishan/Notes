package com.example.notes

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.db.Note
import com.example.notes.db.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),noteAdapter.Click {
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var noteViewModelFactory: NoteViewModelFactory
    private lateinit var repo: Repo
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var fab:FloatingActionButton
    private lateinit var dialog: Dialog
    private lateinit var save:Button
    private lateinit var title_ip:TextView
    private lateinit var content_ip:TextView

    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAd: noteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()



//        notesViewModel.insert(
//            Note(
//
//                noteName = "some note1",
//                content = "this is the content of the note"
//
//            )
//        )
//        notesViewModel.insert(
//            Note(
//                noteName = "some note4",
//                content = " content of the note. this is the content of the note. this is the content of the note. this is the content of the note. "
//
//            )
//        )

        notesViewModel.getAllNotes().observe(this){
            Log.i("database",it.toString())
            recyclerView.layoutManager=LinearLayoutManager(this)
            noteAd= noteAdapter(it,this)
            recyclerView.adapter=noteAd


        }
        fab.setOnClickListener {
            openDialog(flag = true)
        }
    }
    private fun openDialog(flag:Boolean){
        dialog=Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_layout)

        save=dialog.findViewById(R.id.save_btn)
        content_ip=dialog.findViewById(R.id.content_ip)
        title_ip=dialog.findViewById(R.id.title_ip)
        if(flag==false){
            save.text="Update"
        }

        save.setOnClickListener {
            val n=Note(
                noteName=title_ip.text.toString(),
                content = content_ip.text.toString()
            )


            if(flag==true){

                notesViewModel.insert(n)
            } else{
                Log.i("update","enter")
                notesViewModel.update(n)
            }
            dialog.dismiss()
        }
        dialog.show()


    }


    private fun init(){
        noteDatabase=NoteDatabase(this)
        fab=findViewById(R.id.fab)
        repo= Repo(noteDatabase.getNoteDao())
        noteViewModelFactory= NoteViewModelFactory(repo)
        notesViewModel=ViewModelProvider(this,noteViewModelFactory).get(NotesViewModel::class.java)
        recyclerView=findViewById(R.id.rv)
    }

    override fun updateNote(note: Note) {

        openDialog(flag = false)
    }

    override fun deleteNote(note: Note) {
        notesViewModel.delete(note)
    }

}