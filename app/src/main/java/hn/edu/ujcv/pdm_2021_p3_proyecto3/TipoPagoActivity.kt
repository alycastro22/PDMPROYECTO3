package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.TipoPagoService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.TipoPagoDataCollectionItem
import kotlinx.android.synthetic.main.activity_tipopago.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TipoPagoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipopago)
        imageView8.setOnClickListener { Regresar()
        }
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
        val Id4 = txtId4.text.toString().toLong()
        if (txtId4.text.isNotEmpty()) {

            val pagoService: TipoPagoService =
                RestEngine.buildService().create(
                    TipoPagoService::class.
                    java)
            var result: Call<ResponseBody> =pagoService.deleteTipoPago(Id4)

            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@TipoPagoActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@TipoPagoActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@TipoPagoActivity, "Sesion expirada", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(
                            this@TipoPagoActivity,
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
        var id = txtId4.text.toString().toLong()
        var descripcion = txtDescripcion3.text.toString()

        val personInfo = TipoPagoDataCollectionItem(  id = id,
            descripcion = descripcion,
        )

        val retrofit = RestEngine.buildService().create(TipoPagoService::class.java)
        var result: Call<TipoPagoDataCollectionItem> = retrofit.updateTipoPago(personInfo)

        result.enqueue(object : Callback<TipoPagoDataCollectionItem> {
            override fun onFailure(call: Call<TipoPagoDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@TipoPagoActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<TipoPagoDataCollectionItem>,
                                    response: Response<TipoPagoDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@TipoPagoActivity,"OK"+response.body()!!.descripcion, Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@TipoPagoActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@TipoPagoActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetPerson() {
        var  Id = txtId4.text.toString().toLong()
        val clienteService: TipoPagoService = RestEngine.buildService().create(TipoPagoService::class.java)
        var result: Call<TipoPagoDataCollectionItem> = clienteService.getTipoPagoById(Id)

        result.enqueue(object : Callback<TipoPagoDataCollectionItem> {
            override fun onFailure(call: Call<TipoPagoDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@TipoPagoActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<TipoPagoDataCollectionItem>,
                response: Response<TipoPagoDataCollectionItem>


            ) {
                txtDescripcion3.setText("")
                Toast.makeText(this@TipoPagoActivity,"OK"+response.body()!!.descripcion, Toast.LENGTH_LONG).show()
            }
        })
    }
   /* private fun obtenertexto(){
        var Id = txtId4.text.toString().toLong()
        var Descripcion = txtDescripcion3.text.toString()

        println("Id:" + Id.toString() +  "Descripcion:" +Descripcion.toString() )
    }*/

    private fun callServicePostPerson() {
        var id = txtId4.text.toString().toLong()
        var descripcion = txtDescripcion3.text.toString()

        val personInfo = TipoPagoDataCollectionItem(  id = id,
                descripcion = descripcion,
        )


        addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@TipoPagoActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@TipoPagoActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callServiceGetPersons() {
        val personService: TipoPagoService = RestEngine.buildService().create(TipoPagoService::class.java)
        var result: Call<List<TipoPagoDataCollectionItem>> = personService.listaTipoPago()

        result.enqueue(object : Callback<List<TipoPagoDataCollectionItem>> {
            override fun onFailure(call: Call<List<TipoPagoDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@TipoPagoActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<TipoPagoDataCollectionItem>>,
                response: Response<List<TipoPagoDataCollectionItem>>
            ) {
                Toast.makeText(this@TipoPagoActivity,"OK"+response.body()!!.get(0).descripcion, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(ClienteData: TipoPagoDataCollectionItem, onResult: (TipoPagoDataCollectionItem?) -> Unit){
        val retrofit = RestEngine.buildService().create(TipoPagoService::class.java)
        var result: Call<TipoPagoDataCollectionItem> = retrofit.addTipoPago(ClienteData)

        result.enqueue(object : Callback<TipoPagoDataCollectionItem> {
            override fun onFailure(call: Call<TipoPagoDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(call: Call<TipoPagoDataCollectionItem>,
                                    response: Response<TipoPagoDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                }
                else if (response.code() == 401){
                    Toast.makeText(this@TipoPagoActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@TipoPagoActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        }
        )
    }


}