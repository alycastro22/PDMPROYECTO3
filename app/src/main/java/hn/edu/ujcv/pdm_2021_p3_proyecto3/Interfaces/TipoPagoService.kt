package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces

import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.PagoDataCollectionItem
import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.TipoPagoDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface TipoPagoService {
    @GET("tipopago")
    fun listaTipoPago(): Call<List<TipoPagoDataCollectionItem>>
    @GET("tipopago/id/{id}")
    fun getTipoPagoById(@Path("id") id:Long): Call<TipoPagoDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("tipopago/addTipoPago")
    fun addTipoPago(@Body personData: TipoPagoDataCollectionItem): Call<TipoPagoDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("tipopago")
    fun updateTipoPago(@Body personData: TipoPagoDataCollectionItem): Call<TipoPagoDataCollectionItem>
    @DELETE("tipopago/delete/{id}")
    fun deleteTipoPago(@Path("id") id:Long): Call<ResponseBody>
}



