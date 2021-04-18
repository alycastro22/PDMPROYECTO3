package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces


import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.TipoCasoDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface TipoCasoService {

    @GET("tipocaso")
    fun listaTipoCaso(): Call<List<TipoCasoDataCollectionItem>>
    @GET("tipocaso/id/{id}")
    fun getTipoCasoById(@Path("id") id:Long): Call<TipoCasoDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("tipocaso/addTipoCaso")
    fun addTipoCaso(@Body personData: TipoCasoDataCollectionItem): Call<TipoCasoDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("tipocaso")
    fun updateTipoCaso(@Body personData: TipoCasoDataCollectionItem): Call<TipoCasoDataCollectionItem>
    @DELETE("tipocaso/delete/(id)")
    fun deleteTipoCaso(@Path("id") id:Long): Call<ResponseBody>
}
