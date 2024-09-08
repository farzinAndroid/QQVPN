package pro.sparrow_team.qq.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager.NameNotFoundException
import android.os.Build
import androidx.annotation.RequiresApi

object VersionHelper {

    fun getVersionName(context: Context): String? {
        try {
            val pInfo: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            val version: String = pInfo.versionName
            return version
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
            return null
        }
    }

    fun getVersionCode(context: Context): Long? {
        try {
            val pInfo: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            val version =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) pInfo.longVersionCode else pInfo.versionCode.toLong()
            return version
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
            return null
        }
    }

}