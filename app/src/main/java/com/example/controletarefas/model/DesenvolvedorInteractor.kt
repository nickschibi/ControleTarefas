package com.example.controletarefas.model
import android.util.Log
import com.example.controletarefas.RetrofitConfig
import com.example.controletarefas.contratos.ContratoDesenvolvedor.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.graphics.Bitmap
import com.example.controletarefas.Utils
import com.google.firebase.database.*
import java.io.ByteArrayOutputStream
import java.util.*


class DesenvolvedorInteractor (_presenter : Presenter): Interactor {

    private var presenter = _presenter

    //Firebase
    private var database = FirebaseDatabase.getInstance() // instancia  do banco
    private var databaseReference = database!!.reference!! // referencia do banco
    private var auth = FirebaseAuth.getInstance() // meu autenticador

    private val TAG = "DesenvolvedorInteractor"

    override fun buscaEndereco(_cep: String){
        var call =  RetrofitConfig().getCEPService().buscarCEP(_cep!!)
        call.enqueue(object : Callback<Endereco> {
            override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                var _endereco : Endereco? = response.body()
                presenter.preencheEndereco(_endereco!!)
            }

            override fun onFailure(call: Call<Endereco>, t: Throwable) {
                Log.e("CepServico   ", "Erro ao buscar o cep")

            }
        })
    }

    override fun autenticaUsuario(_email: String, _senha: String, _desenvolvedor: Desenvolvedor) {
        auth!!.createUserWithEmailAndPassword(_email, _senha)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Criado Usuario com email com sucesso")
                    val userId = auth!!.currentUser!!.uid
                    val key =  databaseReference!!.child("desenvolvedores").push().key
                    _desenvolvedor.uid = userId
                    databaseReference!!.child("desenvolvedores").child(key!!).setValue(_desenvolvedor)

                    confirmacaoEmail()
                    presenter.updateUi(userId)
                } else {
                    Log.w(TAG, "Criacao de Usuario com email falha", task.exception)
                }

            }
    }

    //envia email para usuario conprovando sua autenticidade
    private fun confirmacaoEmail() {
        val USER = auth!!.currentUser
        USER!!.sendEmailVerification().addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                presenter.exibeToast("Um email de confirmacao foi enviado!")
            }
        }
    }


    override fun atualizaDesenvolvedor( _desenvolvedor: Desenvolvedor){


        databaseReference!!.child("desenvolvedores").child(_desenvolvedor.uid.toString()).setValue(_desenvolvedor)

    }

    override fun trazDesenvolvedor(uid: String) {
        databaseReference!!.child("desenvolvedores").orderByChild("uid").equalTo(uid).addValueEventListener(object :
            ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    var desenvolvedor: Desenvolvedor = snapshot.getValue(Desenvolvedor::class.java)!!
                    presenter.preencheCamposDev(desenvolvedor)
                }

            } override fun onCancelled(error: DatabaseError) {}
        })

    }


}