package com.example.evaluacionformativa1

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test

// =======================================================
// PARTE 1: Modelado del Sistema de Entradas (POO)
// =======================================================

open class Entrada(
    val id: Int,
    val precio: Double
) {
    open fun mostrarDetalle() {
        println("Entrada General ID: $id | Precio: $$precio")
    }
}

class EntradaGeneral(
    id: Int,
    precio: Double
) : Entrada(id, precio) {
    override fun mostrarDetalle() {
        println("[Entrada General] ID: $id | Precio: $$precio")
    }
}

class EntradaVIP(
    id: Int,
    precio: Double,
    val beneficiosExtra: String
) : Entrada(id, precio) {
    override fun mostrarDetalle() {
        println("[Entrada VIP] ID: $id | Precio: $$precio | Beneficios: $beneficiosExtra")
    }
}

// =======================================================
// PARTE 3: Validación Asíncrona (Corrutinas)
// =======================================================

sealed class EstadoValidacion {
    object Validando : EstadoValidacion()
    data class Valida(val entrada: Entrada) : EstadoValidacion()
    data class NoValida(val mensajeError: String) : EstadoValidacion()
}

suspend fun validarEntrada(id: Int, entradas: List<Entrada>): EstadoValidacion {
    delay(2000)
    val entradaEncontrada = entradas.find { it.id == id }
    return if (entradaEncontrada != null) {
        EstadoValidacion.Valida(entradaEncontrada)
    } else {
        EstadoValidacion.NoValida("La entrada con ID $id no existe en el sistema.")
    }
}

// =======================================================
// EJECUCIÓN (PARTE 2 Y 3 INTEGRADAS EN EL TEST)
// =======================================================

class EvaluacionTest {

    @Test
    fun ejecutarEvaluacion() = runBlocking {
        println("==============================================")
        println("     SISTEMA DE GESTIÓN DE EVENTOS (KOTLIN)   ")
        println("==============================================\n")

        // PARTE 2: Creación de la lista de entradas
        val listaEntradas: List<Entrada> = listOf(
            EntradaGeneral(id = 101, precio = 15000.0),
            EntradaVIP(id = 102, precio = 35000.0, beneficiosExtra = "Barra Libre + Zona Lounge"),
            EntradaGeneral(id = 103, precio = 15000.0),
            EntradaVIP(id = 104, precio = 45000.0, beneficiosExtra = "Meet & Greet con Artista"),
            EntradaGeneral(id = 105, precio = 15000.0)
        )

        println("--- LISTA DE ENTRADAS REGISTRADAS ---")
        listaEntradas.forEach { it.mostrarDetalle() }

        // PARTE 2: Análisis de Colecciones (Cálculo total y conteo de VIPs)
        val ingresoTotal = listaEntradas.sumOf { it.precio }
        val cantidadVIP = listaEntradas.count { it is EntradaVIP }

        println("\n--- ANÁLISIS DE DATOS ---")
        println("Ingreso Total Generado: $$ingresoTotal")
        println("Cantidad de Entradas VIP vendidas: $cantidadVIP")

        // PARTE 3: Pruebas de Validación Asíncrona
        println("\n--- PRUEBA DE VALIDACIÓN ASÍNCRONA ---")

        val idValido = 102
        println("Iniciando búsqueda para ID $idValido (esperando 2 segundos)...")
        val resultadoValido = validarEntrada(idValido, listaEntradas)
        procesarEstado(resultadoValido)

        val idInvalido = 999
        println("\nIniciando búsqueda para ID $idInvalido (esperando 2 segundos)...")
        val resultadoInvalido = validarEntrada(idInvalido, listaEntradas)
        procesarEstado(resultadoInvalido)
    }

    private fun procesarEstado(estado: EstadoValidacion) {
        when (estado) {
            is EstadoValidacion.Validando -> println("Estado: Validando la información...")
            is EstadoValidacion.Valida -> {
                println("Resultado: [VÁLIDA] - Entrada confirmada.")
                estado.entrada.mostrarDetalle()
            }
            is EstadoValidacion.NoValida -> {
                println("Resultado: [NO VÁLIDA] - Error al validar.")
                println("Motivo: ${estado.mensajeError}")
            }
        }
    }
}

