package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities

class EmpleadoDataCollection : ArrayList<EmpleadoDataCollectionItem>()

data class EmpleadoDataCollectionItem(
        val id : Long?,
        val nombre : String,
        val dni : String,
        val correo : String,
        val telefono : Long,
        val cargo : String,
        val idAgencia : Long,
        val fechaNacimiento : String
)

