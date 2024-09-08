package pro.sparrow_team.qq.ui.screens.main

import android.content.Context
import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.screens.component.CustomButton
import pro.sparrow_team.qq.ui.theme.SparrowTheme
import pro.sparrow_team.qq.ui.theme.disconnectedColor
import pro.sparrow_team.qq.ui.theme.updateCardBlue
import pro.sparrow_team.qq.ui.theme.white
import pro.sparrow_team.qq.utils.MySpacerHeight
import pro.sparrow_team.qq.viewmodel.MainViewModel

@Composable
fun UnderConstructionBottomSheetContent(
    context: Context,
    mainViewModel: MainViewModel,
) {

    Card(
        modifier = Modifier
            .fillMaxHeight(0.3f)
            .clickable(
                enabled = false,
                onClick = {},
                indication = null,
                interactionSource = remember { MutableInteractionSource() }),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.updateCardBlue.copy(0.95f)
        ),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.under_construction),
                fontFamily = FontFamily(Font(R.font.almarai_bold)),
                color = MaterialTheme.colorScheme.white,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 24.dp)
            )

            MySpacerHeight(height = 22.dp)

            val text = mainViewModel.status.descriptions.maintenance ?: stringResource(R.string.under_construction_full_text)

            /**
             * Define the starting and ending indices for "We'll be back soon"
             */
            val startIndex = text.indexOf("We'll be back soon")
            val endIndex = startIndex + "We'll be back soon".length

            val annotatedText = buildAnnotatedString {
                /**
                 * Add everything before "We'll be back soon"
                 */
                append(text.substring(0, startIndex))


                /**
                 * Apply specific style to "We'll be back soon"
                 */
                withStyle(style = SpanStyle(brush = MaterialTheme.colorScheme.disconnectedColor)) {
                    append(text.substring(startIndex, endIndex))
                }

                /**
                 * Add everything after "We'll be back soon"
                 */
                append(text.substring(endIndex))
            }

            Text(
                text = annotatedText,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 22.dp),
                color = MaterialTheme.colorScheme.white,
                fontFamily = FontFamily(Font(R.font.almarai_regular)),
            )

            MySpacerHeight(height = 24.dp)

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