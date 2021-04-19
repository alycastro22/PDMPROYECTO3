package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities

class ConsultanosDataCollection: ArrayList<ConsultanosDataCollectionItem>()

 data class ConsultanosDataCollectionItem(
     val id : Long?,
     val idcliente : Long,
     val idabogado : Long,
     val fecha : String,
     val desscripcion : String

)
