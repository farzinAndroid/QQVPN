package pro.sparrow_team.qq.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import pro.sparrow_team.qq.ui.screens.component.EarthPicture
import pro.sparrow_team.qq.ui.theme.mainBackgroundColor
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import pro.sparrow_team.qq.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    drawerState: DrawerState,
    sheetState: BottomSheetScaffoldState,
    mainViewModel: MainViewModel,
    dataStoreViewModel: DataStoreViewModel
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackgroundColor)
    ) {

        /**
         * first half connection section
         */
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.55f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TopBarRow(
                    onMenuSelected = {
                        scope.launch {
                            if ((!(mainViewModel.bottomSheetContents.value == BottomSheetContents.UNDER_CONSTRUCTION || mainViewModel.bottomSheetContents.value == BottomSheetContents.DISABLED_USER))) {
                                if (drawerState.currentValue == DrawerValue.Closed) {
                                    sheetState.bottomSheetState.hide()
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }

                        }
                        /*scope.launch {
                            if (!sheetState.bottomSheetState.isVisible) {
                                drawerState.close()
                                mainViewModel.bottomSheetContents.value = BottomSheetContents.SERVER_LIST
                                sheetState.bottomSheetState.expand()
                            } else {
                                sheetState.bottomSheetState.hide()
                            }
                        }*/
                    },
                    onNotificationSelected = {
//                        mainViewModel.copyTokenToClipboard()
                        scope.launch {
                            mainViewModel.bottomSheetContents.value =
                                BottomSheetContents.NOTIFICATION
                            sheetState.bottomSheetState.expand()
                        }
                    },
                    onProfileSelected = {/*TODO*/ },
                    modifier = Modifier.align(Alignment.TopCenter),
                    mainViewModel = mainViewModel,
                    context = context
                )


                ConnectionSection(
                    modifier = Modifier.align(Alignment.Center),
                    drawerState = drawerState,
                    sheetState = sheetState,
                    mainViewModel = mainViewModel,
                    context = context,
                    dataStoreViewModel = dataStoreViewModel
                )
            }


        }



        /**
         * second half (earth)
         */
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.45f)
        ) {
//            EarthAnimation(mainViewModel)
            Box(modifier = Modifier.fillMaxSize()){
                EarthPicture(modifier = Modifier.align(Alignment.BottomStart))
            }
        }

    }
}