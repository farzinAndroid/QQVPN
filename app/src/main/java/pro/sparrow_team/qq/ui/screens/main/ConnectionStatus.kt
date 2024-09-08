package pro.sparrow_team.qq.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blongho.country_data.World
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.viewmodel.ConnectionState
import pro.sparrow_team.qq.ui.theme.white
import pro.sparrow_team.qq.utils.MySpacerHeight
import pro.sparrow_team.qq.viewmodel.MainViewModel


@Composable
fun ConnectionStatus(
    connectionState: ConnectionState,
    connecting: Boolean,
    modifier: Modifier = Modifier,
    onConnectedCountryClicked: () -> Unit,
    mainViewModel: MainViewModel
) {

    val connectionStatusText = when (connectionState) {
        ConnectionState.Connected -> stringResource(R.string.connected)
        ConnectionState.Disconnected -> stringResource(R.string.disconnected)
        else -> {
            ""
        }
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (connecting) stringResource(R.string.connecting) else connectionStatusText,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.white,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.almarai_regular))
            )


            Icon(
                painter = painterResource(R.drawable.rocket),
                contentDescription = "",
                modifier = Modifier
                    .size(14.dp),
                tint = MaterialTheme.colorScheme.white
            )
        }


        MySpacerHeight(
            height = if (connectionState == ConnectionState.Disconnected) 20.dp else 6.dp
        )


        AnimatedVisibility(
            visible = connectionState == ConnectionState.Connected,
            enter = expandIn(
                expandFrom = Alignment.TopCenter
            ),
            exit = shrinkOut(
                shrinkTowards = Alignment.BottomCenter
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 18.dp)
                    .clickable {
                        onConnectedCountryClicked()
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(World.getFlagOf(mainViewModel.selectedServer?.location ?: "")),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = mainViewModel.selectedServer?.name ?: "",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.white,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.almarai_regular)),
                )

                Spacer(modifier = Modifier.width(4.dp))

                Image(
                    painter = painterResource(R.drawable.triangle),
                    contentDescription = "",
                    modifier = Modifier
                        .size(10.dp)
                )
            }
        }


    }

}