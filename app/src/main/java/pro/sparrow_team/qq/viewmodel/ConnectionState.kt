package pro.sparrow_team.qq.viewmodel

sealed class ConnectionState {
    data object Connected : ConnectionState()
    data object Disconnected : ConnectionState()
    data object Connecting : ConnectionState()
}