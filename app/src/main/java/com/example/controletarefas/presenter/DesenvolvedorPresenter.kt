package com.example.controletarefas.presenter

import com.example.controletarefas.Utils
import com.example.controletarefas.contratos.ContratoDesenvolvedor.*
import com.example.controletarefas.model.Desenvolvedor
import com.example.controletarefas.model.DesenvolvedorInteractor
import com.example.controletarefas.model.Endereco

class DesenvolvedorPresenter(_view: View) :Presenter  {

    private var view: View = _view
    private var interactor = DesenvolvedorInteractor(this)



    override fun buscaEnderecoPorCEP(_cep: String){
        var cep = _cep.replace("-","")
        interactor.buscaEndereco(cep)
    }

    override fun preencheEndereco(_endereco: Endereco) {

        view.preencheCamposEndereco(_endereco)
    }


    override fun criarDesenvolvedor(_nome : String, _sobrenome : String, _email : String,
                                    _senha : String, _cpf : String, _endereco : Endereco,
                                    _numero : String, _complemento: String) {

        var msg: String? = Utils.verificaCamposVaziosDev(
                                _nome!!, _sobrenome!!, _email!!,
                                _senha!!, _cpf!!, _endereco.cep!!, _endereco.logradouro!!,
                                _numero!!, _endereco.bairro!!, _endereco.localidade!!, _endereco.uf!!)



        if (msg != null){
            view.exibeToast("Campo(s) vazios: $msg")
        }else if(!Utils.emailEValido(_email)){
            view.exibeToast("E-mail inválido")
        }else if(!Utils.eSenha(_senha)) {
            view.exibeToast("Senha inválida")
        }else if(!Utils.eCPF(_cpf)){
            view.exibeToast("CPF inválido")
        } else if(_sobrenome.length< 3){
            view.exibeToast("Sobrenome tamanho invalido")
        }else if(_nome.length < 3){
            view.exibeToast("Nome com tamanho Invalido")
        }
        else {
            _endereco.complemento = _complemento
            _endereco.numero = _numero

            var desenvolvedor = Desenvolvedor()
            desenvolvedor.nome =_nome
            desenvolvedor.sobrenome =_sobrenome
            desenvolvedor.email = _email
            desenvolvedor.senha =_senha
            desenvolvedor.cpf =_cpf
            desenvolvedor.endereco =_endereco



            interactor.autenticaUsuario(_email, _senha, desenvolvedor)

        }
    }

    override fun updateUi(_userId : String){
        view.updateUi(_userId)
    }

    override fun exibeToast(_msg : String){
        view.exibeToast(_msg)
    }

    override fun atualizaDesenvolvedor(ui : String ,_nome : String, _sobrenome : String, _email : String,
                              _senha : String, _cpf : String, _endereco : Endereco,
                              _numero : String, _complemento: String) {

        var msg: String? = Utils.verificaCamposVaziosDev(
            _nome!!, _sobrenome!!, _email!!,
            _senha!!, _cpf!!, _endereco.cep!!, _endereco.logradouro!!,
            _numero!!, _endereco.bairro!!, _endereco.localidade!!, _endereco.uf!!
        )


        if (msg != null) {
            view.exibeToast("Campo(s) vazios: $msg")
        } else if (!Utils.emailEValido(_email)) {
            view.exibeToast("E-mail inválido")
        } else if (!Utils.eSenha(_senha)) {
            view.exibeToast("Senha inválida")
        } else if (!Utils.eCPF(_cpf)) {
            view.exibeToast("CPF inválido")
        } else if (_nome.length == _sobrenome.length) {
            view.exibeToast("Nome e sobrenome com mesmo tamanho")
        } else if (_nome.length < 3) {
            view.exibeToast("Nome com tamanho Invalido")
        } else {
            _endereco.complemento = _complemento
            _endereco.numero = _numero
            var desenvolvedor = Desenvolvedor()
                desenvolvedor.nome =_nome
                desenvolvedor.sobrenome =_sobrenome
                desenvolvedor.email = _email
                desenvolvedor.senha =_senha
                desenvolvedor.cpf =_cpf
                desenvolvedor.endereco =_endereco

            interactor.atualizaDesenvolvedor(desenvolvedor)

        }

    }

    override fun trazDesenvolvedor(uid: String) {
        interactor.trazDesenvolvedor(uid)
    }

    override fun preencheCamposDev(desenvolvedor: Desenvolvedor) {

        view.preencheCamposDev(desenvolvedor )
    }

}





