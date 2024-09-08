package pro.sparrow_team.qq.ui.screens.main

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.screens.component.CustomButton
import pro.sparrow_team.qq.ui.theme.SparrowTheme
import pro.sparrow_team.qq.ui.theme.disconnectedColor
import pro.sparrow_team.qq.ui.theme.red
import pro.sparrow_team.qq.ui.theme.updateCardBlue
import pro.sparrow_team.qq.ui.theme.white
import pro.sparrow_team.qq.utils.MySpacerHeight
import pro.sparrow_team.qq.viewmodel.MainViewModel

@Composable
fun UnderConstructionSection(
    onCloseClicked: () -> Unit,
    mainViewModel: MainViewModel,
) {

    val context = LocalContext.current as Activity

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.updateCardBlue.copy(0.95f))

    ) {

        IconButton(
            onClick = { onCloseClicked() },
            modifier = Modifier
                .padding(start = 12.dp, top = 20.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.close),
                contentDescription = "",
                modifier = Modifier
                    .size(26.dp),
                tint = MaterialTheme.colorScheme.red
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 200.dp)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.oops),
                color = MaterialTheme.colorScheme.red,
                fontFamily = FontFamily(Font(R.font.almarai_regular)),
            )

            MySpacerHeight(height = 2.dp)

            Text(
                text = stringResource(R.string.under_construction),
                color = MaterialTheme.colorScheme.white,
                fontFamily = FontFamily(Font(R.font.almarai_bold)),
            )

            MySpacerHeight(height = 4.dp)

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.white,
                            fontFamily = FontFamily(Font(R.font.almarai_regular))
                        )
                    ) {
                        append(stringResource(R.string.maintanance_caption))
                    }

                    withStyle(
                        style = SpanStyle(
                            brush = MaterialTheme.colorScheme.disconnectedColor,
                            fontFamily = FontFamily(Font(R.font.almarai_regular))
                        )
                    ) {
                        append(" ${stringResource(R.string.be_back_soon)} . ")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.white,
                            fontFamily = FontFamily(Font(R.font.almarai_regular))
                        )
                    ) {
                        append(stringResource(R.string.thank_you))
                    }
                },
            )

            MySpacerHeight(height = 50.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {
                CustomButton(
                    text = stringResource(R.string.contact_us),
                    textColor = MaterialTheme.colorScheme.white,
                    onClick = {
                        mainViewModel.openGmail(mainViewModel.status.email ?: "info@sparrow-team.pro",context)
                    },
                    shapes = RoundedCornerShape(8.dp),
                    borderColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    backgroundBrush = MaterialTheme.colorScheme.disconnectedColor
                )
            }
        }
    }
}