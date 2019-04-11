package com.example.controletarefas.view
import android.support.design.widget.FloatingActionButton
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.example.controletarefas.MyListAdapter
import com.example.controletarefas.R
import com.example.controletarefas.Status
import com.example.controletarefas.contratos.ContratoListaTarefa.*
import com.example.controletarefas.model.Tarefa
import com.example.controletarefas.presenter.ListaTarefasPresenter

class ListaTarefasActivity : AppCompatActivity(), View {
    private var presenter: Presenter? = null
    private var novaTarefaButton: FloatingActionButton? = null
    private var tarefasListView: ListView? = null
    private var tarefaSelecionada: Tarefa? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tarefas)
        presenter = ListaTarefasPresenter(this)
        presenter!!.inicializaFirebase(this)
        presenter!!.carregaLista()
        novaTarefaButton = findViewById(R.id.novaTarefaBtn)

        novaTarefaButton!!.setOnClickListener {
            val intent = Intent(this, TarefaActivity::class.java)
            intent.putExtra("flag", "nova")
            startActivity(intent)
        }

        tarefasListView = findViewById<ListView>(R.id.tarefasListView)


        tarefasListView!!.setOnItemClickListener { parent, view, position, id ->
            tarefaSelecionada = parent!!.getItemAtPosition(position) as Tarefa?
            val intent: Intent = Intent(this@ListaTarefasActivity, TarefaActivity::class.java)
            intent.putExtra("EXTRA_TAREFA", tarefaSelecionada)
            startActivity(intent)
        }

        tarefasListView!!.setOnItemLongClickListener { parent, view, position, id ->
            tarefaSelecionada = parent!!.getItemAtPosition(position) as Tarefa?
            criaAlerta()
            true
            }



    }

    override fun inflaLista(list: List<Tarefa>){
        //adapter
        tarefasListView!!.adapter = MyListAdapter(this, R.layout.item_lista_tarefas,list)
    }

    override fun exibeToast(_msg: String) {

    }

    fun criaAlerta(){
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Deletar"){dialog, which ->
            presenter!!.deletaTarefa(tarefaSelecionada!!.idTarefa!!)
        }
        if(!tarefaSelecionada!!.status.equals("Pausada")) {
            builder.setNegativeButton("Pausar") { dialog, which ->
                tarefaSelecionada!!.status = Status.Pausada.toString()
                presenter!!.atualizaTarefa(tarefaSelecionada!!)
            } }
        builder.setNeutralButton("Entregar"){dialog, which ->
            tarefaSelecionada!!.status = Status.Entregue.toString()
            presenter!!.atualizaTarefa(tarefaSelecionada!!)}
        val dialog: AlertDialog = builder.create()
        dialog.show()


    }

}
