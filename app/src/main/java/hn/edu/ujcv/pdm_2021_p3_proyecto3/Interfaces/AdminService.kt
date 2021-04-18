package hn.edu.ujcv.pdm_2021_p3_proyecto3.Interfaces

import hn.edu.ujcv.pdm_2021_p3_proyecto3.entities.AdminDataCollectionItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AdminService {
    @GET("admin/usuario/{usuario}")
    fun getAdminByUsurio(@Path("usuario") usuario : String): Call<AdminDataCollectionItem>
}