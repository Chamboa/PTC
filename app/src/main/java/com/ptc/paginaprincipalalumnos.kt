package com.ptc

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import oracle.security.crypto.core.MessageDigest

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

        /*fun hashSHA256 (contraencriptada: String): String {
            val bytes = MessageDigest.getInstance("SHA-256").digest(contraencriptada.toByteArray())
            return bytes.joinToString("") { "%02x".format(it)}
        }*/

        val txtcorreologin = findViewById<EditText>(R.id.txtcorreologin)
        val txtcontralogin = findViewById<EditText>(R.id.txtcontralogin)
        val btniniciarsesion = findViewById<Button>(R.id.btniniciarsesion)
        val btncontraolvidada = findViewById<Button>(R.id.btncontraolvidada)
        val imgvercontra = findViewById<ImageView>(R.id.idvercontra)
        //val contraencriptada = hashSHA256(txtcontralogin.text.toString())

        btniniciarsesion.setOnClickListener {
            val pantallaprincipalalumnosBinding = Intent(this, pantallaprincipalalumnos::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val objConexion = ClaseConexion().cadenaConexion()

                    //val contraencriptada = hashSHA256(txtcontralogin.text.toString())


                    val validarusuario = objConexion?.prepareStatement("SELECT * FROM Usuarios WHERE correo_usuario = ? AND contrasena = ?")
                    validarusuario?.setString(1, txtcorreologin.text.toString())
                    validarusuario?.setString(2, txtcontralogin.text.toString())

                    val resultado = validarusuario?.executeQuery()

                    // Cambio al hilo principal para operaciones de UI
                    withContext(Dispatchers.Main) {
                        if (resultado?.next() == true) {
                            startActivity(pantallaprincipalalumnosBinding)
                        } else {
                            Toast.makeText(this@paginaprincipalalumnos, "Usuario no encontrado, verifique las credenciales", Toast.LENGTH_LONG).show()
                        }
                    }
                } catch (e: Exception) {
                    // Manejo de excepciones en el hilo principal
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@paginaprincipalalumnos, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }


        btncontraolvidada.setOnClickListener{

            val recuperarcontrasena = Intent (this, recuperarcontrasena::class.java)
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