package pro.sparrow_team.qq.data.service

import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pro.sparrow_team.qq.data.datastore.DataStoreRepoImpl
import pro.sparrow_team.qq.utils.Constants
import pro.sparrow_team.qq.utils.showToast
import java.util.UUID
import javax.inject.Inject



@AndroidEntryPoint
class FCMService : FirebaseMessagingService() {

    /**
     * get the new token from FCM service
     * and save it into datastore
     */


    @Inject
    lateinit var mainCoroutineScope: CoroutineScope

    @Inject
    lateinit var dataStoreRepo: DataStoreRepoImpl

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        saveToken(token)
        mainCoroutineScope.launch(Dispatchers.Main) {
//            applicationContext.showToast("New token : $token")
        }
    }

    private fun saveToken(token:String){
        mainCoroutineScope.launch(Dispatchers.IO) {
            dataStoreRepo.putString(token, Constants.FCM_TOKEN_ID)
        }
    }

}

