package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.PagoService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.PagoDataCollectionItem
import kotlinx.android.synthetic.main.activity_pago.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PagoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago)
        imageView10.setOnClickListener { Regresar()
        }
        val botonPostear = findViewById<TextView>(R.id.txtAgregarPag)
        botonPostear.setOnClickListener { v-> callServicePostPerson()}
        val botonPut = findViewById<TextView>(R.id.txtGuardarPag)
        botonPut.setOnClickListener { v-> callServicePutPerson()}
        val botonDelete = findViewById<TextView>(R.id.txtEliminarPag)
        botonDelete.setOnClickListener { v-> callServiceDeletePerson()}
        val botonBuscar = findViewById<ImageView>(R.id.txtBuscarPag)
        botonBuscar.setOnClickListener{v -> callServiceGetPerson()}

    }



    fun Regresar(){
        val cambio = Intent (this,MenuActivity::class.java)
        startActivity(cambio)

    }
    private fun callServiceDeletePerson() {
        val Id = txtIdPago1.text.toString().toLong()
        if (txtIdPago1.text.isNotEmpty()) {

            val pagoService: PagoService =
                RestEngine.buildService().create(
                    PagoService::class.
                    java)
            var result: Call<ResponseBody> =pagoService.deletePago(Id)

            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@PagoActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@PagoActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@PagoActivity, "Sesion expirada", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(
                            this@PagoActivity,
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
        var id = txtIdPago1.text.toString().toLong()
        var idtipoPago =txtIdTipoPago1.text.toString().toLong()
        var cantidadPago = txtCantidadPago.text.toString().toDouble()
        var impuesto = txtImpuestos.text.toString().toDouble()
        var subtotal = txtSubtotal.text.toString().toDouble()
        var totalPagar = txtTotal.text.toString().toDouble()

        val personInfo = PagoDataCollectionItem(  id = id,
            id_TipoPago = idtipoPago,
            cantidad_pago= cantidadPago,
            impuesto = impuesto,
            subtotal = subtotal,
            totalpagar = totalPagar
        )

        val retrofit = RestEngine.buildService().create(PagoService::class.java)
        var result: Call<PagoDataCollectionItem> = retrofit.updatePago(personInfo)

        result.enqueue(object : Callback<PagoDataCollectionItem> {
            override fun onFailure(call: Call<PagoDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@PagoActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<PagoDataCollectionItem>,
                                    response: Response<PagoDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@PagoActivity,"OK"+response.body()!!.cantidad_pago, Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@PagoActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@PagoActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetPerson() {
        var  Id = txtIdPago1.text.toString().toLong()
        val clienteService: PagoService = RestEngine.buildService().create(PagoService::class.java)
        var result: Call<PagoDataCollectionItem> = clienteService.getPagoById(Id)

        result.enqueue(object : Callback<PagoDataCollectionItem> {
            override fun onFailure(call: Call<PagoDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@PagoActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<PagoDataCollectionItem>,
                response: Response<PagoDataCollectionItem>


            ) {
                txtCantidadPago.setText("")
                Toast.makeText(this@PagoActivity,"OK"+response.body()!!.cantidad_pago, Toast.LENGTH_LONG).show()
            }
        })
    }
    /*private fun obtenertexto(){
        var Id = txtIdPago1.text.toString().toLong()
        var Id_TipoPago = txtIdTipoPago1.text.toString()
        var CantidadPago= txtCantidadPago.text.toString()
        var Impuesto=txtImpuestos.text.toString()
        var Subtotal = txtSubtotal.toString().toLong()
        var TotalPagar = txtTotal.toString().toLong()

        println("Id:" + Id.toString() +  "Id TipoPago:" + Id_TipoPago.toString() + "CantidadPago" + CantidadPago.toString() +
                "Impuesto"+ Impuesto+ "Subtotal" + Subtotal.toString()+"TotalPagar" + TotalPagar.toString() )
    }*/

    private fun callServicePostPerson() {
        //var Id = txtIdPago1.text.toString().toLong()
        var idtipoPago =txtIdTipoPago1.text.toString().toLong()
        var cantidadPago = txtCantidadPago.text.toString().toDouble()
        var impuesto = txtImpuestos.text.toString().toDouble()
        var subtotal = txtSubtotal.text.toString().toDouble()
        var totalPagar = txtTotal.text.toString().toDouble()

        val personInfo = PagoDataCollectionItem(  id = null,
                id_TipoPago = idtipoPago,
                cantidad_pago= cantidadPago,
                impuesto = impuesto,
                subtotal = subtotal,
                totalpagar = totalPagar
        )


        addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@PagoActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@PagoActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callServiceGetPersons() {
        val personService: PagoService = RestEngine.buildService().create(PagoService::class.java)
        var result: Call<List<PagoDataCollectionItem>> = personService.listaPago()

        result.enqueue(object : Callback<List<PagoDataCollectionItem>> {
            override fun onFailure(call: Call<List<PagoDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@PagoActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<PagoDataCollectionItem>>,
                response: Response<List<PagoDataCollectionItem>>
            ) {
                Toast.makeText(this@PagoActivity,"OK"+response.body()!!.get(0).cantidad_pago, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(ClienteData: PagoDataCollectionItem, onResult: (PagoDataCollectionItem?) -> Unit){
        val retrofit = RestEngine.buildService().create(PagoService::class.java)
        var result: Call<PagoDataCollectionItem> = retrofit.addPago(ClienteData)

        result.enqueue(object : Callback<PagoDataCollectionItem> {
            override fun onFailure(call: Call<PagoDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(call: Call<PagoDataCollectionItem>,
                                    response: Response<PagoDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                }
                else if (response.code() == 401){
                    Toast.makeText(this@PagoActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@PagoActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        }
        )
    }




}