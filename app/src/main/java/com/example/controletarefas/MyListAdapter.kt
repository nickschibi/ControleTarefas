package com.example.controletarefas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.controletarefas.model.Tarefa

class MyListAdapter( context:Context,  var resource: Int, var items: List<Tarefa>): ArrayAdapter<Tarefa>(context, resource ,items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //inflar aqui
        val layoutInflater : LayoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(resource,null)
        val descricao : TextView = view.findViewById(R.id.descricaoTextView)
        val dataSolitacao : TextView = view.findViewById(R.id.dataDaSolicitacaoTextView)
        val dataPrevista : TextView = view.findViewById(R.id.dataPrevistaTextView)
        val solicitante : TextView = view.findViewById(R.id.solicitanteTextView)
        val status : TextView = view.findViewById(R.id.statusTextView)

        var items : Tarefa = items[position]

        descricao.text = items.descricaoAtividade
        dataSolitacao.text = items.dataRegistroAtividade
        dataPrevista.text= items.dataPrevistaEntrega
        solicitante.text = items.solicitanteAtividade
        status.text = items.status


        return view
    }

}