package pillihuaman.com.fibertechia.auth
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import pillihuaman.com.fibertechia.entity.User
import pillihuaman.com.fibertechia.inteface.AuthRepository
import pillihuaman.com.fibertechia.util.AppLogger
class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    var user by mutableStateOf<User?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String) {
        AppLogger.debug("Iniciando login para el email: $email")

        viewModelScope.launch {
            try {
                val result = repository.login(email, password)

                if (result != null) {
                    user = result
                    AppLogger.info("Login exitoso para el usuario: ${result}")
                    errorMessage = null
                } else {
                    errorMessage = "Credenciales inválidas"
                    AppLogger.warning("Login fallido para el usuario: $email (credenciales inválidas)")
                }

            } catch (e: Exception) {
                errorMessage = "Error de red: ${e.message}"
                AppLogger.error("Error durante login: ${e.message}", e)
            }
        }
    }
}

