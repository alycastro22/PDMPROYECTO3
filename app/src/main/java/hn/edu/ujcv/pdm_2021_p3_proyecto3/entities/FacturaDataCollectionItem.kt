package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities


class FacturaDataCollection : ArrayList<FacturaDataCollectionItem>()

data class FacturaDataCollectionItem(
        val id : Long?,
        val fecha: String,
        val idempleado : Long,
        val idcaso : Long,
        val idpago : Long,
        val idservicios : Long,
)
