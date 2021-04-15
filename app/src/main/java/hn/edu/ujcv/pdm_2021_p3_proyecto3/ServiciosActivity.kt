package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_abogados.*
import kotlinx.android.synthetic.main.activity_servicios.*

class ServiciosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)
        imageView12.setOnClickListener { Regresar()
        }


    }
    fun Regresar(){
        val cambio = Intent (this,MenuActivity::class.java)
        startActivity(cambio)

    }
}