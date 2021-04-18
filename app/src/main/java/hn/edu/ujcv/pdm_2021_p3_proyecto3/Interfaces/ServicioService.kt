package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces

import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.ServiciosDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ServicioService {
    @GET("servicios")
    fun listaServicio(): Call<List<ServiciosDataCollectionItem>>
    @GET("servicios/id/{id}")
    fun getServicioById(@Path("id") id:Long): Call<ServiciosDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("servicios/addServicio")
    fun addServicio(@Body personData: ServiciosDataCollectionItem): Call<ServiciosDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("servicios")
    fun updateServicio(@Body personData: ServiciosDataCollectionItem): Call<ServiciosDataCollectionItem>
    @DELETE("servicios/delete/{id}")
    fun deleteServicio (@Path("id") id:Long): Call<ResponseBody>
}