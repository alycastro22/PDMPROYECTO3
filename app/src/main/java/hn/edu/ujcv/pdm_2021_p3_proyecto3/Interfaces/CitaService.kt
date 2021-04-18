package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces


import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.CitaDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface CitaService {

    @GET("cita")
    fun listaCita(): Call<List<CitaDataCollectionItem>>
    @GET("cita/id/{id}")
    fun getCitaById(@Path("id") id:Long): Call<CitaDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("cita/addCita")
    fun addCita(@Body personData: CitaDataCollectionItem): Call<CitaDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("cita")
    fun updateCita(@Body personData: CitaDataCollectionItem): Call<CitaDataCollectionItem>
    @DELETE("cita/delete/{id}")
    fun deleteCita(@Path("id") id:Long): Call<ResponseBody>
}