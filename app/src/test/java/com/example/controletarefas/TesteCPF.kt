package com.example.controletarefas

import junit.framework.TestCase

class TesteCPF : TestCase() {

    fun testValidacaoCPFValido(){

      var resultado = Utils.eCPF("322.836.850-52")
        assertTrue(resultado)
    }

    fun testValidacaoCPFInvalido(){

        var resultado = Utils.eCPF("000.000.000-00")
        assertFalse(resultado)
    }

    fun testValidacaoCPFNulo(){

        var resultado = Utils.eCPF("")
        assertFalse(resultado)
    }
}