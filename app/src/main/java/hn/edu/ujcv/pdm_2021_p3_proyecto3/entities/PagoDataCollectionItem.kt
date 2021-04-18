package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities
class PagoDataCollection: ArrayList<AbogadoDataCollectionItem>()

data class PagoDataCollectionItem(
    val id:Long?,
    val id_TipoPago:Long?,
    val cantidad_pago:Double,
    val impuesto:Double,
    val subtotal:Double,
    val totalpagar:Double
)

