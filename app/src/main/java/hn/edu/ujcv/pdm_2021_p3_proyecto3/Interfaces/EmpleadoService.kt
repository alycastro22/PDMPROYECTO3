package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces

import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.EmpleadoDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface EmpleadoService {
    @GET("empleados")
    fun listaemplado(): Call<List<EmpleadoDataCollectionItem>>
    @GET("empleados/id/{id}")
    fun getEmpleadoById(@Path("id") id:Long): Call<EmpleadoDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("empleados/addEmpleado")
    fun addEmpleado(@Body personData: EmpleadoDataCollectionItem): Call<EmpleadoDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("empleados")
    fun updateEmpleado(@Body personData: EmpleadoDataCollectionItem): Call<EmpleadoDataCollectionItem>
    @DELETE("empleados/delete/{id}")
    fun deleteEmpleado (@Path("id") id:Long): Call<ResponseBody>
}