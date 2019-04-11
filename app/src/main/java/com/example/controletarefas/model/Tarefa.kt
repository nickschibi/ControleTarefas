package com.example.controletarefas.model

import java.io.Serializable


/*Serializable permite que eu passe a instancia de um objeto por intents
porem se esse objeto tiver algum atributo não serializavel, ele nao vai,
como por exemplo um Context.
se no caso eu tivesse um Toast eu teria que fazer com que ele fosse ignorado utilizando "transient(termo em java, kotlin não sei)"

 */

class Tarefa : Serializable {
     var idTarefa: String? = null
     var descricaoAtividade: String? = null
     var solicitanteAtividade: String? = null
     var dataRegistroAtividade: String?=  null
     var dataPrevistaEntrega: String? = null
     var status: String? = null


}