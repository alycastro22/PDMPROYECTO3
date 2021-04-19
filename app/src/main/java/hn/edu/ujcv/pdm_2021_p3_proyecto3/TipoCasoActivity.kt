package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces.TipoCasoService
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.TipoCasoDataCollectionItem
import kotlinx.android.synthetic.main.activity_tipocaso.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TipoCasoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipocaso)
        imageView7.setOnClickListener { Regresar()

            val botonPostear = findViewById<TextView>(R.id.txtAgregarTC)
            botonPostear.setOnClickListener { v-> callServicePostPerson()}
            val botonPut = findViewById<TextView>(R.id.txtGuardarTC)
            botonPut.setOnClickListener { v-> callServicePutPerson()}
            val botonDelete = findViewById<TextView>(R.id.txtEliminarTC)
            botonDelete.setOnClickListener { v-> callServiceDeletePerson()}

        }


    }
    fun Regresar(){
        val cambio = Intent (this,MenuActivity::class.java)
        startActivity(cambio)

    }


    private fun callServiceDeletePerson() {
        val id = txtId9.text.toString().toLong()
        if (txtId9.text.isNotEmpty()) {

            val tipoCasoService: TipoCasoService = RestEngine.buildService().create(TipoCasoService::class.java)
            var result: Call<ResponseBody> = tipoCasoService.deleteTipoCaso(id)
            result.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@TipoCasoActivity, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@TipoCasoActivity, "DELETE", Toast.LENGTH_LONG).show()
                    } else if (response.code() == 401) {
                        Toast.makeText(this@TipoCasoActivity, "Sesion expirada", Toast.LENGTH_LONG)
                                .show()
                    } else {
                        Toast.makeText(
                                this@TipoCasoActivity,
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
        var id = txtId9.text.toString().toLong()
        var nombre=txtNombre5.text.toString()
        var descripcion = txtDescripcion4.text.toString()
        var precio =txtPrecio1.text.toString().toDouble()

        println("id:" + id.toString().toLong() +"nombre:" + nombre.toString() + "descripcion" + descripcion.toString() +"precio:" +
                precio.toString().toDouble() )

        val personInfo = TipoCasoDataCollectionItem(  id = id,
                nombre = nombre,
                descripcion=descripcion,
                precio= precio,
        )

        val retrofit = RestEngine.buildService().create(TipoCasoService::class.java)
        var result: Call<TipoCasoDataCollectionItem> = retrofit.updateTipoCaso(personInfo)

        result.enqueue(object : Callback<TipoCasoDataCollectionItem> {
            override fun onFailure(call: Call<TipoCasoDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@TipoCasoActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<TipoCasoDataCollectionItem>,
                                    response: Response<TipoCasoDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val updatedPerson = response.body()!!
                    Toast.makeText(this@TipoCasoActivity,"OK"+response.body()!!.nombre, Toast.LENGTH_LONG).show()
                }
                else if (response.code() == 401){
                    Toast.makeText(this@TipoCasoActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@TipoCasoActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun callServiceGetPerson() {
        var  id = txtId9.text.toString().toLong()
        val tipoCasoService: TipoCasoService = RestEngine.buildService().create(TipoCasoService::class.java)
        var result: Call<TipoCasoDataCollectionItem> = tipoCasoService.getTipoCasoById(id)

        result.enqueue(object : Callback<TipoCasoDataCollectionItem> {
            override fun onFailure(call: Call<TipoCasoDataCollectionItem>, t: Throwable) {
                Toast.makeText(this@TipoCasoActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<TipoCasoDataCollectionItem>,
                    response: Response<TipoCasoDataCollectionItem>


            ) {
                txtNombre5.setText("")
                Toast.makeText(this@TipoCasoActivity,"OK"+response.body()!!.nombre, Toast.LENGTH_LONG).show()
            }
        })
    }
    /*private fun obtenertexto(){
        var id = txtId9.text.toString().toLong()
        var nombre=txtNombre5.text.toString()
        var descripcion = txtDescripcion4.text.toString()
        var precio =txtPrecio1.text.toString().toDouble()

        println("id:" + id.toString().toLong() +"nombre:" + nombre.toString() + "descripcion" + descripcion.toString() +"precio:" +
                precio.toString().toDouble() )
    }*/

    private fun callServicePostPerson() {
        var id = txtId9.text.toString().toLong()
        var nombre=txtNombre5.text.toString()
        var descripcion = txtDescripcion4.text.toString()
        var precio =txtPrecio1.text.toString().toDouble()


        //val fecha = "1995-12-06"
        val personInfo = TipoCasoDataCollectionItem(  id = id,
                nombre = nombre,
                descripcion=descripcion,
                precio= precio,
        )

        addPerson(personInfo) {
            if (it?.id != null) {
                Toast.makeText(this@TipoCasoActivity,"OK"+it?.id, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@TipoCasoActivity,"Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callServiceGetPersons() {
        val personService: TipoCasoService = RestEngine.buildService().create(TipoCasoService::class.java)
        var result: Call<List<TipoCasoDataCollectionItem>> = personService.listaTipoCaso()

        result.enqueue(object : Callback<List<TipoCasoDataCollectionItem>> {
            override fun onFailure(call: Call<List<TipoCasoDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@TipoCasoActivity,"Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                    call: Call<List<TipoCasoDataCollectionItem>>,
                    response: Response<List<TipoCasoDataCollectionItem>>
            ) {
                Toast.makeText(this@TipoCasoActivity,"OK"+response.body()!!.get(0).nombre, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addPerson(tipoCasoData: TipoCasoDataCollectionItem, onResult: (TipoCasoDataCollectionItem?) -> Unit){
        val retrofit = RestEngine.buildService().create(TipoCasoService::class.java)
        var result: Call<TipoCasoDataCollectionItem> = retrofit.addTipoCaso(tipoCasoData)

        result.enqueue(object : Callback<TipoCasoDataCollectionItem> {
            override fun onFailure(call: Call<TipoCasoDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(call: Call<TipoCasoDataCollectionItem>,
                                    response: Response<TipoCasoDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedPerson = response.body()!!
                    onResult(addedPerson)
                }
                else if (response.code() == 401){
                    Toast.makeText(this@TipoCasoActivity,"Sesion expirada", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@TipoCasoActivity,"Fallo al traer el item", Toast.LENGTH_LONG).show()
                }
            }

        }
        )
    }


}
