package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces


import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.ClienteDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ClienteService {
    @GET("clientes")
    fun listacliente(): Call<List<ClienteDataCollectionItem>>
    @GET("clientes/id/{id}")
    fun getClienteById(@Path("id") id:Long): Call<ClienteDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("clientes/addCliente")
    fun addCliente(@Body personData: ClienteDataCollectionItem): Call<ClienteDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("clientes")
    fun updateCliente(@Body personData: ClienteDataCollectionItem): Call<ClienteDataCollectionItem>
    @DELETE("clientes/delete/{id}")
    fun deleteCliente (@Path("id") id:Long): Call<ResponseBody>


}