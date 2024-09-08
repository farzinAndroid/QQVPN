package pro.sparrow_team.qq.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import pro.sparrow_team.qq.ui.theme.mainBackgroundColor

@Composable
fun MySpacerHeight(
    modifier: Modifier = Modifier,
    height:Dp
) {
    Spacer(modifier = modifier.height(height))
}

@Composable
fun MySpacerWidth(
    modifier: Modifier = Modifier,
    width:Dp
) {
    Spacer(modifier = modifier.width(width))
}


/**
 * first one is for Theme.kt
 * This one is for changing the nav and status bar color
 * in Theme.kt
 */
@Composable
fun ChangeStatusBarAndNavigationBarColor1(statusBarColor:Int,navigationBarColor:Int) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity  = view.context as Activity
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                activity.window.navigationBarColor = navigationBarColor
                activity.window.statusBarColor = statusBarColor
//                WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars = !darkTheme
//                WindowCompat.getInsetsController(activity.window, view).isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }
}


/**
 * second one is for using edgeToEdge
 * same result different method (easier)
 */
@Composable
fun ChangeStatusBarAndNavigationBarColor2(isDarkMode: Boolean, context: ComponentActivity) {
    val color = MaterialTheme.colorScheme.mainBackgroundColor

    val statusBarStyle = if (isDarkMode) {
        SystemBarStyle.dark(
            color.toArgb(),
        )
    } else {
        SystemBarStyle.dark(
            android.graphics.Color.TRANSPARENT
        )
    }

    DisposableEffect(isDarkMode) {
        context.enableEdgeToEdge(
            statusBarStyle = statusBarStyle,
            navigationBarStyle = if (!isDarkMode) {
                SystemBarStyle.light(
                    android.graphics.Color.TRANSPARENT,
                    android.graphics.Color.TRANSPARENT
                )
            } else {
                SystemBarStyle.dark(
                    android.graphics.Color.TRANSPARENT
                )
            }
        )
        onDispose {  }
    }
}


fun Context.showToast(
    message:String
) = Toast.makeText(this,message,Toast.LENGTH_SHORT).show()