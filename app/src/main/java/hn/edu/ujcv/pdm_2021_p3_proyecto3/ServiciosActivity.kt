package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.ServicioService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.ServiciosDataCollectionItem
import kotlinx.android.synthetic.main.activity_factura.*
import kotlinx.android.synthetic.main.activity_servicios.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiciosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)
        imageView12.setOnClickListener { Regresar()}
        val botonGetId = findViewById<ImageView>(R.id.txtBuscar)
        botonGetId.setOnClickListener {v -> callServiceGetPerson()}
        val botonPostear = findViewById<TextView>(R.id.txtAgregar)
        botonPostear.setOnClickListener { v-> callServicePostPerson()}
        val botonPut = findViewById<TextView>(R.id.txtGuardar)
        botonPut.setOnClickListener { v-> callServicePutPerson()}
        val botonDelete = findViewById<TextView>(R.id.txtEliminar)
        botonDelete.setOnClickListener { v-> callServiceDeletePerson()}

    }
    fun Regresar(){
        val cambio = Intent (this,MenuActivity::class.java)
        startActivity(cambio)
    }
    private fun callServiceDeletePerson() {
        val Id = txtId7.text.toString().toLong()
        if (txtId7.text.isNotEmpty()) {

            val servicioService: ServicioService = RestEngine.buildService().create(ServicioService::class.java)
            var result: Call<ResponseBody> = servicioService.deleteServicio(Id)

            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@ServiciosActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ServiciosActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@ServiciosActivity, "Sesion expirada", Toast.LENGTH_LONG)
                                .show()
                    } else {
                        Toast.makeText(
                                this@ServiciosActivity,
                                "Fallo al traer el item",
                                Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })
        }

    }

    private fun callServicePutPerson() {
        val Id = txtId7.text.toString().toLong()
        val tiposervicio = txtTipoServicio.text.toString()
        val asunto = txtIdAsunto.text.toString()
        val precio = txtPrecio2.text.toString()

        val personInfo = ServiciosDataCollectionItem(
                id = Id.toString().toLong(),
                tipoServicio = tiposervicio,
                asunto = asunto,
                precio = precio.toDouble()

        )

        val retrofit = RestEngine.buildService().create(ServicioService::class.java)
        var result: Call<ServiciosDataCollectionItem> = retrofit.updateServicio(personInfo)

        result.enqueue(object : Callback<ServiciosDataCollectionItem> {
            override fun onFailure(call: Call<ServiciosDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@ServiciosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ServiciosDataCollectionItem>,
                                    response: Response<ServiciosDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@ServiciosActivity,"OK"+response.body()!!.tipoServicio, Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@ServiciosActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@ServiciosActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetPerson() {
        var  Id = txtId7.text.toString().toLong()
        val servicioService : ServicioService = RestEngine.buildService().create(ServicioService::class.java)
        var result: Call<ServiciosDataCollectionItem> = servicioService.getServicioById(Id)

        result.enqueue(object : Callback<ServiciosDataCollectionItem> {
            override fun onFailure(call: Call<ServiciosDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@ServiciosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<ServiciosDataCollectionItem>,
                    response: Response<ServiciosDataCollectionItem>

            ) {
                txtTipoServicio.setText("")
                Toast.makeText(this@ServiciosActivity,"OK"+response.body()!!.tipoServicio, Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun obtenertexto(){
        val Id = txtId7.text.toString().toLong()
        val tiposervicio = txtTipoServicio.text.toString()
        val asunto = txtIdAsunto.text.toString()
        val precio = txtPrecio2.text.toString()
    }

    private fun callServicePostPerson() {
        val Id = txtId7.text.toString().toLong()
        val tiposervicio = txtTipoServicio.text.toString()
        val asunto = txtIdAsunto.text.toString()
        val precio = txtPrecio2.text.toString()


        //val fecha = "1995-12-06"
        val personInfo = ServiciosDataCollectionItem(
                id = Id.toString().toLong(),
                tipoServicio = tiposervicio,
                asunto = asunto,
                precio = precio.toDouble()

        )


        addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@ServiciosActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@ServiciosActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callServiceGetPersons() {
        val personService: ServicioService = RestEngine.buildService().create(ServicioService::class.java)
        var result: Call<List<ServiciosDataCollectionItem>> = personService.listaServicio()

        result.enqueue(object : Callback<List<ServiciosDataCollectionItem>> {
            override fun onFailure(call: Call<List<ServiciosDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@ServiciosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<List<ServiciosDataCollectionItem>>,
                    response: Response<List<ServiciosDataCollectionItem>>
            ) {
                Toast.makeText(this@ServiciosActivity,"OK"+response.body()!!.get(0).tipoServicio, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(servicioData:ServiciosDataCollectionItem, onResult: (ServiciosDataCollectionItem?) -> Unit){
        val retrofit = RestEngine.buildService().create(ServicioService::class.java)
        var result: Call<ServiciosDataCollectionItem> = retrofit.addServicio(servicioData)

        result.enqueue(object : Callback<ServiciosDataCollectionItem> {
            override fun onFailure(call: Call<ServiciosDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(call: Call<ServiciosDataCollectionItem>,
                                    response: Response<ServiciosDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                }
                else if (response.code() == 401){
                    Toast.makeText(this@ServiciosActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@ServiciosActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        }
        )
    }
}