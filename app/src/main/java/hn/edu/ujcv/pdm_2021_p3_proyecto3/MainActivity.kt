package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txvCambiar.setOnClickListener { ingresar() }

    }

    fun ingresar(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

    }
}