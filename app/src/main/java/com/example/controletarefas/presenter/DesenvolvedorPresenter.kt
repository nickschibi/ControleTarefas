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
        }
        else {

            var desenvolvedor = Desenvolvedor("",_nome,_sobrenome,
                _email, _senha, _cpf, _endereco)


            interactor.autenticaUsuario(_email, _senha, desenvolvedor)


        }
    }

    override fun updateUi(_userId : String){
        view.updateUi(_userId)
    }

    override fun exibeToast(_msg : String){
        view.exibeToast(_msg)
    }
}





