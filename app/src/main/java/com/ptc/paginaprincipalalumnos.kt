package com.ptc

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion

//Esta es la clase del login solo que me equivoqué con el nombre que le puse

class paginaprincipalalumnos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtcorreologin = findViewById<EditText>(R.id.txtcorreologin)
        val txtcontralogin = findViewById<EditText>(R.id.txtcontralogin)
        val btniniciarsesion = findViewById<Button>(R.id.btniniciarsesion)
        val btncontraolvidada = findViewById<Button>(R.id.btncontraolvidada)
        val imgvercontra = findViewById<ImageView>(R.id.idvercontra)


        btniniciarsesion.setOnClickListener{

            val pantallaprincipalalumnosBinding = Intent (this, pantallaprincipalalumnos::class.java)

            GlobalScope.launch(Dispatchers.IO){

                val objConexion = ClaseConexion().cadenaConexion()

                val validarusuario = objConexion?.prepareStatement("SELECT * FROM tbUsuarios WHERE correoElectronico = ? AND clave = ?")!!
                validarusuario.setString(1, txtcorreologin.text.toString())
                validarusuario.setString(2, txtcontralogin.text.toString())
                val resultado = validarusuario.executeQuery()
                //Si encuentra un resultado
                if (resultado.next()) {
                    startActivity(pantallaprincipalalumnosBinding)
                } else {
                    println("Usuario no encontrado, verifique las credenciales")
                }

            }
        }

        btncontraolvidada.setOnClickListener{

            val recuperarcontrasena = Intent (this, paginaprincipalalumnos::class.java)
            startActivity(recuperarcontrasena)
        }

        imgvercontra.setOnClickListener {
            if (txtcontralogin.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                txtcontralogin.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                txtcontralogin.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

        }
    }
}