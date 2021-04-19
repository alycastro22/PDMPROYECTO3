package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.EmpleadoService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.EmpleadoDataCollectionItem
import kotlinx.android.synthetic.main.activity_empleados.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmpleadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleados)
        imageView6.setOnClickListener { Regresar()
        }
        val botonGetId = findViewById<ImageView>(R.id.txtBuscarEmpleado)
        botonGetId.setOnClickListener {v -> callServiceGetPerson()}
        val botonPostear = findViewById<TextView>(R.id.txtAgregarEmpleado)
        botonPostear.setOnClickListener { v-> callServicePostPerson()}
        val botonPut = findViewById<TextView>(R.id.txtGuardarEmpleado)
        botonPut.setOnClickListener { v-> callServicePutPerson()}
        val botonDelete = findViewById<TextView>(R.id.txtEliminarEmpleado)
        botonDelete.setOnClickListener { v-> callServiceDeletePerson()}

    }
    fun Regresar(){
        val cambio = Intent (this,MenuActivity::class.java)
        startActivity(cambio)

    }
    private fun callServiceDeletePerson() {
        val Id = txtIdEmpleado.text.toString().toLong()
        if (txtIdEmpleado.text.isNotEmpty()) {

            val empleadosService: EmpleadoService =
                    RestEngine.buildService().create(EmpleadoService::class.
                    java)
            var result: Call<ResponseBody> = empleadosService.deleteEmpleado(Id)

            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@EmpleadosActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@EmpleadosActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@EmpleadosActivity, "Sesion expirada", Toast.LENGTH_LONG)
                                .show()
                    } else {
                        Toast.makeText(
                                this@EmpleadosActivity,
                                "Fallo al traer el item",
                                Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })
        }

    }

    private fun callServicePutPerson() {
        val Id = txtIdEmpleado.text.toString().toLong()
        val fecha = "1995-01-15"
        val nombre=txtNombre.text.toString()
        val dni = txtIdentidad1.text.toString()
        val correo = txtCorreo.text.toString()
        val telefono = txtTelefono3.text.toString().toLong()
        val cargo = txtCargo.text.toString()
        val clave = txtClave.text.toString()

        val personInfo = EmpleadoDataCollectionItem(
                id = Id.toString().toLong(),
                dni = dni,
                nombre = nombre,
                fechaNacimiento = fecha,
                correo= correo,
                telefono = telefono,
                cargo = cargo,
                idAgencia = 1,
                clave = clave,
        )

        val retrofit = RestEngine.buildService().create(EmpleadoService::class.java)
        var result: Call<EmpleadoDataCollectionItem> = retrofit.updateEmpleado(personInfo)

        result.enqueue(object : Callback<EmpleadoDataCollectionItem> {
            override fun onFailure(call: Call<EmpleadoDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@EmpleadosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<EmpleadoDataCollectionItem>,
                                    response: Response<EmpleadoDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@EmpleadosActivity,"OK"+response.body()!!.nombre, Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@EmpleadosActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@EmpleadosActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetPerson() {
        var  Id = txtIdEmpleado.text.toString().toLong()
        val empleadoService: EmpleadoService = RestEngine.buildService().create(EmpleadoService::class.java)
        var result: Call<EmpleadoDataCollectionItem> = empleadoService.getEmpleadoById(Id)

        result.enqueue(object : Callback<EmpleadoDataCollectionItem> {
            override fun onFailure(call: Call<EmpleadoDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@EmpleadosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<EmpleadoDataCollectionItem>,
                    response: Response<EmpleadoDataCollectionItem>

            ) {
                txtNombre.setText("")
                Toast.makeText(this@EmpleadosActivity,"OK"+response.body()!!.nombre, Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun obtenertexto(){
        val Id = txtIdEmpleado.text.toString().toLong()
        val fecha = "1995-01-15"
        val nombre=txtNombre.text.toString()
        val dni = txtIdentidad1.text.toString()
        val correo = txtCorreo.text.toString()
        val telefono = txtTelefono3.text.toString()
        val cargo = txtCargo.text.toString()
        val clave = txtClave.text.toString()
        println("Id:" + Id.toString() +  "fecha:" + fecha + "nombre:" + nombre +"dni:" +
                dni+ "correo"+ correo + "telefono:" + telefono + "cargo" + cargo + "clave:" + clave)
    }

    private fun callServicePostPerson() {
        val Id = txtIdEmpleado.text.toString().toLong()
        val fecha = txtFechaNacimiento.text.toString()
        val nombre=txtNombre.text.toString()
        val dni = txtIdentidad1.text.toString()
        val correo = txtCorreo.text.toString()
        val telefono = txtTelefono3.text.toString().toLong()
        val cargo = txtCargo.text.toString()
        val clave = txtClave.text.toString()


        //val fecha = "1995-12-06"
        val personInfo = EmpleadoDataCollectionItem(
                id = null,
                dni = dni,
                nombre = nombre,
                fechaNacimiento = fecha,
                correo= correo,
                telefono = telefono,
                cargo = cargo,
                idAgencia = 1,
                clave = clave,
        )


        addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@EmpleadosActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@EmpleadosActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callServiceGetPersons() {
        val personService: EmpleadoService = RestEngine.buildService().create(EmpleadoService::class.java)
        var result: Call<List<EmpleadoDataCollectionItem>> = personService.listaemplado()

        result.enqueue(object : Callback<List<EmpleadoDataCollectionItem>> {
            override fun onFailure(call: Call<List<EmpleadoDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@EmpleadosActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<List<EmpleadoDataCollectionItem>>,
                    response: Response<List<EmpleadoDataCollectionItem>>
            ) {
                Toast.makeText(this@EmpleadosActivity,"OK"+response.body()!!.get(0).nombre, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(EmpleadoData: EmpleadoDataCollectionItem, onResult: (EmpleadoDataCollectionItem?) -> Unit){
        val retrofit = RestEngine.buildService().create(EmpleadoService::class.java)
        var result: Call<EmpleadoDataCollectionItem> = retrofit.addEmpleado(EmpleadoData)

        result.enqueue(object : Callback<EmpleadoDataCollectionItem> {
            override fun onFailure(call: Call<EmpleadoDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(call: Call<EmpleadoDataCollectionItem>,
                                    response: Response<EmpleadoDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                }
                else if (response.code() == 401){
                    Toast.makeText(this@EmpleadosActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@EmpleadosActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        }
        )
    }
}