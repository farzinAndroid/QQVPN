package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.sparrow_team.qq.R

@Composable
fun ProfileCircle(
    onProfileSelected:()->Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .size(42.dp)
            .clickable {
                onProfileSelected()
            }
            .padding(2.dp)
    ){
        Image(
            painter = painterResource(R.drawable.profile_circle),
            contentDescription ="",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Image(
            painter = painterResource(R.drawable.usa_flag),
            contentDescription ="",
            modifier = Modifier
                .size(30.dp)
                .padding(1.dp)
                .clip(CircleShape)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.Fit
        )

    }

}