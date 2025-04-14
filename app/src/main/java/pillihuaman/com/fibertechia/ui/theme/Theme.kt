package pillihuaman.com.fibertechia.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val CustomColorScheme = lightColorScheme(
    primary = Color(0xFF7B1FA2),      // Morado principal (botones, enlaces)
    onPrimary = Color.White,          // Texto blanco sobre morado
    secondary = Color(0xFF00ACC1),    // Cian acento
    tertiary = Color(0xFF4A148C),     // Morado profundo (extra)
    background = Color(0xFF1A0033),   // Fondo morado oscuro
    onBackground = Color.White,       // Texto blanco sobre fondo
    surface = Color(0xFF311B92),      // Paneles, tarjetas
    onSurface = Color(0xFFE1BEE7),    // Texto lavanda sobre tarjetas
    error = Color(0xFFD32F2F),        // Rojo estándar para errores
)

@Composable
fun FiberTechIATheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = CustomColorScheme,
        typography = Typography,
        content = content
    )
}