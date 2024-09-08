package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.theme.disconnectedColor
import pro.sparrow_team.qq.ui.theme.white

@Composable
fun UpdateVersionText(
    forwardOnClick: () -> Unit,
    backwardOnClick: () -> Unit,
    version: String,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 8.dp)
    ) {

        ForwardBackwardPagerArrow(
            onClick = backwardOnClick,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .rotate(180f)
        )


        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        brush = MaterialTheme.colorScheme.disconnectedColor,
                        fontFamily = FontFamily(Font(R.font.almarai_bold)),
                        fontSize = 17.sp
                    )
                ) {
                    append(stringResource(R.string.update))
                }

                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.white,
                        fontFamily = FontFamily(Font(R.font.almarai_bold)),
                        fontSize = 17.sp
                    )
                ) {
                    append(" ${stringResource(R.string.to_new_version)}")
                }
            },
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )


        ForwardBackwardPagerArrow(
            onClick = forwardOnClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }

}