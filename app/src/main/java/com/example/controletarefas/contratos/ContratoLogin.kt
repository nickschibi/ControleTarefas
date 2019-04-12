package com.example.controletarefas.contratos

interface ContratoLogin {
    interface View {
        fun updateUi(_uid: String)
        fun exibeToast(msg: String)
    }
    interface Presenter{
        fun loginUser(email : String , senha : String)
        fun updateUi(_uid :String)
        fun exibeToast(msg: String)
    }
    interface Interactor{
        fun loginUser(email : String , senha : String)


    }
}