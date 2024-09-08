package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.theme.white
import pro.sparrow_team.qq.utils.MySpacerWidth

@Composable
fun DrawerRow(
    onClick: () -> Unit,
    showText: Boolean = false,
    title: String,
    subTitle: String,
) {


    val rotationDegree by animateFloatAsState(
        targetValue = if(showText) 0f else -90f,
        label = "",
        animationSpec = tween(500)
    )
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.triangle),
                contentDescription = "",
                modifier = Modifier
                    .size(10.dp)
                    .rotate(rotationDegree)
            )

            MySpacerWidth(width = 8.dp)

            Text(
                text = title,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.almarai_regular)),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.white
                )
            )
        }


        AnimatedVisibility(
            visible = showText,
            enter = expandIn(
                expandFrom = Alignment.BottomStart,
            ),
            exit = shrinkOut(
                shrinkTowards = Alignment.BottomStart
            )
        ) {
            Text(
                text = subTitle,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.almarai_regular)),
                    fontSize = 12.sp,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.white
                ),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .padding(start = 38.dp)
                    .padding(top = 12.dp)
                    .fillMaxWidth()

            )
        }


    }


}