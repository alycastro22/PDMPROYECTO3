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

        val card2: View = findViewById(R.id.card_clientes)
        card2.setOnClickListener(this)

        val card3: View = findViewById(R.id.card_abogados)
        card3.setOnClickListener(this)

        val card4: View = findViewById(R.id.card_empleados)
        card4.setOnClickListener(this)

        val card5: View = findViewById(R.id.card_casos)
        card5.setOnClickListener(this)

        val card6: View = findViewById(R.id.card_tiposdecasos)
        card6.setOnClickListener(this)

        val card7: View = findViewById(R.id.card_agencia)
        card7.setOnClickListener(this)

        val card8: View = findViewById(R.id.card_citas)
        card8.setOnClickListener(this)

        val card9: View = findViewById(R.id.card_consultas)
        card9.setOnClickListener(this)

        val card10: View = findViewById(R.id.card_pago)
        card10.setOnClickListener(this)

        val card11: View = findViewById(R.id.card_tiposdepago)
        card11.setOnClickListener(this)

        val card12: View = findViewById(R.id.card_factura)
        card12.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.card_servicios -> {
                val intent = Intent(this, ServiciosActivity::class.java)
                startActivity(intent)
            }

            R.id.card_clientes -> {
                val intent = Intent(this, ClienteActivity::class.java)
                startActivity(intent)
                }

            R.id.card_abogados -> {
                val intent = Intent(this,AbogadosActivity::class.java)
                startActivity(intent)
            }

            R.id.card_empleados -> {
                val intent = Intent(this,EmpleadosActivity::class.java)
                startActivity(intent)
            }

            R.id.card_casos -> {
                val intent = Intent(this,CasosActivity::class.java)
                startActivity(intent)
            }

            R.id.card_tiposdecasos -> {
                val intent = Intent(this,TipoCasoActivity::class.java)
                startActivity(intent)
            }

            R.id.card_agencia -> {
                val intent = Intent(this,AgenciaActivity::class.java)
                startActivity(intent)
            }

            R.id.card_citas -> {
                val intent = Intent(this,CitaActivity::class.java)
                startActivity(intent)
            }
            R.id.card_consultas -> {
                val intent = Intent(this,ConsultanosActivity::class.java)
                startActivity(intent)
            }
            R.id.card_pago -> {
                val intent = Intent(this,PagoActivity::class.java)
                startActivity(intent)
            }
            R.id.card_tiposdepago -> {
                val intent = Intent(this,TipoCasoActivity::class.java)
                startActivity(intent)
            }
            R.id.card_factura-> {
                val intent = Intent(this,FacturaActivity::class.java)
                startActivity(intent)
            }

            }
        }
    }


