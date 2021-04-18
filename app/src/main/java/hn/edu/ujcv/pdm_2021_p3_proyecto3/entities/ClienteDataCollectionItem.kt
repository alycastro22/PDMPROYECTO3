package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities

class ClienteDataCollection: ArrayList<ClienteDataCollectionItem>()

data class ClienteDataCollectionItem(
        val id:Long?,
        val nombre:String,
        val correo:String,
        val fechaNacimiento:String,
        val telefono:Long

)