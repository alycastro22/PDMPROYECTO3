package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.AgenciaService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.AgenciaDataCollectionItem
import kotlinx.android.synthetic.main.activity_agencia.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgenciaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agencia)
        imageView3.setOnClickListener { Regresar()}

        val botonPostear = findViewById<TextView>(R.id.txtAgregar)
        botonPostear.setOnClickListener { v-> callServicePostPerson()}
        val botonPut = findViewById<TextView>(R.id.txtGuardar)
        botonPut.setOnClickListener { v-> callServicePutPerson()}
        val botonDelete = findViewById<TextView>(R.id.txtEliminar)
        botonDelete.setOnClickListener { v-> callServiceDeleteAgencia()}
        val botonbuscar = findViewById<ImageView>(R.id.imageView20)
        botonbuscar.setOnClickListener { v-> callServiceGetAgencia()}


    }
    fun Regresar(){
        val cambio = Intent (this, MenuActivity::class.java)
        startActivity(cambio)
    }
    private fun callServiceDeleteAgencia() {
        val Id = txtId13.text.toString().toLong()
        if (txtId13.text.isNotEmpty()) {

            val agenciaService: AgenciaService =
                RestEngine.buildService().create(AgenciaService::class.java)
            var result: Call<ResponseBody> = agenciaService.deleteAgencia(Id)

            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@AgenciaActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AgenciaActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@AgenciaActivity, "Sesion expirada", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(
                            this@AgenciaActivity,
                            "Fallo al traer el item",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })
        } else {
        }

    }
    private fun callServicePutPerson() {
            //fecha = txtFechaNaciAbo.text.toString()
            val nombre=txtNombreCaso.text.toString()
            val direccion = txtDireccion.text.toString()
            val id =txtId13.text.toString().toLong()
            val telefono = txtTelefono1.text.toString().toLong()


        val personInfo = AgenciaDataCollectionItem(  id = id,
            nombre = nombre,
            direccion = direccion ,
            telefono = telefono

        )

        val retrofit = RestEngine.buildService().create(AgenciaService::class.java)
        var result: Call<AgenciaDataCollectionItem> = retrofit.updateAgencia(personInfo)

        result.enqueue(object : Callback<AgenciaDataCollectionItem> {
            override fun onFailure(call: Call<AgenciaDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@AgenciaActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<AgenciaDataCollectionItem>,
                                    response: Response<AgenciaDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@AgenciaActivity,"OK"+response.body()!!.nombre, Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@AgenciaActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@AgenciaActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetAgencia() {
        val Id = txtId13.text.toString().toLong()
        val personService: AgenciaService = RestEngine.buildService().create(AgenciaService::class.java)
        var result: Call<AgenciaDataCollectionItem> = personService.getAgenciasById(Id)

        result.enqueue(object : Callback<AgenciaDataCollectionItem> {
            override fun onFailure(call: Call<AgenciaDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@AgenciaActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<AgenciaDataCollectionItem>,
                response: Response<AgenciaDataCollectionItem>
            ) {
                Toast.makeText(this@AgenciaActivity,"OK"+response.body()!!.nombre, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun callServicePostPerson() {
        val nombre=txtNombreCaso.text.toString()
        val direccion = txtDireccion.text.toString()
        val id =txtId13.text.toString()
        val telefono = txtTelefono1.text.toString().toLong()

        val personInfo = AgenciaDataCollectionItem(  id = null,
                nombre = nombre,
                direccion = direccion ,
                telefono = telefono
        )

        addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@AgenciaActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@AgenciaActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

        private fun callServiceGetAgencias() {
        val personService: AgenciaService = RestEngine.buildService().create(AgenciaService::class.java)
        var result: Call<List<AgenciaDataCollectionItem>> = personService.listaAgencia()

        result.enqueue(object : Callback<List<AgenciaDataCollectionItem>> {
            override fun onFailure(call: Call<List<AgenciaDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@AgenciaActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<AgenciaDataCollectionItem>>,
                response: Response<List<AgenciaDataCollectionItem>>
            ) {
                Toast.makeText(this@AgenciaActivity,"OK"+response.body()!!.get(0).nombre, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(AgenciaData: AgenciaDataCollectionItem, onResult: (AgenciaDataCollectionItem?) -> Unit) {
        val retrofit = RestEngine.buildService().create(AgenciaService::class.java)
        var result: Call<AgenciaDataCollectionItem> = retrofit.addAgencia(AgenciaData)

        result.enqueue(object : Callback<AgenciaDataCollectionItem> {
            override fun onFailure(call: Call<AgenciaDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(
                call: Call<AgenciaDataCollectionItem>,
                response: Response<AgenciaDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                } else if (response.code() == 401) {
                    Toast.makeText(this@AgenciaActivity, "Sesion expirada", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(
                        this@AgenciaActivity,
                        "Fallo al traer el item",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
        )
    }

}