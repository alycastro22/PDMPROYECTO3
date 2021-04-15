package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_abogados.*

class AbogadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abogados)

        imageView2.setOnClickListener { Regresar()
        }


       }
    fun Regresar(){
        val cambio = Intent (this,MenuActivity::class.java)
        startActivity(cambio)

    }
}