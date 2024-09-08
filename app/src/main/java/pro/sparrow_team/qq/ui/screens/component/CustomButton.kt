package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.theme.transparentBrush

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    backgroundBrush: Brush = MaterialTheme.colorScheme.transparentBrush,
    borderStroke: Dp = 0.dp,
    text: String,
    textColor: Color,
    onClick: () -> Unit,
    shapes: RoundedCornerShape,
    borderColor: Color,
    enabled:Boolean = true
) {


    Box(
        modifier = modifier
            .clip(shapes)
            .height(44.dp)
            .width(150.dp)
            .background(backgroundBrush)
            .background(backgroundColor)
            .border(borderStroke,borderColor,shapes)
            .clickable(enabled = enabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
            Text(
                text = text,
                color = textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.almarai_regular)),
                textAlign = TextAlign.Center
            )
    }

}