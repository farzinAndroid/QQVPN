package pro.sparrow_team.qq.ui.screens.main

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.theme.white
import pro.sparrow_team.qq.viewmodel.MainViewModel

@Composable
fun TopBarRow(
    onMenuSelected: () -> Unit,
    onNotificationSelected: () -> Unit,
    onProfileSelected: () -> Unit,
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    context: Context
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .statusBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
        ) {

            Image(
                painter = painterResource(R.drawable.menu),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 20.dp, start = 16.dp)
                    .size(24.dp)
                    .clickable(
                        enabled = !(mainViewModel.bottomSheetContents.value == BottomSheetContents.UNDER_CONSTRUCTION ||
                                mainViewModel.bottomSheetContents.value == BottomSheetContents.DISABLED_USER
                                || isForceUpdate(mainViewModel, context)),
                        onClick = { onMenuSelected() }
                    )

            )



            Image(
                painter = painterResource(R.drawable.notification_fill),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 20.dp, start = 16.dp)
                    .size(24.dp)
                    .clickable(
                        enabled = !(mainViewModel.bottomSheetContents.value == BottomSheetContents.UNDER_CONSTRUCTION ||
                                mainViewModel.bottomSheetContents.value == BottomSheetContents.DISABLED_USER
                                || isForceUpdate(mainViewModel, context)),
                        onClick = {onNotificationSelected()}
                    ),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.white)
            )

        }

        /*Text(
            text = stringResource(R.string.beans),
            color = MaterialTheme.colorScheme.white,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Center),
            fontFamily = FontFamily(Font(R.font.almarai_regular))
        )*/


        /*ProfileCircle(
            onProfileSelected = onProfileSelected,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 12.dp, top = 14.dp)
        )*/


    }

}