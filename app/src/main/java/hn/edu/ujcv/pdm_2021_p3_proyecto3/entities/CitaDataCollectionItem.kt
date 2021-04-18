package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities

class CitaDataCollection: ArrayList<CitaDataCollectionItem>()

data class CitaDataCollectionItem(
        val idcita  :Long?,
        val fecha:String,
        val descripcion:String,
        val precio:Double,
        val idcaso :Long,


)
