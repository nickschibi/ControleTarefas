package com.example.controletarefas.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("unidade","ibge","gia")
class Endereco {
    var cep: String? = null
    var logradouro : String?=null
    var complemento: String? = null
    var bairro: String?= null
    var localidade: String? = null
    var uf: String?=null
    var numero: String? = null

    override fun toString(): String {
        return "Endereco(cep=$cep, logradouro=$logradouro, complemento=$complemento, bairro=$bairro, localidade=$localidade, uf=$uf, numero=$numero)"
    }


}