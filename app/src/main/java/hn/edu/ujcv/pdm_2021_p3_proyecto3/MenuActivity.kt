package hn.edu.ujcv.pdm_2021_p3_proyecto3

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MenuActivity: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val card1: View = findViewById(R.id.card_servicios)
        card1.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.card_servicios -> {
                val intent = Intent(this,ServiciosActivity::class.java)
                startActivity(intent)
            }
        }
    }
}