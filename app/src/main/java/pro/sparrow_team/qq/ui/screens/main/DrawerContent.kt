package pro.sparrow_team.qq.ui.screens.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.screens.component.DrawerRow
import pro.sparrow_team.qq.ui.theme.blurBlue
import pro.sparrow_team.qq.ui.theme.disconnectedColor
import pro.sparrow_team.qq.ui.theme.sideMenuBorderColor
import pro.sparrow_team.qq.viewmodel.MainViewModel

@Composable
fun DrawerContent(
    mainViewModel: MainViewModel,
    onCloseClicked: () -> Unit,
) {
    val bColor = MaterialTheme.colorScheme.sideMenuBorderColor

    var showPrivacyText by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current as Activity

    Card(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.7f)
//            .drawWithContent {
//                val widthPx = 2.dp.toPx() // Adjust BorderWidth as needed
//                val color = bColor // Define your desired border color
//
//                drawContent() // Draw the card content
//
//                drawLine(
//                    color = color,
//                    start = Offset(x = size.width, y = 0f), // Start from top right corner
//                    end = Offset(x = size.width, y = size.height), // End at bottom right corner
//                    strokeWidth = widthPx
//                )
//            }
        ,
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 10.dp,
            bottomEnd = 10.dp,
            bottomStart = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.blurBlue.copy(0.95f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 44.dp)
                .padding(start = 20.dp)
                .padding(end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            brush = MaterialTheme.colorScheme.disconnectedColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(Font(R.font.almarai_extra_bold))
                        )
                    ) {
                        append(stringResource(R.string.qq_vpn))
                    }
                }
            )

            Image(
                painter = painterResource(R.drawable.close),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        onClick = {
                            onCloseClicked()
                        }
                    )
            )

        }

        DrawerRow(
            onClick = {
                shareToSocialMedia(
                    context,
                    mainViewModel.status.url
                )
            },
            title = stringResource(R.string.share),
            subTitle = ""
        )

        DrawerRow(
            onClick = {
                showPrivacyText = !showPrivacyText
            },
            title = stringResource(R.string.privacy_policy),
            subTitle = mainViewModel.status.descriptions.policies ?: stringResource(R.string.privacy_policy_text),
            showText = showPrivacyText
        )

        DrawerRow(
            onClick = {
                mainViewModel.openGmail(mainViewModel.status.email ?: "info@sparrow-team.pro", context)
            },
            title = stringResource(R.string.contact_us),
            subTitle = ""
        )


    }
}

fun shareToSocialMedia(
    context: Context,
    url: String,
) {

    val shareIntent = Intent(Intent.ACTION_SEND)

    shareIntent.type = "text/plain"

    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        "Download QQ VPN at $url"
    )

    context.startActivity(Intent.createChooser(shareIntent, "Share to..."))

}