package com.example.controletarefas

import junit.framework.TestCase

class TesteNome : TestCase() {

    fun testNomeInvalido(){

        var resultado = Utils.eNome("1254gh")
        assertFalse(resultado)
    }
    fun testNomeTamanhoImvalido(){
        var resultado = Utils.verificaTamanho("ab")
        assertFalse(resultado)
    }

    fun testNomeTamanhoValido(){
        var resultado = Utils.verificaTamanho("abcd")
        assertTrue(resultado)
    }

    fun testNomeValido(){
        var resultado = Utils.eNome("Maria")
        assertTrue(resultado)
    }

}