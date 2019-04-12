package com.example.controletarefas.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.controletarefas.ControleTarefasApplication
import com.example.controletarefas.R
import com.example.controletarefas.model.Endereco
import com.example.controletarefas.presenter.DesenvolvedorPresenter
import com.example.controletarefas.contratos.ContratoDesenvolvedor.*
import com.example.controletarefas.MaskWatcher
import com.example.controletarefas.model.Desenvolvedor


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
    private var presenter: Presenter? = null
    private var endereco: Endereco? = null
    private var uid : String? = null
    private var updateFlag : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desenvolvedor)
        presenter = DesenvolvedorPresenter(this)
        if(ControleTarefasApplication.uid != null)  {
            uid = ControleTarefasApplication.uid
            presenter!!.trazDesenvolvedor(uid!!)
        }

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
        salvarDevButton!!.setOnClickListener {

            if(updateFlag== true ){

                presenter!!.atualizaDesenvolvedor( uid!!,nomeEditText!!.text.toString(),
                    sobrenomeEditText!!.text.toString(),
                    emailEditText!!.text.toString(),
                    senhaEditText!!.text.toString(),
                    cpfEditText!!.text.toString(),
                    endereco!!,
                    numeroEditText!!.text.toString(),
                    complementoEditText!!.text.toString())


            }else {
                presenter!!.criarDesenvolvedor(
                    nomeEditText!!.text.toString(),
                    sobrenomeEditText!!.text.toString(),
                    emailEditText!!.text.toString(),
                    senhaEditText!!.text.toString(),
                    cpfEditText!!.text.toString(),
                    endereco!!,
                    numeroEditText!!.text.toString(),
                    complementoEditText!!.text.toString()
                )
            }
        }
        cpfEditText!!.addTextChangedListener(MaskWatcher("###.###.###-##"))
        cepEditText!!.addTextChangedListener(object : TextWatcher {
            var isUpdating = false
            override fun afterTextChanged(s: Editable) {
                if (cepEditText!!.text.toString().length == 9) {
                    presenter!!.buscaEnderecoPorCEP(cepEditText!!.text.toString()) } }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int) {}
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int) {
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


    override fun updateUi(_userId: String) {
        val intent = Intent(this@DesenvolvedorActivity, ListaTarefasActivity::class.java)
        //intent.putExtra("uid",_userId)
        ControleTarefasApplication.uid = _userId
        startActivity(intent)
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


    override fun preencheCamposDev(desenvolvedor: Desenvolvedor) {
        nomeEditText!!.setText(desenvolvedor.nome)
        sobrenomeEditText!!.setText(desenvolvedor.sobrenome)
        emailEditText!!.setText(desenvolvedor.email)
        senhaEditText!!.setText(desenvolvedor.senha)
        cpfEditText!!.setText(desenvolvedor.cpf)
        cepEditText!!.setText(desenvolvedor.endereco!!.cep)
        estadoEditText!!.setText(desenvolvedor.endereco!!.uf)
        cidadeEditText!!.setText(desenvolvedor.endereco!!.localidade)
        bairroEditText!!.setText(desenvolvedor.endereco!!.bairro)
        ruaEditText!!.setText(desenvolvedor.endereco!!.logradouro)
        complementoEditText!!.setText(desenvolvedor.endereco!!.complemento)
        numeroEditText!!.setText(desenvolvedor.endereco!!.numero)
        updateFlag = true
    }




}
