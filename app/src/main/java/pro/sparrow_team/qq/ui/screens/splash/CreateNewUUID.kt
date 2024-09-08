package pro.sparrow_team.qq.ui.screens.splash

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.traceEventStart
import pro.sparrow_team.qq.utils.Constants
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import java.util.UUID

@Composable
fun CreateNewUUID(
    dataStoreViewModel: DataStoreViewModel,
    onUUIDCreated:(String)->Unit
) {

    LaunchedEffect(true) {
        if (dataStoreViewModel.getUUID() == null){
            val uuid = UUID.randomUUID().toString()
            dataStoreViewModel.saveUUID(uuid)
            onUUIDCreated(uuid)
        }else{
            val uuid = dataStoreViewModel.getUUID()
            onUUIDCreated(uuid!!)
        }
    }

}