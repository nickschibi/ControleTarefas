package com.example.controletarefas.presenter

import com.example.controletarefas.Utils
import com.example.controletarefas.contratos.ContratoLogin.*
import com.example.controletarefas.model.LoginInteractor

class LoginPresenter(_view : View) : Presenter {

    private var view: View = _view
    private var interactor = LoginInteractor(this)
    var dadosCorretos : Boolean? = false

    override fun loginUser(email : String , senha : String) {
        if (Utils.emailEValido(email!!) && !Utils.campoEstaVazio(senha!!))
        {
            dadosCorretos = true
        }else{
           view.exibeToast("Dados inválidos")
        }

        if(dadosCorretos == true) {
            interactor.loginUser(email, senha)
        }
    }

    override fun updateUi(_uid: String) {
       view.updateUi(_uid)
    }

    override fun exibeToast(msg: String) {
        view.exibeToast(msg)
    }

}