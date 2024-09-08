package pro.sparrow_team.qq.utils

import androidx.compose.runtime.Composable
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import pro.sparrow_team.qq.viewmodel.MainViewModel

fun appConfig(
    dataStore: DataStoreViewModel,
    mainViewModel: MainViewModel
) {

    getDataStoreVariables(dataStore,mainViewModel)

}

private fun getDataStoreVariables(dataStore: DataStoreViewModel,mainViewModel: MainViewModel) {
    Constants.TOKEN = dataStore.getToken()
    Constants.UUID = dataStore.getUUID().toString()
    /**
     * here we fill the selectedServer var with the server that is
     * already selected in the dataStore
     */
    dataStore.getSelectedServerId().let { id ->
        mainViewModel.selectedServer = mainViewModel.decodedConfigList.value.find { it.id == id }
    }
}