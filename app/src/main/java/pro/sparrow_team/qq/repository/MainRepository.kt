package pro.sparrow_team.qq.repository

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.dev7.lib.v2ray.V2rayController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pro.sparrow_team.qq.data.database.PingDao
import pro.sparrow_team.qq.data.remote.BaseApiResponse
import pro.sparrow_team.qq.data.remote.MainScreenApiInterface
import pro.sparrow_team.qq.model.mainscreen.Ping
import pro.sparrow_team.qq.model.mainscreen.UpdateTokenModel
import pro.sparrow_team.qq.model.mainscreen.UserPublicIDModel
import pro.sparrow_team.qq.model.mainscreen.UserRegisterModel
import pro.sparrow_team.qq.utils.showToast
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val fcm: FirebaseMessaging,
    private val clipboardManager: ClipboardManager,
    @ApplicationContext private val context: Context,
    private val pingDao: PingDao,
    private val mainScreenApiInterface: MainScreenApiInterface,
) : BaseApiResponse() {



    /**
     * APIs
     */
    suspend fun getStatus() = safeApiCall {
        mainScreenApiInterface.getStatus()
    }

    suspend fun registerUser(userRegisterModel: UserRegisterModel) = safeApiCall {
        mainScreenApiInterface.registerUser(userRegisterModel)
    }

    suspend fun getUserAuthenticity(id: String) = safeApiCall {
        mainScreenApiInterface.getUserAuthenticity(id)
    }

    suspend fun updateUserToken(id: String, updateTokenModel: UpdateTokenModel) = safeApiCall {
        mainScreenApiInterface.updateUserToken(id, updateTokenModel)
    }

    suspend fun sendUserPublicID(id: String, userPublicIDModel: UserPublicIDModel) = safeApiCall {
        mainScreenApiInterface.sendUserPublicID(id, userPublicIDModel)
    }


    suspend fun getConfigurations(id: String) = safeApiCall {
        mainScreenApiInterface.getConfigurations(id)
    }


    /**
     * Data base
     */
    val pingsListFromDB = pingDao.getAllPings()
    suspend fun addPings(list: List<Ping>) = pingDao.addPings(list)



    suspend fun startV2ray(
        remark: String,
        config: String,
        activity: Activity,
        blockedApps: ArrayList<String>,
    ) {
        withContext(Dispatchers.IO) {
            V2rayController.startV2ray(activity, remark, config, blockedApps)
        }
    }

    suspend fun stopV2ray(activity: Activity) {
        withContext(Dispatchers.Main) {
            V2rayController.stopV2ray(activity)
        }
    }


    /**
     * copyTokenToClipboard for testing Only
     */

    suspend fun copyTokenToClipboard() {
        withContext(Dispatchers.Main) {
            fcm.token
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val clipData = ClipData.newPlainText("config copy", task.result)
                        clipboardManager.setPrimaryClip(clipData)
                        context.showToast("copied")

                    } else {
                        context.showToast("no token")
                    }
                }
        }

    }

}