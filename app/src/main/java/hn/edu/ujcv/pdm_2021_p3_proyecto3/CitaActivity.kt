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
            val botonGetId = findViewById<ImageView>(R.id.txtBuscarAbog)
            botonGetId.setOnClickListener {v -> callServiceGetPerson()}
            val botonPostear = findViewById<ImageView>(R.id.txtAgregarAbog)
            botonPostear.setOnClickListener { v-> callServicePostPerson()}
            val botonPut = findViewById<TextView>(R.id.txtGuardarAbog)
            botonPut.setOnClickListener { v-> callServicePutPerson()}
            val botonDelete = findViewById<TextView>(R.id.txtEliminarAbog)
            botonDelete.setOnClickListener { v-> callServiceDeletePerson()}

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
    var idcita = txtIdCita1.text.toString().toLong()
    var fecha = txtFecha3.toString()
    var descripcion= txtDescripcion5.text.toString()
    var precio = txtPrecio3.text.toString().toDouble()
    var idcaso=txtIdCaso3.text.toString()

    println("Id:" + idcita.toString() +  "fecha:" + fecha.toString() + "descripcion:" + descripcion.toString() +"precio:" +precio.toDouble()+"caso:" +  idcaso.toString()
         )
    //val fecha = "1995-12-06"
    val personInfo = CitaDataCollectionItem(  idcita = idcita.toString().toLong(),

            fecha=fecha,
            descripcion = descripcion,
            precio= precio,
            idcaso = idcaso.toLong())



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
    var  IdCita = txtIdCita1.text.toString().toLong()
    val citaService: CitaService = RestEngine.buildService().create(CitaService::class.java)
    var result: Call<CitaDataCollectionItem> = citaService.getCitaById(IdCita)

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
private fun obtenertexto(){
    var idcita = txtIdCita1.text.toString().toLong()
    var fecha = txtFecha3.toString()
    var descripcion= txtDescripcion5.text.toString()
    var precio = txtPrecio3.text.toString().toDouble()
    var idcaso=txtIdCaso3.text.toString()
    println("idcita:" + idcita.toString() +  "fecha:" + fecha.toString() + "descripcion:" + descripcion.toString() +"precio:" +
            precio.toDouble()+ "caso"+ idcaso.toString() )
}

private fun callServicePostPerson() {
    var idcita = txtIdCita1.text.toString().toLong()
    var fecha = txtFecha3.toString()
    var descripcion= txtDescripcion5.text.toString()
    var precio = txtPrecio3.text.toString().toDouble()
    var idcaso=txtIdCaso3.text.toString()

    //val fecha = "1995-12-06"
    val personInfo = CitaDataCollectionItem(  idcita = null,
            fecha=fecha,
            descripcion = descripcion,
            precio= precio,
            idcaso = idcaso.toLong())

    addPerson(personInfo) {
        if (it?.idcita != null) {
            Toast.makeText(this@CitaActivity,"OK"+it?.idcita, Toast.LENGTH_LONG).show()
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

}