package com.example.controletarefas.view
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.controletarefas.ControleTarefasApplication
import com.example.controletarefas.R
import com.example.controletarefas.contratos.ContratoLogin.*
import com.example.controletarefas.presenter.LoginPresenter

class LoginActivity : AppCompatActivity(), View {

    private var emailLoginEditText : EditText? = null
    private var senhaLoginEditText : EditText? = null
    private var logarButton : Button? = null
    private var novoDevButton : Button? = null
    private var email : String? = null
    private var senha: String? = null
    private var presenter: Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        //Mudar a cor da barra, versões mais antigas
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColorto(R.color.colorPrimaryDark)
        }

        emailLoginEditText = findViewById(R.id.emailLoginEditText) as EditText
        senhaLoginEditText = findViewById(R.id.senhaLoginEditText) as EditText
        logarButton = findViewById(R.id.logarButton) as Button
        novoDevButton = findViewById(R.id.novoDevButton) as Button

        novoDevButton!!.setOnClickListener {
                    val intent = Intent(this, DesenvolvedorActivity::class.java)
                     intent.putExtra("novo", "novo")
                      startActivity(intent)}

        logarButton!!.setOnClickListener { loginUser() }


    }//

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun Window.setStatusBarColorto(colorInt: Int){
        this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.statusBarColor = ContextCompat.getColor(baseContext, R.color.colorPrimaryDark)
    }

    private fun loginUser(){
        email= emailLoginEditText?.text.toString()
        senha=senhaLoginEditText?.text.toString()

         presenter!!.loginUser(email!!, senha!!)
    }

    override fun updateUi(_uid: String) {
        val intent = Intent(this@LoginActivity, ListaTarefasActivity::class.java)
        ControleTarefasApplication.uid = _uid
        startActivity(intent)
    }

    override fun exibeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
