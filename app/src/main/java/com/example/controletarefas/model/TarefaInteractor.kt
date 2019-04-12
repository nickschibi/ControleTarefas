package com.example.controletarefas.model

import android.content.Context
import android.util.Log
import com.example.controletarefas.contratos.ContratoTarefa.*
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class TarefaInteractor (_presenter : Presenter) : Interactor {

    private var presenter = _presenter

    var firebaseDatabase : FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    private val TAG = "TarefaInteractor"

    override fun inicializaFirebase(_this : Context){
        FirebaseApp.initializeApp(_this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.getReference()
    }
     override fun criaTarefa(descricao: String ,solicitante: String? ,dtSolicita : String?, dtPrevista : String, status : String) {
         var tarefa = Tarefa()
         //tarefa.idTarefa = UUID.randomUUID().toString()
         tarefa.descricaoAtividade = descricao
         tarefa.solicitanteAtividade = solicitante
         tarefa.dataRegistroAtividade = dtSolicita
         tarefa.dataPrevistaEntrega = dtPrevista
         tarefa.status = status
         val key = databaseReference!!.child("tarefas").push().key
         tarefa.idTarefa = key
         databaseReference!!.child("tarefas").child(key!!).setValue(tarefa).addOnCompleteListener() {
             if (it.isSuccessful) {
                 Log.d(TAG,"Tarefa slava")
                 presenter.exibeToast("Tarefa salva com sucesso")
             } else {
                 Log.e(TAG,"Erro ao criar tarefa")
                 presenter.exibeToast("Erro ao salvar tarefa")

             }
         }
     }

    override fun alteratarefa(_idTarefa : String , descricao: String,
                              solicitante: String?, dtSolicita: String?,
                              dtPrevista: String, status: String) {

        var tarefa = Tarefa()
        tarefa.idTarefa = _idTarefa
        tarefa.descricaoAtividade = descricao
        tarefa.solicitanteAtividade = solicitante
        tarefa.dataRegistroAtividade = dtSolicita
        tarefa.dataPrevistaEntrega = dtPrevista
        tarefa.status = status
        databaseReference!!.child("tarefas").child(tarefa.idTarefa.toString()).setValue(tarefa)

        presenter.exibeToast("Tarefa alterada!")
    }


}