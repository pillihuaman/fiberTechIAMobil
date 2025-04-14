package pillihuaman.com.fibertechia
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import pillihuaman.com.fibertechia.common.MyAppBar
import pillihuaman.com.fibertechia.ui.theme.FiberTechIATheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pillihuaman.com.fibertechia.auth.AuthViewModel
import pillihuaman.com.fibertechia.common.DashboardScreen
import pillihuaman.com.fibertechia.common.RetrofitClient
import pillihuaman.com.fibertechia.inteface.AuthRepository
import pillihuaman.com.fibertechia.process.camera.PhotoCaptureScreen
import pillihuaman.com.fibertechia.util.AppLogger

class MainActivity : ComponentActivity() {

    private val CAMERA_PERMISSION = Manifest.permission.CAMERA

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            Toast.makeText(this, "Se requiere permiso de cámara", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppLogger.info("MainActivity creado")

        enableEdgeToEdge()

        // Pedir permiso de cámara si no ha sido concedido
        if (ContextCompat.checkSelfPermission(this, CAMERA_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }

        setContent {
            FiberTechIATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    // Use `remember` to manage the state of the back stack entry
                    val currentBackStackEntry by navController.currentBackStackEntryFlow.collectAsState(initial = null)
                    val currentDestination = currentBackStackEntry?.destination?.route

                    Scaffold(
                        topBar = {
                            // Use top bar only on specified screens
                            when (currentDestination) {
                                "dashboard", "photo_capture", "qr_scan" -> {
                                    MyAppBar(navController = navController) // Pass navController here
                                }
                            }
                        },
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.Transparent
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "login",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("login") {
                                AppLogger.debug("Navegando a LoginScreen")
                                LoginScreen(
                                    onLoginSuccess = {
                                        AppLogger.info("Login exitoso. Navegando a Dashboard.")
                                        navController.navigate("dashboard") {
                                            popUpTo("login") { inclusive = true }
                                        }
                                    }
                                )
                            }

                            composable("dashboard") {
                                AppLogger.debug("Mostrando DashboardScreen")
                                DashboardScreen(navController = navController)
                            }

                            composable("photo_capture") {
                                AppLogger.debug("Accediendo a PhotoCaptureScreen")
                                PhotoCaptureScreen { uri ->
                                    AppLogger.info("Foto capturada con URI: $uri")
                                    navController.popBackStack()
                                }
                            }

                            composable("qr_scan") {
                                AppLogger.debug("Funcionalidad QR aún no implementada.")
                                // Future QR screen
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit,
    viewModel: AuthViewModel = remember { AuthViewModel(AuthRepository(RetrofitClient.authApi)) }
) {
    var username by remember { mutableStateOf("pillihuamanhz@gmail.com") }
    var password by remember { mutableStateOf("1988deza") }

    val user = viewModel.user
    val errorMessage = viewModel.errorMessage

    // Observe user login state
    LaunchedEffect(user) {
        if (user != null) {
            AppLogger.info("Usuario autenticado exitosamente: ${user}")
            onLoginSuccess()  // Navega al dashboard si login fue exitoso
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to FiberTechIA", color = MaterialTheme.colorScheme.onBackground)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (!errorMessage.isNullOrEmpty()) {
            AppLogger.warning("Error al intentar iniciar sesión: $errorMessage")
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            AppLogger.debug("Intento de login con usuario: $username")
            viewModel.login(username, password)
        }) {
            Text("Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    FiberTechIATheme {
        LoginScreen(onLoginSuccess = {})
    }
}
