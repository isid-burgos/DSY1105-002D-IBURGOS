package com.example.variablesyfunciones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.variablesyfunciones.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // (1) Declaración de variables requeridas por la guía
    val nombreUsuario: String = "Ana"
    var edadUsuario: Int = 20
    var promedioNotas: Double = 6.5
    val esMayorDeEdad: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // (3) Invocación de funciones y conexión con la interfaz
        val mensajeSaludo = crearSaludo(nombreUsuario, edadUsuario)
        val esMayor = calcularMayoriaEdad(edadUsuario)

        val mensajeFinal = "$mensajeSaludo ¿Es mayor de edad? $esMayor"

        mostrarResultado(mensajeFinal)
    }

    // (2) Declaración de funciones requeridas por la guía[cite: 1]

    fun crearSaludo(nombre: String, edad: Int): String {
        return "Hola $nombre, tienes $edad años."
    }

    fun calcularMayoriaEdad(edad: Int): Boolean {
        return edad >= 18
    }

    fun mostrarResultado(mensaje: String) {
        binding.textView.text = mensaje
    }
}