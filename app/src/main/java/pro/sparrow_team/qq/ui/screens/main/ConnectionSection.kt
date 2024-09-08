package pro.sparrow_team.qq.ui.screens.main

import android.Manifest
import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.viewmodel.ConnectionState
import pro.sparrow_team.qq.model.mainscreen.DecodedConfig
import pro.sparrow_team.qq.ui.screens.component.ShadowCircle
import pro.sparrow_team.qq.ui.screens.component.TestConfigDialogBox
import pro.sparrow_team.qq.ui.screens.component.Timer
import pro.sparrow_team.qq.ui.screens.component.VPNButton
import pro.sparrow_team.qq.ui.theme.connectedColor
import pro.sparrow_team.qq.ui.theme.disconnectedColor
import pro.sparrow_team.qq.utils.MySpacerHeight
import pro.sparrow_team.qq.utils.PermissionHelper.askForNotificationPermission
import pro.sparrow_team.qq.utils.PermissionHelper.shouldAskForPermission
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import pro.sparrow_team.qq.viewmodel.MainViewModel

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ConnectionSection(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    sheetState: BottomSheetScaffoldState,
    mainViewModel: MainViewModel,
    context: Context,
    dataStoreViewModel: DataStoreViewModel,
) {

    val notificationPermission = rememberPermissionState(
        permission = Manifest.permission.POST_NOTIFICATIONS
    )

    val scope = rememberCoroutineScope()
    val connectionState by mainViewModel.connectionState.collectAsState()
    val time by mainViewModel.time.collectAsState()
    var showDialog by mainViewModel.showDialog
    var connecting = mainViewModel.connecting

    val activity = LocalContext.current as Activity

    // Observe connection state and update the connecting state accordingly
    LaunchedEffect(connectionState) {
        val connectionBoolean = connectionState == ConnectionState.Connected
        dataStoreViewModel.saveConnectionState(connectionBoolean)
        if (connectionState != ConnectionState.Connecting) {
            connecting.value = false
//            Log.e("TAG", connectionState.toString())
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        if (connectionState == ConnectionState.Connected) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 70.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MySpacerHeight(
                    height = 20.dp,
                    modifier = Modifier.background(Color.Red)
                )
                ShadowCircle()
            }
        }

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                TestConfigDialogBox(
                    activity = activity,
                    mainViewModel = mainViewModel,
                    connectionState = connectionState
                )
            }
        }


        Column(
            modifier = modifier.padding(top = 65.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ConnectionStatus(
                connectionState = connectionState,
                connecting = connecting.value,
                onConnectedCountryClicked = {
                    scope.launch {
                        mainViewModel.bottomSheetContents.value = BottomSheetContents.SERVER_LIST
                        sheetState.bottomSheetState.expand()
                    }
                },
                mainViewModel = mainViewModel
            )

            VPNButton(
                borderColor = MaterialTheme.colorScheme.disconnectedColor,
                switchIcon = painterResource(R.drawable.switch_off),
                icon = ImageBitmap.imageResource(R.drawable.moon),
                connectedColor = MaterialTheme.colorScheme.connectedColor,
                connectionState = connectionState,
                onClick = {
                    scope.launch {
//                        Log.i("TAG", "${mainViewModel.selectedServer}")
                        if (isButtonEnabled(mainViewModel)) {
                            if (shouldAskForPermission(notificationPermission)) {
                                askForNotificationPermission(
                                    notificationPermission
                                )
                            } else {

                                if (connectionState == ConnectionState.Disconnected && !connecting.value) {
                                    connecting.value = true
                                    /**
                                     * find the server with least delay and start that config
                                     * ps. showDialog value is for testing i didn't remove it
                                     * because we might need it later
                                     */
                                    val serverWithLeastDelay =
                                        mainViewModel.findServerWithLeastDelay(mainViewModel.decodedConfigList.value)
                                    mainViewModel.selectedServer = serverWithLeastDelay
                                    if (mainViewModel.selectedServer != DecodedConfig()){
                                        dataStoreViewModel.saveSelectedServerId(serverWithLeastDelay?.id!!)
                                        mainViewModel.startV2ray(
                                            mainViewModel.selectedServer?.name ?: "",
                                            mainViewModel.selectedServer?.decodedConfig ?: "",
                                            activity,
                                            arrayListOf("pro.sparrow_team.qq")
                                        )
                                    }
                                } else {
                                    dataStoreViewModel.saveConnectionState(false)
                                    mainViewModel.stopV2ray(activity)
                                    mainViewModel.showDialog.value = false
                                }
                            }

                        }
                    }
                },
                modifier = modifier,
                connecting = connecting.value,
                isButtonEnabled = !(mainViewModel.bottomSheetContents.value == BottomSheetContents.UNDER_CONSTRUCTION ||
                        mainViewModel.bottomSheetContents.value == BottomSheetContents.DISABLED_USER
                        || isForceUpdate(mainViewModel, context))
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
                Timer(modifier = modifier, time)
            }

        }
    }
}

fun isButtonEnabled(mainViewModel: MainViewModel) =
    (!(mainViewModel.bottomSheetContents.value == BottomSheetContents.UNDER_CONSTRUCTION
            || mainViewModel.bottomSheetContents.value == BottomSheetContents.DISABLED_USER))

fun shouldShowToast(mainViewModel: MainViewModel) = mainViewModel.selectedServer == null



