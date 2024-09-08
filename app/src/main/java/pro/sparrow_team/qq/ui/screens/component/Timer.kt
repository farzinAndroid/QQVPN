package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.theme.white

@Composable
fun Timer(modifier: Modifier = Modifier,time:String) {

    Text(
        text = time,
        fontSize = 16.sp,
        modifier = modifier.padding(top = 20.dp),
        color = MaterialTheme.colorScheme.white,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily(Font(R.font.almarai_regular))
    )

}