package pro.sparrow_team.qq.ui.screens.splash

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.theme.white

@Composable
fun LoadingText(loadingText:String,modifier: Modifier = Modifier) {

    Text(
        text = loadingText,
        color = MaterialTheme.colorScheme.white,
        fontFamily = FontFamily(Font(R.font.almarai_regular)),
        fontSize = 14.sp,
        modifier = modifier
    )

}