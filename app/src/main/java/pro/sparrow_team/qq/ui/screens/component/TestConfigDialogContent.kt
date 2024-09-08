package pro.sparrow_team.qq.ui.screens.component

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.viewmodel.ConnectionState
import pro.sparrow_team.qq.ui.theme.mainBackgroundColor
import pro.sparrow_team.qq.ui.theme.white
import pro.sparrow_team.qq.utils.MySpacerHeight
import pro.sparrow_team.qq.viewmodel.MainViewModel

@Composable
fun TestConfigDialogBox(
    activity: Activity,
    mainViewModel: MainViewModel,
    connectionState: ConnectionState
) {

    var config by remember { mutableStateOf("Constants.TEST_SERVER_CONFIG") }
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.4f)
            .background(MaterialTheme.colorScheme.mainBackgroundColor),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = config,
            onValueChange = { config = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 8.dp)
                .padding(vertical = 4.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.white,
                unfocusedTextColor = MaterialTheme.colorScheme.white,
                disabledContainerColor = MaterialTheme.colorScheme.mainBackgroundColor,
                focusedContainerColor = MaterialTheme.colorScheme.mainBackgroundColor,
                unfocusedContainerColor = MaterialTheme.colorScheme.mainBackgroundColor
            ),
            shape = Shapes().medium,
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(R.string.insert_test_config),
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            },
            textStyle = TextStyle(
                fontSize = 16.sp
            )
        )

        MySpacerHeight(height = 10.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    scope.launch {
                        if (connectionState == ConnectionState.Disconnected){
                            /*mainViewModel.connecting.value = true
                            mainViewModel.showDialog.value = false
                            Log.e("TAG"," ff ${mainViewModel.connecting.value.toString()}")
                            // Start V2ray connection and delay in parallel
                            val startV2rayJob = async {
                                delay(5000)
                                mainViewModel.startTestV2ray("", config, activity)
                                mainViewModel.connecting.value = false
                                Log.e("TAG"," ff ${mainViewModel.connecting.value.toString()}")
                            }
                            startV2rayJob.await() // Wait for V2ray connection to finish*/
                            mainViewModel.connecting.value = true
//                            Log.e("TAG","connecting ${mainViewModel.connecting.value.toString()}")
//                            mainViewModel.showDialog.value = false
                            delay(5000)
                            mainViewModel.connecting.value = false

//                            Log.e("TAG","connecting ${mainViewModel.connecting.value.toString()}")
//                            Log.e("TAG","farzin")

                        }else{
                            mainViewModel.stopV2ray(activity)
                            mainViewModel.showDialog.value = false
                        }
                    }

                },
                modifier = Modifier
                    .weight(0.5f)
                    .height(60.dp)
            ) {
                Text(
                    text = stringResource(R.string.confirm),
                    color = MaterialTheme.colorScheme.white,
                    fontSize = 16.sp,
                )
            }

            Button(
                onClick = {
                    config = ""
                },
                modifier = Modifier
                    .weight(0.5f)
                    .height(60.dp)
            ) {
                Text(
                    text = stringResource(R.string.clear),
                    color = MaterialTheme.colorScheme.white,
                    fontSize = 16.sp,
                )
            }

        }

    }

}