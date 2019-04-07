package com.example.controletarefas.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.controletarefas.R
import com.example.controletarefas.Utils
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private var emailLoginEditText : EditText? = null
    private var senhaLoginEditText : EditText? = null
    private var logarButton : Button? = null
    private var novoDevButton : Button? = null
    private var email : String? = null
    private var senha: String? = null

    //referencia ao banco de dados
    private var auth : FirebaseAuth?= null
   // private var firebaseApp: FirebaseApp?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Mudar a cor da barra, versões mais antigas
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            window.setStatusBarColorto(R.color.colorPrimaryDark)
        }
         //firebaseApp = FirebaseApp.initializeApp(this)

        emailLoginEditText = findViewById(R.id.emailLoginEditText) as EditText
        senhaLoginEditText = findViewById(R.id.senhaLoginEditText) as EditText
        logarButton = findViewById(R.id.logarButton) as Button
        novoDevButton = findViewById(R.id.novoDevButton) as Button
        auth = FirebaseAuth.getInstance()
        novoDevButton!!.setOnClickListener { startActivity((
                    Intent(this, DesenvolvedorActivity::class.java))) }

        logarButton!!.setOnClickListener { loginUser() }



    }//

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun Window.setStatusBarColorto(colorInt: Int){
        this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.statusBarColor = ContextCompat.getColor(baseContext, R.color.colorPrimaryDark)
    }

    private fun loginUser(){
        var dadosCorretos : Boolean? = false
        email= emailLoginEditText?.text.toString()
        senha=senhaLoginEditText?.text.toString()

        if (Utils.emailEValido(email!!) && !Utils.campoEstaVazio(senha!!))
        {
            dadosCorretos = true
        }else{
            Toast.makeText(this,"Dados inválidos",Toast.LENGTH_LONG).show()
        }


        if(dadosCorretos == true) {
            auth!!.signInWithEmailAndPassword(
                emailLoginEditText!!.text.toString(),
                senhaLoginEditText!!.text.toString()
            ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "logado com sucesso")
                    updateUi()
                } else {
                    Log.e(TAG, "erro ao logar", task.exception)
                    //colocar isso no else superior depois
                    Toast.makeText(this, "Erro na autenticação de usuário", Toast.LENGTH_LONG).show()
                }
            }
        }
        updateUi()
    }
    private fun updateUi(){
        val intent = Intent(this@LoginActivity, ListaTarefasActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}
