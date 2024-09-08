package pro.sparrow_team.qq.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)




val ColorScheme.mainBackgroundColor: Color
    get() =  Color(0xFF00070F)
val ColorScheme.white: Color
    get() =  Color(0xFFFFFAFA)

val ColorScheme.vpnButtonColor1: Color
    get() =  Color(0xFF00B2FF)

val ColorScheme.vpnButtonColor2: Color
    get() =  Color(0xFF0AA9FA)

val ColorScheme.vpnButtonColor3: Color
    get() =  Color(0xFF4670DA)

val ColorScheme.disconnectedColor: Brush
    get() =  Brush.verticalGradient(
        colors = listOf(
            vpnButtonColor1,
            vpnButtonColor2,
            vpnButtonColor3,
        ),
    )

val ColorScheme.green: Color
    get() =  Color(0xFF00FF00)

val ColorScheme.connectedColor: Brush
    get() =  Brush.verticalGradient(
        colors = listOf(
            green,
            green,
        ),
    )

val ColorScheme.blackAndTransparentVertical: Brush
    get() =  Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.Black,
        ),
    )

val ColorScheme.blurBlue: Color
    get() =  Color(0xFF011221)

val ColorScheme.unselectedServerRadioButtonColor: Color
    get() =  Color(0xFFC2CDDC)

val ColorScheme.sideMenuBorderColor: Color
    get() =  Color(0xFF0AA9FA)

val ColorScheme.updateCardBlue: Color
    get() =  Color(0xFF011221)

val ColorScheme.red: Color
    get() =  Color(0xFFFF4842)

val ColorScheme.cancelRed: Color
    get() =  Color(0x873C0C0C)


val ColorScheme.transparentBrush: Brush
    get() =  Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.Transparent,
        ),
    )
