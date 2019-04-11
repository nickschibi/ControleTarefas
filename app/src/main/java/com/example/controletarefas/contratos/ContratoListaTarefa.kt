package com.example.controletarefas.contratos

import android.content.Context
import com.example.controletarefas.model.Tarefa

interface ContratoListaTarefa {
    interface View{
        fun inflaLista(list: List<Tarefa>)
        fun exibeToast(_msg : String)
    }

    interface  Presenter {
        fun exibeToast(_msg : String)
        fun inicializaFirebase(_this : Context)
        //instancia o adapter para inflar a lista
        fun criaListaTarefas(list : List<Tarefa>)
        fun carregaLista()
        fun atualizaTarefa(tarefa : Tarefa)
        fun deletaTarefa(idTarefa : String)
    }
    interface Interactor{
        fun inicializaFirebase(_this : Context)
        //traz todas tarefas que estão no database
        fun trazListaTarefas()
        fun atualizaTarefa(tarefa : Tarefa)
        fun deletaTarefa(idTarefa : String)
    }



}