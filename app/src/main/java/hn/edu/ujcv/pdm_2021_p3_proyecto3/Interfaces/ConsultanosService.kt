package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces

import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.ConsultanosDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ConsultanosService {
    @GET("consultanos")
    fun listaConsultanos(): Call<List<ConsultanosDataCollectionItem>>
    @GET("consultanos/id/{id}")
    fun getConsultanosById(@Path("id") id:Long): Call<ConsultanosDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("cita/addCita")
    fun addConsultanos(@Body personData: ConsultanosDataCollectionItem): Call<ConsultanosDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("cita")
    fun updateConsultanos(@Body personData: ConsultanosDataCollectionItem): Call<ConsultanosDataCollectionItem>
    @DELETE("consultanos/delete/{id}")
    fun deleteConsultanos(@Path("id") id:Long): Call<ResponseBody>
}
