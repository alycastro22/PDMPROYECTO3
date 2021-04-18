package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces


import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.ClienteDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ClienteService {
    @GET("cliente")
    fun listacliente(): Call<List<ClienteDataCollectionItem>>
    @GET("cliente/id/{id}")
    fun getClienteById(@Path("id") id:Long): Call<ClienteDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("cliente/addCliente")
    fun addCliente(@Body personData: ClienteDataCollectionItem): Call<ClienteDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("cliente")
    fun updateCliente(@Body personData: ClienteDataCollectionItem): Call<ClienteDataCollectionItem>
    @DELETE("cliente/delete/{id}")
    fun deleteCliente (@Path("id") id:Long): Call<ResponseBody>


}