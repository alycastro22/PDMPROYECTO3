package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.ConsultanosService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.ConsultanosDataCollectionItem
import kotlinx.android.synthetic.main.activity_consultanos.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsultanosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultanos)
        imageView11.setOnClickListener { Regresar()
            val botonGetId = findViewById<ImageView>(R.id.txtBuscar)
            botonGetId.setOnClickListener {v -> callServiceGetPerson()}
            val botonPostear = findViewById<ImageView>(R.id.txtAgregarAbog)
            botonPostear.setOnClickListener { v-> callServicePostPerson()}
            val botonPut = findViewById<TextView>(R.id.txtGuardar)
            botonPut.setOnClickListener { v-> callServicePutPerson()}
            val botonDelete = findViewById<TextView>(R.id.txtEliminar)
            botonDelete.setOnClickListener { v-> callServiceDeletePerson()}

        }

    }
    fun Regresar(){
        val cambio = Intent (this,MenuActivity::class.java)
        startActivity(cambio)

    }

    private fun callServiceDeletePerson() {
        val id = txtId5.text.toString().toLong()
        if (txtId5.text.isNotEmpty()) {

            val consultanosService:ConsultanosService=
                    RestEngine.buildService().create(ConsultanosService::class.java)
            var result: Call<ResponseBody> = consultanosService.deleteConsultanos(id)

            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@ConsultanosActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ConsultanosActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@ConsultanosActivity, "Sesion expirada", Toast.LENGTH_LONG)
                                .show()
                    } else {
                        Toast.makeText(
                                this@ConsultanosActivity,
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
        var id = txtId5.text.toString().toLong()
        var idcliente = txtIdCliente2.toString().toLong()
        var idabogado= txtIdAbogado4.text.toString().toLong()
        var fecha = txtFecha2.text.toString()

        println("id:" + id.toString() +  "cliente:" +idcliente.toString() + "idabogado:" + idabogado.toString() +"fecha:" +fecha.toString())
        //val fecha = "1995-12-06"
        val personInfo = ConsultanosDataCollectionItem(  id = id,
                idcliente=idcliente,
                idabogado=idabogado,
                fecha=fecha,
        )
        val retrofit = RestEngine.buildService().create(ConsultanosService::class.java)
        var result: Call<ConsultanosDataCollectionItem> = retrofit.updateConsultanos(personInfo)

        result.enqueue(object : Callback<ConsultanosDataCollectionItem> {
            override fun onFailure(call: Call<ConsultanosDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@ConsultanosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ConsultanosDataCollectionItem>,
                                    response: Response<ConsultanosDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@ConsultanosActivity,"OK"+response.body()!!.fecha, Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@ConsultanosActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@ConsultanosActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetPerson() {
        var  id = txtId5.text.toString().toLong()
        val consultanosService: ConsultanosService = RestEngine.buildService().create(ConsultanosService::class.java)
        var result: Call<ConsultanosDataCollectionItem> = consultanosService.getConsultanosById(id)

        result.enqueue(object : Callback<ConsultanosDataCollectionItem> {
            override fun onFailure(call: Call<ConsultanosDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@ConsultanosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<ConsultanosDataCollectionItem>,
                    response: Response<ConsultanosDataCollectionItem>


            ) {
                txtFecha2.setText("")
                Toast.makeText(this@ConsultanosActivity,"OK"+response.body()!!.fecha, Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun obtenertexto(){
        var id = txtId5.text.toString().toLong()
        var idcliente = txtIdCliente2.toString()
        var idabogado= txtIdAbogado4.text.toString()
        var fecha = txtFecha2.text.toString().toDouble()

        println("id:" + id.toString() +  "cliente:" +idcliente.toString() + "idabogado:" + idabogado.toString() +"fecha:" +fecha.toString())
    }

    private fun callServicePostPerson() {
        var id = txtId5.text.toString().toLong()
        var idcliente = txtIdCliente2.toString().toLong()
        var idabogado= txtIdAbogado4.text.toString().toLong()
        var fecha = txtFecha2.text.toString()

        println("id:" + id.toString() +  "cliente:" +idcliente.toString() + "idabogado:" + idabogado.toString() +"fecha:" +fecha.toString())

        //val fecha = "1995-12-06"
        val personInfo = ConsultanosDataCollectionItem(  id = null,
                idcliente= idcliente,
                idabogado=idabogado,
                fecha=fecha
        )


                addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@ConsultanosActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@ConsultanosActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callServiceGetPersons() {
        val personService: ConsultanosService = RestEngine.buildService().create(ConsultanosService::class.java)
        var result: Call<List<ConsultanosDataCollectionItem>> = personService.listaConsultanos()

        result.enqueue(object : Callback<List<ConsultanosDataCollectionItem>> {
            override fun onFailure(call: Call<List<ConsultanosDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@ConsultanosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<List<ConsultanosDataCollectionItem>>,
                    response: Response<List<ConsultanosDataCollectionItem>>
            ) {
                Toast.makeText(this@ConsultanosActivity,"OK"+response.body()!!.get(0).fecha, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(ConsultanosData: ConsultanosDataCollectionItem, onResult: (ConsultanosDataCollectionItem?) -> Unit){
        val retrofit = RestEngine.buildService().create(ConsultanosService::class.java)
        var result: Call<ConsultanosDataCollectionItem> = retrofit.addConsultanos(ConsultanosData)

        result.enqueue(object : Callback<ConsultanosDataCollectionItem> {
            override fun onFailure(call: Call<ConsultanosDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(call: Call<ConsultanosDataCollectionItem>,
                                    response: Response<ConsultanosDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                }
                else if (response.code() == 401){
                    Toast.makeText(this@ConsultanosActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@ConsultanosActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        }
        )
    }

}
