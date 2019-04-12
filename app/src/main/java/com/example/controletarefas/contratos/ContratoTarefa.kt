package com.example.controletarefas.contratos

import android.content.Context
import android.widget.ArrayAdapter
import com.example.controletarefas.model.Tarefa

interface ContratoTarefa {

    interface View{
        fun exibeToast(_msg : String)
    }

    interface  Presenter {
        fun exibeToast(_msg : String)
        fun inicializaFirebase(_this : Context)
        fun criaTarefa(descricao: String ,solicitante: String? ,
                       dtSolicita : String?, dtPrevista : String, status : String)
        fun atualizaTarefa(_idTareafa : String , descricao: String,solicitante: String?,
                           dtSolicita: String?, dtPrevista: String, status: String)
        fun preencheCampoStatus(_this: Context): ArrayAdapter<*>
        fun registrarData():String

    }

    interface Interactor{
        fun inicializaFirebase(_this : Context)
        fun criaTarefa(descricao: String ,solicitante: String? ,
                       dtSolicita : String?, dtPrevista : String, status : String)
        fun alteratarefa(_idTareafa : String , descricao: String,solicitante: String?,
                         dtSolicita: String?, dtPrevista: String, status: String)

    }
}