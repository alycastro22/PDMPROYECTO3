package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.ClienteService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.ClienteDataCollectionItem
import kotlinx.android.synthetic.main.activity_cliente.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)
        imageView14.setOnClickListener { Regresar()
        }

        val botonPostear = findViewById<TextView>(R.id.txtAgregarCliente)
        botonPostear.setOnClickListener { v-> callServicePostPerson()}
        val botonPut = findViewById<TextView>(R.id.txtGuardarCliente)
        botonPut.setOnClickListener { v-> callServicePutPerson()}
        val botonDelete = findViewById<TextView>(R.id.txtEliminarCliente)
        botonDelete.setOnClickListener { v-> callServiceDeletePerson()}
        val botonBuscar = findViewById<ImageView>(R.id.txtLupaCliente)
        botonBuscar.setOnClickListener{v -> callServiceGetPerson() }
        txtLimpiarCliente.setOnClickListener { limpiar() }

    }
    fun Regresar(){
        val cambio = Intent (this,MenuActivity::class.java)
        startActivity(cambio)

    }
    private fun callServiceDeletePerson() {
        val Id = txtId12.text.toString().toLong()
        if (txtId12.text.isNotEmpty()) {

            val clienteService: ClienteService =
                RestEngine.buildService().create(
                    ClienteService::class.
                java)
            var result: Call<ResponseBody> = clienteService.deleteCliente(Id)

            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@ClienteActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ClienteActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@ClienteActivity, "Sesion expirada", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(
                            this@ClienteActivity,
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
        var Id = txtId12.text.toString().toLong()
        var fecha = txtFechaNacimiento2.text.toString()
        var nombre = txtNombre10.text.toString()
        var correo = txtCorreo3.text.toString()
        var telefono = txtTelefono2.text.toString().toLong()

        println("Id:" + Id.toString() +  "fecha:" + fecha + "nombre:" + nombre + "correo"+ correo + "telefono:" + telefono.toString() )


        val personInfo = ClienteDataCollectionItem(  id = Id,
            nombre = nombre,
            correo= correo,
            telefono = telefono,
            fechaNacimiento = fecha

        )

        val retrofit = RestEngine.buildService().create(ClienteService::class.java)
        var result: Call<ClienteDataCollectionItem> = retrofit.updateCliente(personInfo)

        result.enqueue(object : Callback<ClienteDataCollectionItem> {
            override fun onFailure(call: Call<ClienteDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@ClienteActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ClienteDataCollectionItem>,
                                    response: Response<ClienteDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@ClienteActivity,"OK"+response.body()!!.nombre, Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@ClienteActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@ClienteActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetPerson() {
        val id = txtId12.text.toString().toLong()
        val clienteService: ClienteService = RestEngine.buildService().create(ClienteService::class.java)
        var result: Call<ClienteDataCollectionItem> = clienteService.getClienteById(id)

        result.enqueue(object : Callback<ClienteDataCollectionItem> {
            override fun onFailure(call: Call<ClienteDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@ClienteActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ClienteDataCollectionItem>,
                response: Response<ClienteDataCollectionItem>


            ) {
                txtNombre10.setText("")
                Toast.makeText(this@ClienteActivity,"OK"+response.body()!!.nombre, Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun callServicePostPerson() {
        //var id = txtId12.text.toString().toLong()
        var fecha = txtFechaNacimiento2.text.toString()
        var nombre = txtNombre10.text.toString()
        var correo = txtCorreo3.text.toString()
        var telefono = txtTelefono2.text.toString().toLong()
        println(   "fecha:" + fecha + "nombre:" + nombre + "correo"+ correo + "telefono:" + telefono.toString() )
        //val fecha = "1995-12-06"

        val personInfo = ClienteDataCollectionItem(  id = null,
                nombre = nombre,
                correo= correo,
                telefono = telefono,
                fechaNacimiento = fecha

        )


        addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@ClienteActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@ClienteActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callServiceGetPersons() {
        val personService: ClienteService = RestEngine.buildService().create(ClienteService::class.java)
        var result: Call<List<ClienteDataCollectionItem>> = personService.listacliente()

        result.enqueue(object : Callback<List<ClienteDataCollectionItem>> {
            override fun onFailure(call: Call<List<ClienteDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@ClienteActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<ClienteDataCollectionItem>>,
                response: Response<List<ClienteDataCollectionItem>>
            ) {
                Toast.makeText(this@ClienteActivity,"OK"+response.body()!!.get(0).nombre, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(ClienteData: ClienteDataCollectionItem, onResult: (ClienteDataCollectionItem?) -> Unit){
        val retrofit = RestEngine.buildService().create(ClienteService::class.java)
        var result: Call<ClienteDataCollectionItem> = retrofit.addCliente(ClienteData)

        result.enqueue(object : Callback<ClienteDataCollectionItem> {
            override fun onFailure(call: Call<ClienteDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(call: Call<ClienteDataCollectionItem>,
                                    response: Response<ClienteDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                }
                else if (response.code() == 401){
                    Toast.makeText(this@ClienteActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@ClienteActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        }
        )
    }
    private fun limpiar(){
        txtId12.text.clear()
        txtFechaNacimiento2.text.clear()
        txtNombre10.text.clear()
        txtCorreo3.text.clear()
        txtTelefono2.text.clear()

    }

}