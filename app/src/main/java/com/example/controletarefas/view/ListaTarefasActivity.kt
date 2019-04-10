package com.example.controletarefas.view
import android.support.design.widget.FloatingActionButton
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.controletarefas.R
import com.example.controletarefas.model.Tarefa
import com.google.firebase.auth.FirebaseAuth

class ListaTarefasActivity : AppCompatActivity() {
    private var novaTarefaButton : FloatingActionButton? = null
    private var auth : FirebaseAuth?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tarefas)
        novaTarefaButton = findViewById(R.id.novaTarefaBtn)
        novaTarefaButton!!.setOnClickListener {
            val intent = Intent(this, TarefaActivity:: class.java)
            startActivity(intent)
        }

        val tarefasListView = findViewById<ListView>(R.id.tarefasListView)
        var list = mutableListOf<Tarefa>()

        carregaLista()

//        // teste adicionando manualmente na lista
//
//        list.add(Tarefa("Minha Primeira Tarefa","Monique",
//            "07/04/2018","20/04/2018", "pausado" ))
//        list.add(Tarefa("Minha Segunda Tarefa","Ana",
//            "04/04/2018","25/04/2018", "andamento" ))
//
//        //adapter
//        tarefasListView.adapter = MyListAdapter(this, R.layout.item_lista_tarefas,list)
//
//        //
//        //clicks listView
//        tarefasListView.setOnItemClickListener{ parent : AdapterView<*>, view: View,  position : Int, id : Long ->
//            if(position == 0){
//                Toast.makeText(this, "Item 1", Toast.LENGTH_LONG).show()
//            }
//            if(position == 1){
//                Toast.makeText(this, "Item 2", Toast.LENGTH_LONG).show()
//            }
//
//        }


    }

    private fun carregaLista(){


    }
}
