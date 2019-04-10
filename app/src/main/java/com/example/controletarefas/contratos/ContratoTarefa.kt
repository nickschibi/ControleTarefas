package com.example.controletarefas.contratos

import android.content.Context

interface ContratoTarefa {

    interface View{
        fun exibeToast(_msg : String)
    }

    interface  Presenter {
        fun exibeToast(_msg : String)
        fun inicializaFirebase(_this : Context)
        fun criaTarefa(descricao: String ,solicitante: String? ,
                       dtSolicita : String?, dtPrevista : String, status : String)
    }

    interface Interactor{
        fun inicializaFirebase(_this : Context)
        fun criaTarefa(descricao: String ,solicitante: String? ,
                       dtSolicita : String?, dtPrevista : String, status : String)

    }
}