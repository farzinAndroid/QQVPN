package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.theme.white

@Composable
fun Stars(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.stars),
        contentDescription = "",
        modifier = modifier,
        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.white)
    )
}