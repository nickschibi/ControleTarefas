package com.example.controletarefas.presenter
import android.R
import android.content.Context
import android.widget.ArrayAdapter
import com.example.controletarefas.Status
import com.example.controletarefas.contratos.ContratoTarefa.*
import com.example.controletarefas.model.TarefaInteractor

class TarefaPresenter(_view : View): Presenter{

    private var _view : View = _view
    private var interactor = TarefaInteractor(this)


    override fun exibeToast(_msg: String) {
        _view.exibeToast(_msg)
    }

    override  fun inicializaFirebase(_this : Context){
        interactor.inicializaFirebase(_this)

    }

    override fun criaTarefa(descricao: String, solicitante: String?, dtSolicita: String?, dtPrevista: String, status: String){
        // tratar as datas aqui e o status

        interactor.criaTarefa(descricao, solicitante, dtSolicita,dtPrevista,status)
    }
    override fun atualizaTarefa(_idTareafa : String , descricao: String,solicitante: String?,
                                dtSolicita: String?, dtPrevista: String, status: String) {

        interactor.alteratarefa(_idTareafa,descricao,solicitante,dtSolicita,dtPrevista,status)

    }

    override fun preencheCampoStatus(_this: Context): ArrayAdapter<*>{
        var arrayAdapter = ArrayAdapter( _this, R.layout.simple_spinner_item, Status.values())
        return arrayAdapter
    }

}