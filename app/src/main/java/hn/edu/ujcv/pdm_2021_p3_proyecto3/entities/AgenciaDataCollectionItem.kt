package hn.edu.ujcv.pdm_2021_p3_proyecto3.entities

class AgenciaDataCollection : ArrayList<AgenciaDataCollectionItem>()

data class AgenciaDataCollectionItem(
        val id : Long?,
        val nombre : String,
        val direccion : String,
        val telefono : Long
)


