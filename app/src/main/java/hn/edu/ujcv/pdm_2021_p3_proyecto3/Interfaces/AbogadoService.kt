package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces


import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.AbogadoDataCollectionItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
interface AbogadoService {
        @GET("abogados")
        fun listabogado(): Call<List<AbogadoDataCollectionItem>>
        @GET("abogados/id/{id}")
        fun getAbogadoById(@Path("id") id:Long): Call<AbogadoDataCollectionItem>
        @Headers("Content-Type:application/json")
        @POST("abogados/addAbogado")
        fun addAbogado(@Body personData: AbogadoDataCollectionItem): Call<AbogadoDataCollectionItem>
        @Headers("Content-Type:application/json")
        @PUT("abogados")
        fun updateAbogado(@Body personData: AbogadoDataCollectionItem): Call<AbogadoDataCollectionItem>
        @DELETE("abogados/delete/{id}")
        fun deleteAbogado(@Path("id") id:Long): Call<ResponseBody>

}