package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.CitaService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.CitaDataCollectionItem
import kotlinx.android.synthetic.main.activity_abogados.*
import kotlinx.android.synthetic.main.activity_cita.*
import kotlinx.android.synthetic.main.activity_menu.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cita)

        imageView5.setOnClickListener { Regresar()
            val botonGetId = findViewById<ImageView>(R.id.txtBuscarCita)
            botonGetId.setOnClickListener {v -> callServiceGetPerson()}
            val botonPostear = findViewById<TextView>(R.id.txtAgregarCita)
            botonPostear.setOnClickListener { v-> callServicePostPerson()}
            val botonPut = findViewById<TextView>(R.id.txtGuardarCita)
            botonPut.setOnClickListener { v-> callServicePutPerson()}
            val botonDelete = findViewById<TextView>(R.id.txtEliminarCita)
            botonDelete.setOnClickListener { v-> callServiceDeletePerson()}
            txtLimpiarCita.setOnClickListener { limpiar() }

        }

    }
    fun Regresar(){
        val cambio = Intent (this,MenuActivity::class.java)
        startActivity(cambio)


    }
    private fun callServiceDeletePerson() {
        val idcita = txtIdCita1.text.toString().toLong()
        if (txtIdCita1.text.isNotEmpty()) {

            val citaService: CitaService =
                    RestEngine.buildService().create(CitaService::class.java)
            var result: Call<ResponseBody> = citaService.deleteCita(idcita)

            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@CitaActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@CitaActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@CitaActivity, "Sesion expirada", Toast.LENGTH_LONG)
                                .show()
                    } else {
                        Toast.makeText(
                                this@CitaActivity,
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
        var id = txtIdCita1.text.toString().toLong()
        var fecha = txtFecha3.toString()
        var descripcion= txtDescripcion5.text.toString()
        var precio = txtPrecio3.text.toString().toDouble()
        var idcaso=txtIdCaso3.text.toString().toLong()

        println("Id:" + id.toString() +  "fecha:" + fecha.toString() + "descripcion:" + descripcion.toString() +"precio:" +precio.toDouble()+"caso:" +  idcaso.toString()
             )

        val personInfo = CitaDataCollectionItem(  id = id,
                fecha=fecha,
                descripcion = descripcion,
                precio= precio,
                idcaso = idcaso
        )



        val retrofit = RestEngine.buildService().create(CitaService::class.java)
        var result: Call<CitaDataCollectionItem> = retrofit.updateCita(personInfo)

        result.enqueue(object : Callback<CitaDataCollectionItem> {
            override fun onFailure(call: Call<CitaDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@CitaActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<CitaDataCollectionItem>,
                                    response: Response<CitaDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@CitaActivity,"OK"+response.body()!!.descripcion,Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@CitaActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@CitaActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetPerson() {
        var  id = txtIdCita1.text.toString().toLong()
        val citaService: CitaService = RestEngine.buildService().create(CitaService::class.java)
        var result: Call<CitaDataCollectionItem> = citaService.getCitaById(id)

        result.enqueue(object : Callback<CitaDataCollectionItem> {
            override fun onFailure(call: Call<CitaDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@CitaActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<CitaDataCollectionItem>,
                    response: Response<CitaDataCollectionItem>


            ) {
                txtDescripcion5.setText("")
                Toast.makeText(this@CitaActivity,"OK"+response.body()!!.descripcion, Toast.LENGTH_LONG).show()
            }
        })
    }
/*private fun obtenertexto(){
    var idcita = txtIdCita1.text.toString().toLong()
    var fecha = txtFecha3.toString()
    var descripcion= txtDescripcion5.text.toString()
    var precio = txtPrecio3.text.toString().toDouble()
    var idcaso=txtIdCaso3.text.toString()
    println("idcita:" + idcita.toString() +  "fecha:" + fecha.toString() + "descripcion:" + descripcion.toString() +"precio:" +
            precio.toDouble()+ "caso"+ idcaso.toString() )
}*/

    private fun callServicePostPerson() {
        //var id = txtIdCita1.text.toString().toLong()
        var fecha = txtFecha3.toString()
        var descripcion = txtDescripcion5.text.toString()
        var precio = txtPrecio3.text.toString().toDouble()
        var idcaso = txtIdCaso3.text.toString().toLong()

        //val fecha = "1995-12-06"
        val personInfo = CitaDataCollectionItem(  id = null,
                fecha=fecha,
                descripcion = descripcion,
                precio= precio,
                idcaso = idcaso
        )

        addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@CitaActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@CitaActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callServiceGetPersons() {
        val personService: CitaService = RestEngine.buildService().create(CitaService::class.java)
        var result: Call<List<CitaDataCollectionItem>> = personService.listaCita()

        result.enqueue(object : Callback<List<CitaDataCollectionItem>> {
            override fun onFailure(call: Call<List<CitaDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@CitaActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<List<CitaDataCollectionItem>>,
                    response: Response<List<CitaDataCollectionItem>>
            ) {
                Toast.makeText(this@CitaActivity,"OK"+response.body()!!.get(0).descripcion, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(CitaData: CitaDataCollectionItem, onResult: (CitaDataCollectionItem?) -> Unit){
        val retrofit = RestEngine.buildService().create(CitaService::class.java)
        var result: Call<CitaDataCollectionItem> = retrofit.addCita(CitaData)

        result.enqueue(object : Callback<CitaDataCollectionItem> {
            override fun onFailure(call: Call<CitaDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(call: Call<CitaDataCollectionItem>,
                                    response: Response<CitaDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                }
                else if (response.code() == 401){
                    Toast.makeText(this@CitaActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@CitaActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        }
        )
    }

    private fun limpiar(){
        txtIdCita1.text.clear()
        txtFecha3.text.clear()
        txtDescripcion5.text.clear()
        txtPrecio3.text.clear()
        txtIdCaso3.text.clear()
    }
}