package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces


import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.AgenciaDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface AgenciaService {
    @GET("agencia")
    fun listaAgencia(): Call<List<AgenciaDataCollectionItem>>
    @GET("agencia/id/{id}")
    fun getAgenciasById(@Path("id") id:Long): Call<AgenciaDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("agencia/addAgencia")
    fun addAgencia(@Body personData: AgenciaDataCollectionItem): Call<AgenciaDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("agencia")
    fun updateAgencia(@Body personData: AgenciaDataCollectionItem): Call<AgenciaDataCollectionItem>
    @DELETE("agencia/delete/(id)")
    fun deleteAgencia(@Path("id") id:Long): Call<ResponseBody>
}