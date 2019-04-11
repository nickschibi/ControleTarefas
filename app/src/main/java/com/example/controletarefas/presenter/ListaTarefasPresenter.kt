package com.example.controletarefas.presenter

import android.content.Context
import com.example.controletarefas.contratos.ContratoListaTarefa.*
import com.example.controletarefas.model.ListaTarefasInteractor
import com.example.controletarefas.model.Tarefa

class ListaTarefasPresenter (_view : View): Presenter {


    private var _view : View = _view
    private var interactor = ListaTarefasInteractor(this)

    override fun inicializaFirebase(_this: Context) {
        interactor.inicializaFirebase(_this)
    }
    override fun carregaLista(){
        interactor.trazListaTarefas()
    }

    override fun criaListaTarefas(list: List<Tarefa>) {
     _view.inflaLista(list)
    }
    override fun exibeToast(_msg: String) {

    }
    override fun atualizaTarefa(tarefa: Tarefa) {
        interactor.atualizaTarefa(tarefa)
    }
    override fun deletaTarefa(idTarefa: String) {
        interactor.deletaTarefa(idTarefa)
    }
}