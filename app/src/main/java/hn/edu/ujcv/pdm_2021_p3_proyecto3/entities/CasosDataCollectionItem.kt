package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities

class CasosDataCollection : ArrayList<CasosDataCollectionItem>()

data class CasosDataCollectionItem(
        val id: Long?,
        val idClientes : Long,
        val idAbogados : Long,
        val inicio : String,
        val finalizacion : String,
        val idTipoCaso : Long
)