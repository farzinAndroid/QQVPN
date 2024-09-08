package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pro.sparrow_team.qq.ui.theme.mainBackgroundColor
import pro.sparrow_team.qq.ui.theme.vpnButtonColor2

@Composable
fun ShadowCircle(
    modifier: Modifier = Modifier,
    shadowBorderRadius:Dp = 100.dp,
    spread:Dp = 30.dp,
    blurRadius:Dp = 300.dp,
    size:Dp = 140.dp,
    borderRadius:Dp = 100.dp,
    background:Color = MaterialTheme.colorScheme.mainBackgroundColor,
    shadowColor:Color = MaterialTheme.colorScheme.vpnButtonColor2.copy(0.30f)
) {

    Box(
        modifier = modifier
            .shadow(
                color = shadowColor,
                borderRadius = shadowBorderRadius,
                spread = spread,
                blurRadius = blurRadius
            )
            .size(size)
            .clip(RoundedCornerShape(borderRadius))
            .background(background)
    )
}