package com.example.controletarefas

import android.content.Intent
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.controletarefas.model.Tarefa
import com.example.controletarefas.view.ListaTarefasActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class TarefaActivity : AppCompatActivity() {
    private val TAG = "TarefaActivity"
    private var descricaoEditText: EditText? = null
    private var solicitanteEditText: EditText? = null
    private var dataSolicitacaoEditText : EditText? = null
    private var dataPrevistaEditText: EditText? = null
    private var statusEditText : EditText? = null
    private var saveButton : FloatingActionButton? = null

    var firebaseDatabase : FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa)

        descricaoEditText = findViewById(R.id.descricaoEditText)
        solicitanteEditText= findViewById(R.id.solicitanteEditText)
        dataSolicitacaoEditText= findViewById(R.id.dataSolEditText)
        dataPrevistaEditText= findViewById(R.id.dataPrevEditText)
        statusEditText = findViewById(R.id.statusEditText)
        saveButton = findViewById(R.id.saveBtn)
        inicializaFirebase() // descobrir se é isso mesmo que tenho que fazer

        saveButton!!.setOnClickListener {
            criaTarefa(descricaoEditText!!.text.toString(),solicitanteEditText!!.text.toString(),dataSolicitacaoEditText!!.text.toString(),
                        dataPrevistaEditText!!.text.toString(),statusEditText!!.text.toString())

            val intent = Intent(this@TarefaActivity, ListaTarefasActivity::class.java)
            startActivity(intent); }

    }
    private fun inicializaFirebase(){
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.getReference()
    }


    private fun criaTarefa(descricao: String ,solicitante: String? ,dtSolicita : String?, dtPrevista : String, status : String){
        var tarefa = Tarefa( descricao, solicitante,dtSolicita, dtPrevista, status)
        val key =  databaseReference!!.child("tarefas").push().key
        databaseReference!!.child("tarefas").child(key!!).setValue(tarefa).addOnCompleteListener(this){

            if(it.isSuccessful){
                Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT)
            }else{
                Log.e(TAG,"Erro na criação da tarefa",it.exception)
            }
        }



    }

}
