package com.example.controletarefas.model

import android.util.Log
import com.example.controletarefas.contratos.ContratoLogin.*
import com.google.firebase.auth.FirebaseAuth

class LoginInteractor(_presenter : Presenter) : Interactor {
    private var auth : FirebaseAuth?= null
    private val TAG = "LoginInteractor"
    private val presenter  : Presenter = _presenter

    override fun loginUser(email : String, senha: String) {
        auth = FirebaseAuth.getInstance()
        auth!!.signInWithEmailAndPassword(
            email,
           senha
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "logado com sucesso")

                presenter.updateUi(auth!!.uid!!)
            } else {
                Log.e(TAG, "erro ao logar", task.exception)

             presenter.exibeToast( "Erro na autenticação de usuário")
            }
        }
    }
}