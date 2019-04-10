package com.example.controletarefas.contratos

import com.example.controletarefas.model.Desenvolvedor
import com.example.controletarefas.model.Endereco

interface ContratoDesenvolvedor {
    interface View{
        //preenche os campos da tela com o endereco recebido
        fun preencheCamposEndereco(_endereco: Endereco)
        //para exibir qualquer mensagem na tela
        fun exibeToast(_msg : String)
        //guarda o usuario que está logado
        fun updateUi(_userId : String)
    }
    interface Presenter{
        //recebe o cep para efetuar a busca
        fun buscaEnderecoPorCEP(_cep : String)
        //rercebe o endereco feito pela busca do Cep
        fun preencheEndereco(_endereco : Endereco)
        //instancia um novo desenvolvedor
        fun criarDesenvolvedor(_nome : String, _sobrenome : String, _email : String,
                               _senha : String, _cpf : String, _endereco : Endereco,
                               _numero : String, _complemento: String)
        //guarda o usuario que esta logado
        fun updateUi(_userId : String)
        //exibe mensagens
        fun exibeToast(_msg : String)
    }

    interface Interactor{
        //Utilizando Retrofit e Jackson para consumir o servico de CEP
        fun buscaEndereco(_cep : String)
        // Cria um novo desenvolvedor no Firebase e autentica o mesmo
        fun autenticaUsuario(_email : String, _senha : String, _desenvolvedor : Desenvolvedor)
    }
}