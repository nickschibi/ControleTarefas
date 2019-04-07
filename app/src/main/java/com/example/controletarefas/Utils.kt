package com.example.controletarefas

import android.text.TextUtils
import android.util.Patterns
import java.util.*
import java.util.regex.Pattern


// classe de verificação relacionada a validação de campos entre outros

class Utils {

    companion object { // conpanion object é o static do Kotlin

        fun campoEstaVazio(valor: String): Boolean {

            //Verifica que se o campo está vazio, ou vazio após ter retirado todos os espaços

            return TextUtils.isEmpty(valor) || valor.trim { it <= ' ' }.isEmpty()
        }

        fun emailEValido(email: String): Boolean {
            return !campoEstaVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun eTelefone(phone: String): Boolean {
            return !campoEstaVazio(phone) && Patterns.PHONE.matcher(phone).matches()
        }

        fun eNome(name: String): Boolean {
            val ps = Pattern.compile("^[a-zA-Z ]+$")
            val ms = ps.matcher(name)
            return ms.matches()
        }

        fun eSenha(senha: String): Boolean {

            val ps = Pattern.compile("^(?=.{8})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!?._]).{8}$")
            val ms = ps.matcher(senha)
            return !campoEstaVazio(senha)&& ms.matches()
        }

        fun eCPF(CPF: String): Boolean {
            var CPF = CPF
            CPF = CPF.replace("[^0-9]".toRegex(), "")
            // considera-se erro CPF's formados por uma sequencia de numeros iguais
            if (CPF == "00000000000" ||
                CPF == "11111111111" ||
                CPF == "22222222222" || CPF == "33333333333" ||
                CPF == "44444444444" || CPF == "55555555555" ||
                CPF == "66666666666" || CPF == "77777777777" ||
                CPF == "88888888888" || CPF == "99999999999" ||
                CPF.length != 11
            )
                return false

            val dig10: Char
            val dig11: Char
            var sm: Int
            var i: Int
            var r: Int
            var num: Int
            var peso: Int

            // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
            try {
                // Calculo do 1o. Digito Verificador
                sm = 0
                peso = 10
                i = 0
                while (i < 9) {
                    // converte o i-esimo caractere do CPF em um numero:
                    // por exemplo, transforma o caractere '0' no inteiro 0
                    // (48 eh a posicao de '0' na tabela ASCII)
                    num = CPF[i].toInt() - 48
                    sm = sm + num * peso
                    peso = peso - 1
                    i++
                }

                r = 11 - sm % 11
                if (r == 10 || r == 11)
                    dig10 = '0'
                else
                    dig10 = (r + 48).toChar() // converte no respectivo caractere numerico

                // Calculo do 2o. Digito Verificador
                sm = 0
                peso = 11
                i = 0
                while (i < 10) {
                    num = CPF[i].toInt() - 48
                    sm = sm + num * peso
                    peso = peso - 1
                    i++
                }

                r = 11 - sm % 11
                if (r == 10 || r == 11)
                    dig11 = '0'
                else
                    dig11 = (r + 48).toChar()


                return true

            } catch (erro: InputMismatchException) {
                return false
            }

        }
    }

}