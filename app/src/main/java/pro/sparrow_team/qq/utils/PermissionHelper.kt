package pro.sparrow_team.qq.utils

import android.os.Build
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted

object PermissionHelper {

    @OptIn(ExperimentalPermissionsApi::class)
    fun askForNotificationPermission(permission: PermissionState){
            permission.launchPermissionRequest()
    }

    @OptIn(ExperimentalPermissionsApi::class)
    fun shouldAskForPermission(notificationPermission: PermissionState) = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            !notificationPermission.status.isGranted
}