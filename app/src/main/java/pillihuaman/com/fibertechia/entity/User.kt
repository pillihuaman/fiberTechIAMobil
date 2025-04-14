package pillihuaman.com.fibertechia.entity

data class User(
    val name: String?,
    val lastName: String?,
    val code: Int?,
    val estatus: Boolean?,
    val token: String?,
    val personID: Int?,
    val email: String?,
    val rolId: Int?,
    val userName: String?,
    val typeDocument: String?,
    val numTypeDocument: String?,
    val alias: String?,
    val password: String?,
    val repeatpassword: String?,
    val phoneNumber: String?,
    val access_token: String?
)
