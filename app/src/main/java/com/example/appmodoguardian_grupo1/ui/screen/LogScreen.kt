package com.example.appmodoguardian_grupo1.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.math.cos
import kotlin.math.sin

// Estructura de la pantalla de bienvenida/inicio (LogScreen)
@Composable
fun LogScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F2)) // Fondo crema claro
            .padding(horizontal = 24.dp, vertical = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Sección Superior: Logo Vectorial Centrado
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(200.dp)
            ) {
                HexagonLogoMinimal(
                    modifier = Modifier.size(180.dp)
                )
            }

            // Sección Central: Título + Mensaje de Bienvenida
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Modo Guardian",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "¡Bienvenido! Tu plataforma de seguridad y protección personal. Regístrate o inicia sesión para acceder a tu panel.",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF4B5563),
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Indicador de puntos (Page dots)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF111827))
                    )
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFD1D5DB))
                    )
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFD1D5DB))
                    )
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFD1D5DB))
                    )
                }
            }

            // Sección Inferior: Botones de Acción
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Botón Principal: Ir a Registro
                Button(
                    onClick = { navController.navigate("registro") },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF132A44) // Azul marino
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text(
                        text = "Registro",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }

                // Botón Secundario: Iniciar Sesión
                OutlinedButton(
                    onClick = { navController.navigate("login") },
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.5.dp, Color(0xFF111827)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFF111827)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text(
                        text = "Iniciar Sesión",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF111827)
                    )
                }
            }
        }
    }
}

// Logo Hexagonal Vectorial
@Composable
fun HexagonLogoMinimal(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val center = Offset(width / 2f, height / 2f)
        val outerR = minOf(width, height) / 2f * 0.92f
        val thickness = outerR * 0.28f
        val innerR = outerR - thickness

        val angles = listOf(-90f, -30f, 30f, 90f, 150f, 210f)

        fun getOuterPoint(index: Int): Offset {
            val rad = Math.toRadians(angles[(index + 6) % 6].toDouble())
            return Offset(
                center.x + (outerR * cos(rad)).toFloat(),
                center.y + (outerR * sin(rad)).toFloat()
            )
        }

        fun getInnerPoint(index: Int): Offset {
            val rad = Math.toRadians(angles[(index + 6) % 6].toDouble())
            return Offset(
                center.x + (innerR * cos(rad)).toFloat(),
                center.y + (innerR * sin(rad)).toFloat()
            )
        }

        val darkBlue = Color(0xFF132A44)
        val brightBlue = Color(0xFF1D70B8)
        val yellowGold = Color(0xFFE5A100)

        // Trazo 1: Arriba-Derecha (Dorado)
        val path1 = Path().apply {
            moveTo(getOuterPoint(0).x, getOuterPoint(0).y)
            lineTo(getOuterPoint(1).x, getOuterPoint(1).y)
            lineTo(getInnerPoint(2).x, getInnerPoint(2).y)
            lineTo(getInnerPoint(1).x, getInnerPoint(1).y)
            close()
        }
        drawPath(path1, color = yellowGold)

        // Trazo 2: Derecha (Azul Oscuro)
        val path2 = Path().apply {
            moveTo(getOuterPoint(1).x, getOuterPoint(1).y)
            lineTo(getOuterPoint(2).x, getOuterPoint(2).y)
            lineTo(getInnerPoint(3).x, getInnerPoint(3).y)
            lineTo(getInnerPoint(2).x, getInnerPoint(2).y)
            close()
        }
        drawPath(path2, color = darkBlue)

        // Trazo 3: Abajo-Derecha (Azul Vivo)
        val path3 = Path().apply {
            moveTo(getOuterPoint(2).x, getOuterPoint(2).y)
            lineTo(getOuterPoint(3).x, getOuterPoint(3).y)
            lineTo(getInnerPoint(4).x, getInnerPoint(4).y)
            lineTo(getInnerPoint(3).x, getInnerPoint(3).y)
            close()
        }
        drawPath(path3, color = brightBlue)

        // Trazo 4: Abajo-Izquierda (Dorado)
        val path4 = Path().apply {
            moveTo(getOuterPoint(3).x, getOuterPoint(3).y)
            lineTo(getOuterPoint(4).x, getOuterPoint(4).y)
            lineTo(getInnerPoint(5).x, getInnerPoint(5).y)
            lineTo(getInnerPoint(4).x, getInnerPoint(4).y)
            close()
        }
        drawPath(path4, color = yellowGold)

        // Trazo 5: Izquierda (Azul Oscuro)
        val path5 = Path().apply {
            moveTo(getOuterPoint(4).x, getOuterPoint(4).y)
            lineTo(getOuterPoint(5).x, getOuterPoint(5).y)
            lineTo(getInnerPoint(0).x, getInnerPoint(0).y)
            lineTo(getInnerPoint(5).x, getInnerPoint(5).y)
            close()
        }
        drawPath(path5, color = darkBlue)

        // Trazo 6: Arriba-Izquierda (Azul Vivo)
        val path6 = Path().apply {
            moveTo(getOuterPoint(5).x, getOuterPoint(5).y)
            lineTo(getOuterPoint(0).x, getOuterPoint(0).y)
            lineTo(getInnerPoint(1).x, getInnerPoint(1).y)
            lineTo(getInnerPoint(0).x, getInnerPoint(0).y)
            close()
        }
        drawPath(path6, color = brightBlue)
    }
}

@Preview(showBackground = true)
@Composable
fun LogScreenPreview() {
    MaterialTheme {
        LogScreen(navController = rememberNavController())
    }
}