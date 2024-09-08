package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.theme.white

@Composable
fun ForwardBackwardPagerArrow(
    onClick:()->Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .clickable {
                onClick()
            }
            .size(20.dp)
            .background(MaterialTheme.colorScheme.white)
            .clip(RoundedCornerShape(2.dp)),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = "",
            modifier = Modifier
                .size(8.dp),
            tint = Color.Black
        )
    }

}