package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities

class AbogadoDataCollection: ArrayList<AbogadoDataCollectionItem>()

data class AbogadoDataCollectionItem(
        val id:Long?,
        val nombre:String,
        val dni:Long,
        val correo:String,
        val telefono:Long,
        val fechaNacimiento:String
)
