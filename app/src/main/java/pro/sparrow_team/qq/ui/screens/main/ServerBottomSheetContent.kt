package pro.sparrow_team.qq.ui.screens.main

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.model.mainscreen.DecodedConfig
import pro.sparrow_team.qq.ui.screens.component.ServerItem
import pro.sparrow_team.qq.ui.theme.blurBlue
import pro.sparrow_team.qq.ui.theme.white
import pro.sparrow_team.qq.utils.showToast
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import pro.sparrow_team.qq.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServerBottomSheetContent(
    configList: List<DecodedConfig>,
    mainViewModel: MainViewModel,
    dataStoreViewModel: DataStoreViewModel,
    sheetState: BottomSheetScaffoldState
) {

    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()
    val last = configList.lastIndex

    val pingListFromDB by mainViewModel.pingListFromDB.collectAsState(emptyList())

    Card(
        modifier = Modifier
            .fillMaxHeight(0.4f)
        /*.drawWithContent {
            val widthPx = 4.dp.toPx() // Adjust BorderWidth as needed
            val color = Color.White // Define your desired border color

            drawContent() // Draw the card content

            // Draw the top border
            drawLine(
                color = color,
                start = Offset(x = 0f, y = 0f), // Start from top left corner
                end = Offset(x = size.width, y = 0f), // End at top right corner
                strokeWidth = widthPx
            )
        }*/,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(
            topStart = 28.dp,
            topEnd = 28.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        )

    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.blurBlue.copy(0.95f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.Center
                ) {
                    Text(
                        text = stringResource(R.string.servers),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.white
                        )
                    )
                }


//                Log.d("TAG",mainViewModel.pings.toString())
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp)
                ) {
                    itemsIndexed(configList) { index, config ->
                        /**
                         * find the last item
                         */
                        val lastItem = last == index

                        /**
                         * find the corresponding pings (delays) from pingListFromDB
                         * corresponding means if id of that pingListFromDB is the same as the config.id
                         * this means that they are the same and the ping (delay) is for that config with the same id
                         */
                        val correspondingPing = pingListFromDB.find { it.id == config.id } // Find ping with matching id
                        val delayText = if (correspondingPing != null) {
                            "${correspondingPing.delay} ms ping" // Use delay from matching ping
                        } else {
                            "-1 ms ping" // Display if no matching ping found
                        }
                        ServerItem(
                            config = config,
                            lastItem = lastItem,
                            isSelected = config == mainViewModel.selectedServer,
                            delayText = delayText,
                        ) {
                            scope.launch {
                                if (mainViewModel.selectedServer?.id == config.id){
                                    activity.showToast(activity.getString(R.string.server_already_selected))
                                }else{
                                    /**
                                     * this part is for clicking on th server and changing the server
                                     * the reason i put a delay here is because if we do not do this
                                     * the server bottom sheet will close and quickly opens again
                                     * so we put a slight delay before we start the v2ray with the new server (config)
                                     */
                                    mainViewModel.selectedServer = config
                                    dataStoreViewModel.saveSelectedServerId(config.id)
                                    mainViewModel.stopV2ray(activity)
                                    sheetState.bottomSheetState.hide()
                                    delay(500)
                                    mainViewModel.connecting.value = true
                                    mainViewModel.startV2ray(
                                        config.name,
                                        config.decodedConfig,
                                        activity,
                                        arrayListOf("pro.sparrow_team.qq")
                                    )
                                    dataStoreViewModel.saveConnectionState(true)
                                }


                            }
                        }
                    }
                }
            }
        }


    }

}