package com.ptc

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class correoconfirmacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_correoconfirmacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgregresarrecuccontra = findViewById<ImageView>(R.id.imgregresarrecucontra)
        val etxtcodigoconfirmacion = findViewById<EditText>(R.id.etxtcodigoconfirmacion)

        imgregresarrecuccontra.setOnClickListener {
            val pantallarecuperarcontrasena = Intent (this, recuperarcontrasena::class.java)
            startActivity(pantallarecuperarcontrasena)
        }
    }
}