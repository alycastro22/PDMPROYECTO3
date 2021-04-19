package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces

import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.FacturaDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface FacturaService {
    @GET("factura")
    fun listaFactura(): Call<List<FacturaDataCollectionItem>>
    @GET("factura/id/{id}")
    fun getFacturaById(@Path("id") id:Long): Call<FacturaDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("factura/addFactura")
    fun addFactura(@Body personData: FacturaDataCollectionItem): Call<FacturaDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("factura")
    fun updateFactura(@Body personData: FacturaDataCollectionItem): Call<FacturaDataCollectionItem>
    @DELETE("factura/delete/{id}")
    fun deleteFactura(@Path("id") id:Long): Call<ResponseBody>
}