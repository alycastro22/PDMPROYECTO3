package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces

import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.CitaDataCollectionItem
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.PagoDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface PagoService {


    @GET("pago")
    fun listaPago(): Call<List<PagoDataCollectionItem>>
    @GET("pago/id/{id}")
    fun getPagoById(@Path("id") id:Long): Call<PagoDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("pago/addPago")
    fun addPago(@Body personData: PagoDataCollectionItem): Call<PagoDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("pago")
    fun updatePago(@Body personData: PagoDataCollectionItem): Call<PagoDataCollectionItem>
    @DELETE("pago/delete/{id}")
    fun deletePago(@Path("id") id:Long): Call<ResponseBody>
}
