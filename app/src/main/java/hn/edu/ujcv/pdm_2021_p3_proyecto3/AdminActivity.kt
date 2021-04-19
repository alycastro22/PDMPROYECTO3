package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.AdminService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.AdminDataCollectionItem
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_tipopago.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //txvRegistrar.setOnClickListener { ingresar() }
        val botonRegistrar = findViewById<TextView>(R.id.txvRegistrar)
        botonRegistrar.setOnClickListener { v-> callServiceGetPerson()}
    }


    /*fun ingresar() {
        if(txtUsuario.text.isNotEmpty() && txtContraseña.text.isNotEmpty()){
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }*/

    private fun callServiceGetPerson() {
        var  usuario = txtUsuario.text.toString()
        val usuarioService: AdminService = RestEngine.buildService().create(AdminService::class.java)
        var result: Call<AdminDataCollectionItem> = usuarioService.getAdminByUsurio(usuario)

        result.enqueue(object : Callback<AdminDataCollectionItem> {
            override fun onFailure(call: Call<AdminDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@AdminActivity,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(
                    call: Call<AdminDataCollectionItem>,
                    response: Response<AdminDataCollectionItem>
            ) {
                //txtDescripcion3.setText("")
              //  Toast.makeText(this@TipoPagoActivity,"OK"+response.body()!!.descripcion, Toast.LENGTH_LONG).show()
                val intent = Intent(this@AdminActivity, MenuActivity::class.java)
                startActivity(intent)
            }
        })
    }



}