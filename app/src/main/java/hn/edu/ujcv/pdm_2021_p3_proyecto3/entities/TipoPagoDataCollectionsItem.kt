package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities
class TipoPagoDataCollection: ArrayList<TipoPagoDataCollectionItem>()

data class TipoPagoDataCollectionItem(
    val id:Long?,
    val descripcion:String,

    )