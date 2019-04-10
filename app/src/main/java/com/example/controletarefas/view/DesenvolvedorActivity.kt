package com.example.controletarefas.view

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.controletarefas.R
import com.example.controletarefas.model.Endereco
import com.example.controletarefas.presenter.DesenvolvedorPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.controletarefas.RetrofitConfig
import com.example.controletarefas.Utils
import com.example.controletarefas.contratos.ContratoDesenvolvedor.*
import com.example.controletarefas.model.Desenvolvedor
import kotlinx.android.synthetic.main.activity_desenvolvedor.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class DesenvolvedorActivity : AppCompatActivity(), View {
    

    private var nomeEditText: EditText? = null
    private var sobrenomeEditText: EditText? = null
    private var emailEditText: EditText? = null
    private var senhaEditText: EditText? = null
    private var salvarDevButton: Button? = null
    private var cpfEditText: EditText? = null
    private var cepEditText: EditText? = null
    private var ruaEditText: EditText? = null
    private var numeroEditText: EditText? = null
    private var complementoEditText: EditText? = null
    private var bairroEditText: EditText? = null
    private var cidadeEditText: EditText? = null
    private var estadoEditText: EditText? = null
    private var cep: String? = null
    private var presenter : Presenter? = null
    private var endereco : Endereco? = null
    private var fotoPerfilButton : Button? = null
    private var fotoPerfil : ImageView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desenvolvedor)
        presenter = DesenvolvedorPresenter(this)

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
        fotoPerfilButton = findViewById(R.id.fotoPerfilButton)
        fotoPerfil= findViewById(R.id.imagemPerfil)
        fotoPerfilButton!!.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("incluir nova foto usando:")
            builder.setPositiveButton("Camera"){dialog, which ->

                tirarFoto()
                //root_layout.setBackgroundColor(Color.RED)
            }

            builder.setNegativeButton("Galeria"){dialog,which ->

            }
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }



        salvarDevButton!!.setOnClickListener {
            presenter!!.criarDesenvolvedor(
                nomeEditText!!.text.toString(), sobrenomeEditText!!.text.toString(), emailEditText!!.text.toString(),
                senhaEditText!!.text.toString(), cpfEditText!!.text.toString(), endereco!!,
                numeroEditText!!.text.toString(), complementoEditText!!.text.toString()) }

        cepEditText!!.addTextChangedListener(object : TextWatcher {
            var isUpdating = false

            override fun afterTextChanged(s: Editable) {
                if(cepEditText!!.text.toString().length == 9){
                    presenter!!.buscaEnderecoPorCEP(cepEditText!!.text.toString())
                }
            }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {}
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (isUpdating) {
                    isUpdating = false
                    return
                }
                val hasMask = s.toString().indexOf('.') > -1 || s.toString().indexOf('-') > -1
                var str = s.toString().filterNot { it == '.' || it == '-' }
                if (count > before) {
                    if (str.length > 5) {
                        str = "${str.substring(0, 2)}${str.substring(2, 5)}-${str.substring(5)}"
                    }
                    isUpdating = true
                    cepEditText!!.setText(str)
                    cepEditText!!.setSelection(cepEditText!!.text?.length ?: 0)

                } else {
                    isUpdating = true
                    cepEditText!!.setText(str)
                    cepEditText!!.setSelection(
                        Math.max(
                            0,
                            Math.min(if (hasMask) start - before else start, str.length)
                        )
                    )

                }

            }

        })

    }


    //passa para main quem é o desenvolvedor conectado
    override fun updateUi(_userId : String) {
        val intent = Intent(this@DesenvolvedorActivity, ListaTarefasActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }


   private fun tirarFoto(){

       val intent = Intent(Intent.ACTION_PICK)
       intent.type = "image/*"
       startActivityForResult(intent,0)

   }

    var urifotoSelecionada : Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==0 && resultCode == Activity.RESULT_OK && data != null){
            //ao selecionar a imagem
            urifotoSelecionada = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,urifotoSelecionada)
            val bitmapDrawable = BitmapDrawable(bitmap)
            fotoPerfil!!.setBackgroundDrawable(bitmapDrawable)

        }
    }

    override fun preencheCamposEndereco(_endereco: Endereco) {
        estadoEditText!!.setText(_endereco!!.uf)
        cidadeEditText!!.setText(_endereco!!.localidade)
        bairroEditText!!.setText(_endereco!!.bairro)
        ruaEditText!!.setText(_endereco!!.logradouro)
        endereco = _endereco
    }

    override fun exibeToast(_msg: String) {
        Toast.makeText(this, _msg, Toast.LENGTH_LONG).show()
    }
}
