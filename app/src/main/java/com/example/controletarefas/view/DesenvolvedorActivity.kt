package com.example.controletarefas.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.controletarefas.Contrato
import com.example.controletarefas.R
import com.example.controletarefas.presenter.DesenvolvedorPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DesenvolvedorActivity : AppCompatActivity() , Contrato.View {
    private var nomeEditText : EditText? = null
    private var sobrenomeEditText : EditText? = null
    private var emailEditText: EditText? = null
    private var senhaEditText : EditText? = null
    private var salvarDevButton : Button? = null
    private var cpfEditText: EditText? = null
    private var cepEditText: EditText? = null
    private var ruaEditText: EditText? = null
    private var numeroEditText: EditText? = null
    private var complementoEditText: EditText? = null
    private var bairroEditText : EditText? = null
    private var cidadeEditText : EditText? = null
    private var estadoEditText : EditText? = null



    private val TAG = "DesenvolvedorActivity"

    //Firebase
    private var databaseReference: DatabaseReference? = null // referencia do banco
    private var database: FirebaseDatabase? = null // instancia  do banco
    private var auth: FirebaseAuth?= null // meu autenticador


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desenvolvedor)
        val presenter =  DesenvolvedorPresenter()
        nomeEditText = findViewById(R.id.nomeEditText)
        sobrenomeEditText = findViewById(R.id.sobrenomeEditText)
        emailEditText = findViewById(R.id.emailEditText)
        senhaEditText = findViewById(R.id.senhaEditText)
        cpfEditText = findViewById(R.id.cpfEditText)
        cepEditText = findViewById(R.id.cepEditText)
        ruaEditText = findViewById(R.id.ruaEditText)
        numeroEditText = findViewById(R.id.numeroEditText)
        complementoEditText = findViewById(R.id.complementoEditText)
        bairroEditText = findViewById(R.id.bairroEditText)
        cidadeEditText = findViewById(R.id.cidadeEditText)
        estadoEditText = findViewById(R.id.estadoEditText)
        salvarDevButton = findViewById(R.id.salvarDevButton)

        database = FirebaseDatabase.getInstance()
        databaseReference = database!!.reference!!.child("Desenvolvedores")
        auth = FirebaseAuth.getInstance()


        salvarDevButton!!.setOnClickListener { criarDesenvolvedor() }

    }

    private fun criarDesenvolvedor(){
        auth!!.createUserWithEmailAndPassword(emailEditText!!.text.toString(), senhaEditText!!.text.toString()).addOnCompleteListener(this){
            task ->
                if (task.isSuccessful){
                    Log.d(TAG,"Criado Usuario com email com sucesso")
                    val userId = auth!!.currentUser!!.uid
                    val currentUserDb = databaseReference!!.child(userId)
                    currentUserDb.child("nome").setValue(nomeEditText!!.text.toString())
                    confirmacaoEmail()
                    updateUi()
                }
                else{
                    Log.w(TAG,"Criacao de Usuario com email falha", task.exception)
                }

        }

    }
    //passa para main quem é o desenvolvedor conectado
    private fun updateUi(){
        val intent = Intent(this@DesenvolvedorActivity, ListaTarefasActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    //envia email para usuario conprovando sua autenticidade
    private fun confirmacaoEmail(){
        val USER = auth!!.currentUser
        USER!!.sendEmailVerification().addOnCompleteListener(this){ task ->
            if(task.isSuccessful){
                Toast.makeText(this, "Um email de confirmacao foi enviado para" + USER, Toast.LENGTH_LONG).show()
            }
        }
    }

}
