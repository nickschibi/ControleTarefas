package com.example.controletarefas.model
import android.content.Context
import com.example.controletarefas.contratos.ContratoListaTarefa.*
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class ListaTarefasInteractor(_presenter : Presenter) : Interactor {
    private var presenter = _presenter
    var firebaseDatabase : FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    val TAG = "TarefaInteractor"
    var list = mutableListOf<Tarefa>()


    override fun inicializaFirebase(_this : Context){
        FirebaseApp.initializeApp(_this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.getReference()

        var query : Query?= null
        query = databaseReference!!.child("tarefas")
        query.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    var tarefa: Tarefa = snapshot.getValue(Tarefa::class.java)!!
                    list.add(tarefa!!)
                }
                presenter.criaListaTarefas(list)
            } override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun trazListaTarefas(){
        list.clear()

    }
    override fun atualizaTarefa(tarefa: Tarefa) {
        databaseReference!!.child("tarefas").child(tarefa.idTarefa.toString()).setValue(tarefa)
        list.clear()
    }


    override fun deletaTarefa(idTarefa: String) {
        databaseReference!!.child("tarefas").child(idTarefa).removeValue()
        list.clear()
    }
}