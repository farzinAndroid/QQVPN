package pro.sparrow_team.qq.ui.screens.main

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.screens.component.PagerIndicator
import pro.sparrow_team.qq.ui.screens.component.UpdateButtonSection
import pro.sparrow_team.qq.ui.screens.component.UpdateVersionText
import pro.sparrow_team.qq.ui.theme.updateCardBlue
import pro.sparrow_team.qq.ui.theme.white
import pro.sparrow_team.qq.utils.MySpacerHeight
import pro.sparrow_team.qq.utils.VersionHelper
import pro.sparrow_team.qq.viewmodel.MainViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun UpdateBottomSheetContent(
    sheetState: BottomSheetScaffoldState,
    context: Context,
    mainViewModel: MainViewModel,
) {

    val testListPager = mainViewModel.status.descriptions.update

    val pagerState = rememberPagerState { testListPager.size }
    val scope = rememberCoroutineScope()



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
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp)
            ) {
                PagerIndicator(
                    count = pagerState.pageCount,
                    circleSpacing = 6.dp,
                    width = 5.dp,
                    height = 5.dp,
                    activeLineWidth = 12.dp,
                    pagerState = pagerState
                )
            }

            UpdateVersionText(
                forwardOnClick = {
                    scope.launch {
                        if (pagerState.currentPage < pagerState.pageCount - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }

                },
                backwardOnClick = {
                    scope.launch {
                        if (pagerState.currentPage > 0) {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                version = "1.31"
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
            ) {
                val text = testListPager[it]
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.white,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.almarai_regular)),
                    modifier = Modifier
                        .padding(horizontal = 24.dp),
                    fontWeight = FontWeight.Normal
                )
            }


            MySpacerHeight(height = 34.dp)

            UpdateButtonSection(
                onUpdateClicked = {
                    openPlayStore(
                        context as Activity,
                        mainViewModel
                    )
                },
                onCancelClicked = {
                    scope.launch {
                        sheetState.bottomSheetState.hide()
                    }
                },
                cancelEnabled = !isForceUpdate(mainViewModel, context)
            )

        }


    }
}

fun openPlayStore(context: Activity, mainViewModel: MainViewModel) {
    try {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(mainViewModel.status.url)
            )
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
}