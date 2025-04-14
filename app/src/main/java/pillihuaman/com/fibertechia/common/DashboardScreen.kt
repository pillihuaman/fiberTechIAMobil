package pillihuaman.com.fibertechia.common
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pillihuaman.com.fibertechia.util.AppLogger

@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the FiberTechIA Dashboard!",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            AppLogger.debug("Navegando a PhotoCaptureScreen desde Dashboard")
            navController.navigate("photo_capture")
        }) {
            Text("Go to Camera")
        }
        Button(onClick = { /* TODO: logout or more actions */ }) {
            Text("Do something cool!")
        }
    }
}
