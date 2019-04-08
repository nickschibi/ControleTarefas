package com.example.controletarefas.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("unidade","ibge","gia")
class Endereco {
    private var cep: String? = null
    private var logradouro : String?=null
    private var complemento: String? = null
    private var bairro: String?= null
    private var localidade: String? = null
    private var uf: String?=null
    private var numero: String? = null
}