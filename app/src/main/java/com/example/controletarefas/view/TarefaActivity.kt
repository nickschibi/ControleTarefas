package com.example.controletarefas.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import com.example.controletarefas.MaskWatcher
import com.example.controletarefas.R
import com.example.controletarefas.contratos.ContratoTarefa.*
import com.example.controletarefas.model.Tarefa
import com.example.controletarefas.presenter.TarefaPresenter
import com.example.controletarefas.Status

class TarefaActivity : AppCompatActivity() , View {

    private var presenter : Presenter? = null
    private var descricaoEditText: EditText? = null
    private var solicitanteEditText: EditText? = null
    private var dataSolicitacaoEditText : EditText? = null
    private var dataPrevistaEditText: EditText? = null
    private var saveButton : FloatingActionButton? = null
    private var backButton: ImageButton? = null
    private var flag : String? = null
    private var tarefa : Tarefa? = null
    var _status : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa)
        presenter = TarefaPresenter(this)
        flag = getIntent().getStringExtra("flag")
        val spinner = findViewById<Spinner>(R.id.spinner)
        descricaoEditText = findViewById(R.id.descricaoEditText)
        solicitanteEditText= findViewById(R.id.solicitanteEditText)
        dataSolicitacaoEditText= findViewById(R.id.dataSolEditText)
        dataPrevistaEditText= findViewById(R.id.dataPrevEditText)
        backButton = findViewById(R.id.btnBackToList)
        saveButton = findViewById(R.id.saveBtn)


        backButton!!.setOnClickListener {
            val intent = Intent(this@TarefaActivity, ListaTarefasActivity::class.java)
            startActivity(intent)
        }

        spinner.adapter = presenter!!.preencheCampoStatus(this)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                _status = spinner.getItemAtPosition(position).toString()
                Toast.makeText(this@TarefaActivity, _status, Toast.LENGTH_SHORT).show()
            }
        }

        dataPrevistaEditText!!.addTextChangedListener(MaskWatcher("##:## ##/##/##"))
        if(flag==null) {
            tarefa = getIntent().getExtras().getSerializable("EXTRA_TAREFA") as Tarefa?
        }else{
            dataSolicitacaoEditText!!.setText(presenter!!.registrarData())
        }

        if (tarefa!= null){
            descricaoEditText!!.setText(tarefa!!.descricaoAtividade)
            solicitanteEditText!!.setText(tarefa!!.solicitanteAtividade)
            dataPrevistaEditText!!.setText(tarefa!!.dataPrevistaEntrega)
            dataSolicitacaoEditText!!.setText(tarefa!!.dataRegistroAtividade)
            //statusEditText!!.setText(tarefa!!.status)
            spinner.setSelection(Status.valueOf(tarefa!!.status!!).n)
        }

        presenter!!.inicializaFirebase(this) // descobrir se é isso mesmo que tenho que fazer

        saveButton!!.setOnClickListener {

            //colocar aqui verificação de campos vazios

            //insert
            if (tarefa == null) {
                presenter!!.criaTarefa(
                    descricaoEditText!!.text.toString(),
                    solicitanteEditText!!.text.toString(),
                    dataSolicitacaoEditText!!.text.toString(),
                    dataPrevistaEditText!!.text.toString(),
                    //statusEditText!!.text.toString()
                    _status!!
                )
            } else {//update
                      presenter!!.atualizaTarefa(
                          tarefa!!.idTarefa!!,descricaoEditText!!.text.toString(),
                          solicitanteEditText!!.text.toString(),
                          dataSolicitacaoEditText!!.text.toString(),
                          dataPrevistaEditText!!.text.toString(),
                          //statusEditText!!.text.toString()
                          _status!!
                      )
            }
            val intent = Intent(this@TarefaActivity, ListaTarefasActivity::class.java)
            intent.putExtra("flag","tarefa")
            startActivity(intent);
        }
    }


    override fun exibeToast(_msg: String) {
        Toast.makeText(this, _msg, Toast.LENGTH_LONG).show()
    }




}
