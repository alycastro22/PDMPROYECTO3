package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities


class TipoCasoDataCollection : ArrayList<TipoCasoDataCollectionItem>()

data class TipoCasoDataCollectionItem(
        val id : Long?,
        val nombre : String,
        val descripcion: String,
        val precio : Double,

)
