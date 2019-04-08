package com.example.controletarefas
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


class RetrofitConfig {

    private val retrofit: Retrofit? = null

    constructor(){
        Retrofit.Builder()
    }

    // a url sempre deve terminar com a barra se não ocorre Exception
    fun RetrofitConfig(){
        Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    fun getCEPService(): CepServico {
        return this.retrofit!!.create(CepServico::class.java)
    }
}