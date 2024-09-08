package pro.sparrow_team.qq.model.mainscreen

import kotlin.math.truncate

data class Status(
    val advertise: Boolean = false,
    val currentVersion: Int = 0,
    val descriptions: Descriptions = Descriptions(),
    val email: String? = null,
    val functional: Boolean = false,
    val maxVersion: Int = 0,
    val minVersion: Int = 0,
    val payment: Boolean = false,
    val reject: Boolean = false,
    val url: String = ""
)
data class Descriptions(
    val maintenance: String? = null,
    val policies: String? = null,
    val reject: String? = null,
    val update: List<String> = emptyList()
)

val testStatus = Status(
    minVersion = 1,
    maxVersion = 1,
    functional = true,
    reject = false
)