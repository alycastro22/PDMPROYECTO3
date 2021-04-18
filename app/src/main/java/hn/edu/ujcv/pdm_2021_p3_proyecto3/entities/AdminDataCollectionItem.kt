package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities

class AdminDataCollection: ArrayList<AdminDataCollectionItem>()

data class AdminDataCollectionItem(
        val id : Long?,
        val usuario : String,
        val contrasena : String,
)