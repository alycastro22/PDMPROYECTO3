package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.AbogadoService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.FacturaService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.AbogadoDataCollectionItem
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.FacturaDataCollectionItem
import kotlinx.android.synthetic.main.activity_abogados.*
import kotlinx.android.synthetic.main.activity_empleados.*
import kotlinx.android.synthetic.main.activity_factura.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class FacturaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factura)
        imageView9.setOnClickListener { Regresar()

            val botonPostear = findViewById<ImageView>(R.id.txtAgregarAbog)
            botonPostear.setOnClickListener { v-> callServicePostPerson()}
            val botonPut = findViewById<TextView>(R.id.txtGuardar)
            botonPut.setOnClickListener { v-> callServicePutPerson()}
            val botonDelete = findViewById<TextView>(R.id.txtEliminar)
            botonDelete.setOnClickListener { v-> callServiceDeletePerson()}
            imageView9.setOnClickListener { Regresar()
            }
        }


    }
    fun Regresar(){
        val cambio = Intent (this,MenuActivity::class.java)
        startActivity(cambio)

    }


    private fun callServiceDeletePerson() {
        val id = txtId8.text.toString().toLong()
        if (txtId8.text.isNotEmpty()) {

            val FacturaService: FacturaService =
                    RestEngine.buildService().create(FacturaService::class.
                    java)
            var result: Call<ResponseBody> = FacturaService.deleteFactura(id)

            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@FacturaActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@FacturaActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@FacturaActivity, "Sesion expirada", Toast.LENGTH_LONG)
                                .show()
                    } else {
                        Toast.makeText(
                                this@FacturaActivity,
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
        var id = txtId8.text.toString().toLong()
        var fecha = txtFechaFactura.text.toString()
        var idempleado= txtEmpleado1.toString().toLong()
        var idcaso = txtIdCaso1.text.toString().toLong()
        var idpago = txtIdPago.text.toString().toLong()
        var idservicios = txtIdServicio.text.toString().toLong()
       println("id:" + id.toString().toLong() +"fecha:" + fecha.toString() + "idempleado:" + idempleado.toString() +"idcaso:" +
                idcaso.toString()+ "idpago"+idpago.toString()+ "idservicios:" + idservicios.toString() )

        val personInfo = FacturaDataCollectionItem(  id = id,
                fecha = fecha,
                idempleado=idempleado,
                idcaso= idcaso,
                idpago= idpago,
                idservicios=idservicios,

                )

        val retrofit = RestEngine.buildService().create(FacturaService::class.java)
        var result: Call<FacturaDataCollectionItem> = retrofit.updateFactura(personInfo)

        result.enqueue(object : Callback<FacturaDataCollectionItem> {
            override fun onFailure(call: Call<FacturaDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@FacturaActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<FacturaDataCollectionItem>,
                                    response: Response<FacturaDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@FacturaActivity,"OK"+response.body()!!.fecha, Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@FacturaActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@FacturaActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetPerson() {
        var  id = txtId8.text.toString().toLong()
        val FacturaService: FacturaService = RestEngine.buildService().create(FacturaService::class.java)
        var result: Call<FacturaDataCollectionItem> = FacturaService.getFacturaById(id)

        result.enqueue(object : Callback<FacturaDataCollectionItem> {
            override fun onFailure(call: Call<FacturaDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@FacturaActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<FacturaDataCollectionItem>,
                    response: Response<FacturaDataCollectionItem>


            ) {
                txtFechaFactura.setText("")
                Toast.makeText(this@FacturaActivity,"OK"+response.body()!!.fecha, Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun obtenertexto(){
        var id = txtId8.text.toString().toLong()
        var fecha = txtFechaFactura.text.toString()
        var idempleado= txtEmpleado1.text.toString().toLong()
        var idcaso=txtIdCaso1.toString().toLong()
        var idpago = txtIdPago.text.toString()
        var idservicios = txtTelefono.text.toString().toLong()
        println("id:" + id.toString().toLong() +"fecha:" + fecha.toString() + "idempleado:" + idempleado.toString() +"idcaso:" +
                idcaso.toString()+ "idpago"+idpago.toString()+ "idservicios:" + idservicios.toString() )
    }

    private fun callServicePostPerson() {
        var id = txtId8.text.toString().toLong()
        var fecha = txtFechaFactura.text.toString()
        var idempleado= txtEmpleado1.text.toString().toLong()
        var idcaso=txtIdCaso1.toString().toLong()
        var idpago = txtIdPago.text.toString()
        var idservicios = txtTelefono.text.toString().toLong()


        //val fecha = "1995-12-06"
        val personInfo = FacturaDataCollectionItem(  id = null,

                fecha = fecha,
                idempleado=idempleado,
                idcaso= idcaso,
                idpago= idpago.toLong(),
                idservicios=idservicios,



        )


        addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@FacturaActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@FacturaActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callServiceGetPersons() {
        val personService: FacturaService = RestEngine.buildService().create(FacturaService::class.java)
        var result: Call<List<FacturaDataCollectionItem>> = personService.listaFactura()

        result.enqueue(object : Callback<List<FacturaDataCollectionItem>> {
            override fun onFailure(call: Call<List<FacturaDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@FacturaActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<List<FacturaDataCollectionItem>>,
                    response: Response<List<FacturaDataCollectionItem>>
            ) {
                Toast.makeText(this@FacturaActivity,"OK"+response.body()!!.get(0).fecha, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(FacturaData: FacturaDataCollectionItem, onResult: (FacturaDataCollectionItem?) -> Unit){
        val retrofit = RestEngine.buildService().create(FacturaService::class.java)
        var result: Call<FacturaDataCollectionItem> = retrofit.addFactura(FacturaData)

        result.enqueue(object : Callback<FacturaDataCollectionItem> {
            override fun onFailure(call: Call<FacturaDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(call: Call<FacturaDataCollectionItem>,
                                    response: Response<FacturaDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                }
                else if (response.code() == 401){
                    Toast.makeText(this@FacturaActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@FacturaActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        }
        )
    }


}