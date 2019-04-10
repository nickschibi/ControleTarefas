package com.example.controletarefas.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.EditText
import android.widget.Toast
import com.example.controletarefas.R
import com.example.controletarefas.contratos.ContratoTarefa.*
import com.example.controletarefas.presenter.TarefaPresenter

class TarefaActivity : AppCompatActivity() , View {

    private var presenter : Presenter? = null
    private var descricaoEditText: EditText? = null
    private var solicitanteEditText: EditText? = null
    private var dataSolicitacaoEditText : EditText? = null
    private var dataPrevistaEditText: EditText? = null
    private var statusEditText : EditText? = null
    private var saveButton : FloatingActionButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa)
        presenter = TarefaPresenter(this)

        descricaoEditText = findViewById(R.id.descricaoEditText)
        solicitanteEditText= findViewById(R.id.solicitanteEditText)
        dataSolicitacaoEditText= findViewById(R.id.dataSolEditText)
        dataPrevistaEditText= findViewById(R.id.dataPrevEditText)
        statusEditText = findViewById(R.id.statusEditText)
        saveButton = findViewById(R.id.saveBtn)


        presenter!!.inicializaFirebase(this) // descobrir se é isso mesmo que tenho que fazer

        saveButton!!.setOnClickListener {
            presenter!!.criaTarefa(descricaoEditText!!.text.toString(),solicitanteEditText!!.text.toString(),dataSolicitacaoEditText!!.text.toString(),
                        dataPrevistaEditText!!.text.toString(),statusEditText!!.text.toString())

            val intent = Intent(this@TarefaActivity, ListaTarefasActivity::class.java)
            startActivity(intent); }

    }

    override fun exibeToast(_msg: String) {
        Toast.makeText(this, _msg, Toast.LENGTH_LONG).show()
    }
}
