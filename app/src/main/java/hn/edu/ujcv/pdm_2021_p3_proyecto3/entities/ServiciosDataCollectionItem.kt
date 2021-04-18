package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities

class ServiciosDataCollection : ArrayList<ServiciosDataCollectionItem>()

data class ServiciosDataCollectionItem(
        val id : Long?,
        val tipoServicio : String,
        val asunto : String,
        val precio : Double
)