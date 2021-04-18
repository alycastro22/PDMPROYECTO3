package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces


import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.CasosDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface CasoService {
    @GET("casos")
    fun listaCasos(): Call<List<CasosDataCollectionItem>>
    @GET("casos/id/{id}")
    fun getCasosById(@Path("id") id:Long): Call<CasosDataCollectionItem>
    @Headers("Content-Type:application/json")
    @POST("casos/addCaso")
    fun addCaso(@Body personData: CasosDataCollectionItem): Call<CasosDataCollectionItem>
    @Headers("Content-Type:application/json")
    @PUT("casos")
    fun updateCasos(@Body personData: CasosDataCollectionItem): Call<CasosDataCollectionItem>
    @DELETE("casos/delete/(id)")
    fun deleteCasos(@Path("id") id:Long): Call<ResponseBody>
}