package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.AbogadoService
import kotlinx.android.synthetic.main.activity_abogados.*
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.AbogadoDataCollectionItem
import kotlinx.android.synthetic.main.activity_cita.*
import kotlinx.android.synthetic.main.activity_empleados.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


var Id:Long =0
var fecha:String = ""
var nombre:String = ""
var dni:Long = 0
var correo: String = ""
var telefono: Long = 0

class AbogadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abogados)

//        val botonGetId = findViewById<Button>(R.id.btnGetId)
//        botonGetId.setOnClickListener {v -> callServiceGetPerson()}
//        val botonConsumir = findViewById<Button>(R.id.btnConsumir)
//        botonConsumir.setOnClickListener {v -> callServiceGetPersons()}
        val botonPostear = findViewById<TextView>(R.id.txtAgregar)
        botonPostear.setOnClickListener { v-> callServicePostPerson()}
        val botonPut = findViewById<TextView>(R.id.txtGuardar)
        botonPut.setOnClickListener { v-> callServicePutPerson()}
        val botonDelete = findViewById<TextView>(R.id.txtEliminar)
        botonDelete.setOnClickListener { v-> callServiceDeletePerson()}
        imageView2.setOnClickListener { Regresar()
        }
       }


    fun Regresar(){
        val cambio = Intent (this,MenuActivity::class.java)
        startActivity(cambio)

    }
    private fun callServiceDeletePerson() {
        Id = txtId.text.toString().toLong()
        if (txtId.text.isNotEmpty()) {

            val abogadoService: AbogadoService =
                RestEngine.buildService().create(AbogadoService::class.
                java)
            var result: Call<ResponseBody> = abogadoService.deleteAbogado(1)

            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@AbogadosActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AbogadosActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@AbogadosActivity, "Sesion expirada", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(
                            this@AbogadosActivity,
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
    /*    Id = txtId.text.toString().toLong()
        fecha = txtFechaNaciAbo.text.toString()
        nombre=txtNombre.text.toString()
        dni = txtIdentidad.text.toString().toLong()
        correo=txtCorreo1.text.toString()
        telefono = txtTelefono.text.toString().toLong()*/
        val fecha = "1995-12-06"
        val personInfo = AbogadoDataCollectionItem(  id = 2,
            dni = 80210252,
            nombre =" nombre",
            fechaNacimiento = fecha,
            correo= "andreaRamos@gmail.com",
            telefono = 98574858


        )

        val retrofit = RestEngine.buildService().create(AbogadoService::class.java)
        var result: Call<AbogadoDataCollectionItem> = retrofit.updateAbogado(personInfo)

        result.enqueue(object : Callback<AbogadoDataCollectionItem> {
            override fun onFailure(call: Call<AbogadoDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@AbogadosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<AbogadoDataCollectionItem>,
                                    response: Response<AbogadoDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@AbogadosActivity,"OK"+response.body()!!.nombre, Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@AbogadosActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@AbogadosActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetPerson() {
        Id = txtId.text.toString().toLong()
        val personService:AbogadoService = RestEngine.buildService().create(AbogadoService::class.java)
        var result: Call<AbogadoDataCollectionItem> = personService.getAbogadoById(Id)

        result.enqueue(object : Callback<AbogadoDataCollectionItem> {
            override fun onFailure(call: Call<AbogadoDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@AbogadosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<AbogadoDataCollectionItem>,
                response: Response<AbogadoDataCollectionItem>
            ) {
                Toast.makeText(this@AbogadosActivity,"OK"+response.body()!!.nombre, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun callServicePostPerson() {
        //Id = txtId.text.toString().toLong()
        /*fecha = txtFechaNaciAbo.text.toString()
        nombre=txtNombre.text.toString()
        dni = txtIdentidad.text.toString().toLong()
        correo=txtCorreo1.text.toString()
        telefono = txtTelefono.text.toString().toLong()*/
        val fecha = "1995-12-06"
        val personInfo =AbogadoDataCollectionItem(  id = null,
            dni = 802102521,
            nombre =" nombre",
            fechaNacimiento = fecha,
            correo= "andreaRamos@gmail.com",
            telefono = 98574858
        )

        addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@AbogadosActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@AbogadosActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callServiceGetPersons() {
        val personService:AbogadoService = RestEngine.buildService().create(AbogadoService::class.java)
        var result: Call<List<AbogadoDataCollectionItem>> = personService.listabogado()

        result.enqueue(object : Callback<List<AbogadoDataCollectionItem>> {
            override fun onFailure(call: Call<List<AbogadoDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@AbogadosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<AbogadoDataCollectionItem>>,
                response: Response<List<AbogadoDataCollectionItem>>
            ) {
                Toast.makeText(this@AbogadosActivity,"OK"+response.body()!!.get(0).nombre, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(AbogadoData: AbogadoDataCollectionItem, onResult: (AbogadoDataCollectionItem?) -> Unit){
        val retrofit = RestEngine.buildService().create(AbogadoService::class.java)
        var result: Call<AbogadoDataCollectionItem> = retrofit.addAbogado(AbogadoData)

        result.enqueue(object : Callback<AbogadoDataCollectionItem> {
            override fun onFailure(call: Call<AbogadoDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(call: Call<AbogadoDataCollectionItem>,
                                    response: Response<AbogadoDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                }
                else if (response.code() == 401){
                    Toast.makeText(this@AbogadosActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@AbogadosActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        }
        )
    }



    fun String.toDate(format: String, locale: Locale = Locale.getDefault()): Date = SimpleDateFormat(format, locale).parse(this)
}
