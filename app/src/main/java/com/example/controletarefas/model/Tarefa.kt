package com.example.controletarefas.model


class Tarefa {
     var idTarefa: String? = null
     var descricaoAtividade: String? = null
     var solicitanteAtividade: String? = null
     var dataRegistroAtividade: String?=  null
     var dataPrevistaEntrega: String? = null
     var status: String? = null


    constructor(
        descricaoAtividade: String?,
        solicitanteAtividade: String?,
        dataRegistroAtividade: String?,
        dataPrevistaEntrega: String?,
        status: String?
    ) {
        this.descricaoAtividade = descricaoAtividade
        this.solicitanteAtividade = solicitanteAtividade
        this.dataRegistroAtividade = dataRegistroAtividade
        this.dataPrevistaEntrega = dataPrevistaEntrega
        this.status = status
    }
}