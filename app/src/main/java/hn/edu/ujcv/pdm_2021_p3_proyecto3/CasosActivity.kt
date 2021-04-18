package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.CasoService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.CasosDataCollectionItem
import kotlinx.android.synthetic.main.activity_abogados.*
import kotlinx.android.synthetic.main.activity_casos.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CasosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casos)
        imageView4.setOnClickListener { Regresar() }
        val botonPostear = findViewById<TextView>(R.id.txtAgregar)
        botonPostear.setOnClickListener { v-> callServicePostPerson()}
        val botonPut = findViewById<TextView>(R.id.txtGuardar)
        botonPut.setOnClickListener { v-> callServicePutPerson()}
        val botonDelete = findViewById<TextView>(R.id.txtEliminar)
        botonDelete.setOnClickListener { v-> callServiceDeletePerson()}


    }
    fun Regresar(){
        val cambio = Intent (this, MenuActivity::class.java)
        startActivity(cambio)

    }
    private fun callServiceDeletePerson() {
        val Id = txtId6.text.toString().toLong()
        if (txtId6.text.isNotEmpty()) {

            val casosService: CasoService =
                    RestEngine.buildService().create(CasoService::class.java)
            var result: Call<ResponseBody> = casosService.deleteCasos(1)

            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@CasosActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@CasosActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@CasosActivity, "Sesion expirada", Toast.LENGTH_LONG)
                                .show()
                    } else {
                        Toast.makeText(
                                this@CasosActivity,
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
        val personInfo =  CasosDataCollectionItem(  id = 2,
                idClientes = 1,
                idAbogados = 1,
                inicio = "2021-2-1",
                finalizacion = "2021-2-1",
                idTipoCaso = 1,

        )

        val retrofit = RestEngine.buildService().create(CasoService::class.java)
        var result: Call<CasosDataCollectionItem> = retrofit.updateCasos(personInfo)

        result.enqueue(object : Callback<CasosDataCollectionItem> {
            override fun onFailure(call: Call<CasosDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@CasosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<CasosDataCollectionItem>,
                                    response: Response<CasosDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@CasosActivity,"OK"+response.body()!!.inicio, Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@CasosActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@CasosActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetPerson() {
        val Id = txtId6.text.toString().toLong()
        val personService: CasoService = RestEngine.buildService().create(CasoService::class.java)
        var result: Call<CasosDataCollectionItem> = personService.getCasosById(Id)

        result.enqueue(object : Callback<CasosDataCollectionItem> {
            override fun onFailure(call: Call<CasosDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@CasosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<CasosDataCollectionItem>,
                    response: Response<CasosDataCollectionItem>
            ) {
                Toast.makeText(this@CasosActivity,"OK"+response.body()!!.inicio, Toast.LENGTH_LONG).show()
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
        val personInfo = CasosDataCollectionItem(  id = 2,
                idClientes = 1,
                idAbogados = 1,
                inicio = "2021-2-1",
                finalizacion = "2021-2-1",
                idTipoCaso = 1,
        )

        addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@CasosActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@CasosActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callServiceGetPersons() {
        val personService: CasoService = RestEngine.buildService().create(CasoService::class.java)
        var result: Call<List<CasosDataCollectionItem>> = personService.listaCasos()

        result.enqueue(object : Callback<List<CasosDataCollectionItem>> {
            override fun onFailure(call: Call<List<CasosDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@CasosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<List<CasosDataCollectionItem>>,
                    response: Response<List<CasosDataCollectionItem>>
            ) {
                Toast.makeText(this@CasosActivity,"OK"+response.body()!!.get(0).inicio, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(CasoData: CasosDataCollectionItem, onResult: (CasosDataCollectionItem?) -> Unit) {
        val retrofit = RestEngine.buildService().create(CasoService::class.java)
        var result: Call<CasosDataCollectionItem> = retrofit.addCaso(CasoData)

        result.enqueue(object : Callback<CasosDataCollectionItem> {
            override fun onFailure(call: Call<CasosDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(
                    call: Call<CasosDataCollectionItem>,
                    response: Response<CasosDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                } else if (response.code() == 401) {
                    Toast.makeText(this@CasosActivity, "Sesion expirada", Toast.LENGTH_LONG)
                            .show()
                } else {
                    Toast.makeText(
                            this@CasosActivity,
                            "Fallo al traer el item",
                            Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
        )
    }


}