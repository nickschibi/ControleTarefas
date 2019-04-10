package com.example.controletarefas
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


class RetrofitConfig {

    fun init() {
    }

    // a url sempre deve terminar com a barra se não ocorre Exception
    var retrofit =
        Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()


    fun getCEPService(): CepServico = retrofit.create(CepServico::class.java)

}