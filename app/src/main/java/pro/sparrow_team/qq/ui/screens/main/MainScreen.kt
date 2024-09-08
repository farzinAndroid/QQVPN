package pro.sparrow_team.qq.ui.screens.main

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pro.sparrow_team.qq.model.mainscreen.Status
import pro.sparrow_team.qq.model.mainscreen.testStatus
import pro.sparrow_team.qq.ui.screens.component.ShowInitialBottomSheetBasedOnApi
import pro.sparrow_team.qq.ui.screens.component.Stars
import pro.sparrow_team.qq.utils.VersionHelper
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import pro.sparrow_team.qq.viewmodel.MainViewModel

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    dataStoreViewModel: DataStoreViewModel,
) {


    Main(mainViewModel = mainViewModel, dataStoreViewModel = dataStoreViewModel)

}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(mainViewModel: MainViewModel, dataStoreViewModel: DataStoreViewModel) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    /**
     * if one of these conditions are true
     * that means we should show a bottom sheet
     * so we put it to EXPANDED
     */
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            initialValue = if (!mainViewModel.status.functional ||
                mainViewModel.status.reject || !mainViewModel.isApiHealthy
            ) SheetValue.Expanded
            else
                SheetValue.Hidden,
            skipPartiallyExpanded = true
        )
    )
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    var dpValue by remember { mutableStateOf(0.dp) }
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp



    /**
     * This is a a workaround to blur the background faster
     * previously i used the sheetState.bottomSheetState.isVisible
     * but it has a slight delay
     */
    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.bottomSheetState.targetValue }
            .collect { targetValue ->
                if (targetValue == SheetValue.Expanded || targetValue == SheetValue.PartiallyExpanded) {
                    // Increment dpValue from 0 to 10 as the sheet expands
                    launch {
                        for (i in 1..10) {
                            dpValue = i.dp
                        }
                    }
                } else if (targetValue == SheetValue.Hidden) {
                    // Reset dpValue to 0 when the sheet is hidden
                    dpValue = 0.dp
                }
            }
    }


    /**
     * ShowInitialBottomSheetBasedOnApi
     */
    ShowInitialBottomSheetBasedOnApi(
            mainViewModel = mainViewModel,
            sheetState = sheetState,
            context = context,
            dataStoreViewModel = dataStoreViewModel
        )


    /**
     * we will observe on the showInitialBottomSheetComplete & bottomSheetContents
     * to show the bottom sheet.
     * The reason is because if we do not do this we have to click twice to show bottom sheets.
     */
    if (mainViewModel.showInitialBottomSheetComplete.value && mainViewModel.bottomSheetContents.value != BottomSheetContents.NONE) {
        LaunchedEffect(mainViewModel.bottomSheetContents.value) {
            if (!sheetState.bottomSheetState.isVisible) {
                sheetState.bottomSheetState.show()
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                /**
                 * if any of these conditions are true we should not be able to work with the app
                 */
                enabled = !(mainViewModel.bottomSheetContents.value == BottomSheetContents.UNDER_CONSTRUCTION ||
                        mainViewModel.bottomSheetContents.value == BottomSheetContents.DISABLED_USER
                        || isForceUpdate(mainViewModel,context)),
                onClick = {
                    if (sheetState.bottomSheetState.isVisible) {
                        scope.launch {
                            sheetState.bottomSheetState.hide()
                        }
                    }
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
    ) {


        BottomSheetScaffold(
            sheetContent = {
                mainViewModel.bottomSheetContents.value.let {
                    SheetLayout(
                        bottomSheetContents = it,
                        sheetState = sheetState,
                        mainViewModel = mainViewModel,
                        dataStoreViewModel = dataStoreViewModel
                    )
                }
            },
            sheetContainerColor = Color.Transparent,
            containerColor = Color.Transparent,
            sheetPeekHeight = 0.dp,
            scaffoldState = sheetState,
            content = {
                ModalNavigationDrawer(
                    drawerContent = {
                        DrawerContent(
                            onCloseClicked = {
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            mainViewModel = mainViewModel
                        )
                    },
                    drawerState = drawerState,
                    content = {
                        Box(
                            modifier = Modifier
                                .blur(dpValue)
                        ) {
                            MainScreenContent(
                                drawerState = drawerState,
                                sheetState = sheetState,
                                mainViewModel = mainViewModel,
                                dataStoreViewModel = dataStoreViewModel
                            )

                            /**
                             * this part is for showing stars in the background
                             * The team wanted these stars to be manually implemented
                             */
                            for (i in 1..7) {
                                val modifier = when (i) {
                                    1 ->
                                        Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.5f)
                                            .align(Alignment.TopCenter)

                                    2 ->
                                        Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.5f)
                                            .align(Alignment.Center)
                                            .rotate(180f)

                                    3 ->
                                        Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.5f)
                                            .align(Alignment.Center)
                                            .rotate(90f)

                                    4 ->
                                        Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.5f)
                                            .align(Alignment.BottomCenter)
                                            .rotate(270f)

                                    5 ->
                                        Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.5f)
                                            .align(Alignment.BottomCenter)
                                            .rotate(90f)

                                    6 ->
                                        Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.5f)
                                            .align(Alignment.BottomCenter)
                                            .rotate(180f)

                                    7 ->
                                        Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.5f)
                                            .align(Alignment.BottomCenter)
                                            .rotate(200f)


                                    else -> Modifier
                                }
                                Stars(modifier)
                            }
                        }

                    },
                    gesturesEnabled = drawerState.isOpen
                )
            },
            modifier = Modifier
//                .pointerInput(Unit) {
//                    detectTapGestures(onTap = {
//                        if (sheetState.bottomSheetState.hasExpandedState) {
//                            scope.launch {
//                                sheetState.bottomSheetState.hide()
//                            }
//                        }
//                    })
//
//                }
            ,
            sheetDragHandle = null,
            sheetSwipeEnabled = !(mainViewModel.bottomSheetContents.value == BottomSheetContents.UNDER_CONSTRUCTION ||
                    mainViewModel.bottomSheetContents.value == BottomSheetContents.DISABLED_USER
                    || isForceUpdate(mainViewModel,context) || !mainViewModel.isApiHealthy)

        )
    }
}

/**
 * we have 2 kinds of update
 * a forced update and a normal update in forced update we should not be able t work with the app.
 */

fun isForceUpdate(mainViewModel: MainViewModel, context: Context): Boolean {
    return when {
        mainViewModel.status.minVersion > VersionHelper.getVersionCode(context)!! -> {
            true
        }

        mainViewModel.status.maxVersion > VersionHelper.getVersionCode(context)!! -> {
            false
        }

        else -> {
            false
        }
    }
}

fun isUpdateAvailable(mainViewModel: MainViewModel, context: Context) =
    mainViewModel.status.maxVersion > VersionHelper.getVersionCode(context)!! ||
            mainViewModel.status.minVersion > VersionHelper.getVersionCode(context)!!


