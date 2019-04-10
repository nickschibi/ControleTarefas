package com.example.controletarefas

import com.example.controletarefas.model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepServico {

    @GET ("{cep}/json/")
    fun buscarCEP(@Path("cep") cep: String): Call<Endereco>
}