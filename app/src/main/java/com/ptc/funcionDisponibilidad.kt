package com.ptc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button

class funcionDisponibilidad : AppCompatActivity() {

    private val totalCupos = 20
    private var cuposOcupados = 0 // Este valor debería actualizarse según tu lógica

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comisiones_disponibles)

        val greenCircle: View = findViewById(R.id.green_circle)
        val redButton: Button = findViewById(R.id.red_button)

        updateUI(greenCircle, redButton)
    }

    private fun updateUI(greenCircle: View, redButton: View) {
        if (cuposOcupados < totalCupos) {
            greenCircle.visibility = View.VISIBLE
            redButton.visibility = View.GONE
        } else {
            greenCircle.visibility = View.GONE
            redButton.visibility = View.VISIBLE
        }
    }

    fun actualizarCuposOcupados(nuevosCuposOcupados: Int) {
        cuposOcupados = nuevosCuposOcupados
        updateUI(findViewById(R.id.green_circle), findViewById(R.id.red_button))
    }

}
