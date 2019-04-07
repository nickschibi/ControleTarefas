package com.example.controletarefas.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.example.controletarefas.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        gotoLogin()
    }

    fun gotoLogin(){
       val intent = Intent(this, LoginActivity::class.java)

        Handler().postDelayed({
            intent.change()
        },2000)
    }

    fun Intent.change(){
        startActivity(this)
        finish()
    }
}
