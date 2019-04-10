package com.example.controletarefas.contratos

import com.example.controletarefas.model.Desenvolvedor
import com.example.controletarefas.model.Endereco

interface ContratoDesenvolvedor {
    interface View{
        fun preencheCamposEndereco(_endereco: Endereco)
        fun exibeToast(_msg : String)
        fun updateUi(_userId : String)
    }
    interface Presenter{
        fun buscaEnderecoPorCEP(_cep : String)
        fun preencheEndereco(_endereco : Endereco)
        fun criarDesenvolvedor(_nome : String, _sobrenome : String, _email : String,
                               _senha : String, _cpf : String, _endereco : Endereco,
                               _numero : String, _complemento: String)
        fun updateUi(_userId : String)
        fun exibeToast(_msg : String)
    }

    interface Interactor{
        fun buscaEndereco(_cep : String)
        fun autenticaUsuario(_email : String, _senha : String, _desenvolvedor : Desenvolvedor)
    }
}