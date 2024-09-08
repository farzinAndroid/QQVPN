package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.sparrow_team.qq.R

@Composable
fun EarthPicture(modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(R.drawable.earth_pic),
        contentDescription ="",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .fillMaxHeight(0.82f)
            .fillMaxWidth(0.8f)
    )


}