package com.example.controletarefas.presenter
import android.R
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.widget.ArrayAdapter
import com.example.controletarefas.Status
import com.example.controletarefas.contratos.ContratoTarefa.*
import com.example.controletarefas.model.TarefaInteractor
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TarefaPresenter(_view : View): Presenter{

    private var _view : View = _view
    private var interactor = TarefaInteractor(this)



    override fun exibeToast(_msg: String) {
        _view.exibeToast(_msg)
    }

    override  fun inicializaFirebase(_this : Context){
        interactor.inicializaFirebase(_this)

    }

    override fun criaTarefa(descricao: String, solicitante: String?, dtSolicita: String?, dtPrevista: String, status: String){
        // tratar as datas aqui e o status

        interactor.criaTarefa(descricao, solicitante, dtSolicita,dtPrevista,status)
    }
    override fun atualizaTarefa(_idTareafa : String , descricao: String,solicitante: String?,
                                dtSolicita: String?, dtPrevista: String, status: String) {

        interactor.alteratarefa(_idTareafa,descricao,solicitante,dtSolicita,dtPrevista,status)

    }

    override fun preencheCampoStatus(_this: Context): ArrayAdapter<*>{
        var arrayAdapter = ArrayAdapter( _this, R.layout.simple_spinner_item, Status.values())
        return arrayAdapter
    }


     @RequiresApi(Build.VERSION_CODES.O)
     override fun registrarData(): String {
        val agora = LocalDateTime.now()
        val formato = DateTimeFormatter.ofPattern("dd/MM/YYYY")
        val formatada = agora.format(formato)
        return formatada
    }




}