package pillihuaman.com.fibertechia.entity.response
data class ResponseBody<T>(
    val trace: Trace?,
    val status: Status?,
    val payload: T?
)

data class Trace(
    val traceId: String?,
    val spanId: String?
)

data class Status(
    val code: Int?,
    val message: String?
)
