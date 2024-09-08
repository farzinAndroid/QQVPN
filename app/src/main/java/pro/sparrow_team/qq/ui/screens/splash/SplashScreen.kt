package pro.sparrow_team.qq.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.data.remote.NetworkResult
import pro.sparrow_team.qq.model.mainscreen.Status
import pro.sparrow_team.qq.navigation.Screens
import pro.sparrow_team.qq.ui.theme.mainBackgroundColor
import pro.sparrow_team.qq.viewmodel.ConnectionState
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import pro.sparrow_team.qq.viewmodel.MainViewModel

@Composable
fun SplashScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    dataStoreViewModel: DataStoreViewModel,
) {


    /**
     * observe on the connectionState
     * and save the connection state in dataStore
     * we may not need this part but i put as an insurance
     */
    LaunchedEffect(mainViewModel.connectionState) {
        val connectionBoolean = mainViewModel.connectionState.value == ConnectionState.Connected
        dataStoreViewModel.saveConnectionState(connectionBoolean)
//        Log.e("TAG", "from Splash ${mainViewModel.connectionState.value.toString()}")
    }

    val context = LocalContext.current

    var isStatusApiCallFinished by remember {
        mutableStateOf(false)
    }

    var isUserRegistrationComplete by remember {
        mutableStateOf(false)
    }
    var isConfigProcessComplete by remember {
        mutableStateOf(false)
    }
    var uuid by remember {
        mutableStateOf("")
    }

    CreateNewUUID(
        dataStoreViewModel = dataStoreViewModel,
        onUUIDCreated = {
            uuid = it
        }
    )

    Splash(mainViewModel)


    LaunchedEffect(true) {
        getAllApiCalls(mainViewModel)
        mainViewModel.statusFlow.collectLatest {
            mainViewModel.loadingText = context.getString(R.string.loading)
            when (it) {
                is NetworkResult.Success -> {
                    mainViewModel.status = it.data ?: Status()
                    mainViewModel.isApiHealthy = true
                    if (it.data?.reject == true){
                        navController.navigate(Screens.MainScreen.route) {
                            popUpTo(Screens.SplashScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                    isStatusApiCallFinished = true
                }

                is NetworkResult.Loading -> {
                    mainViewModel.isApiHealthy = true
                    isStatusApiCallFinished = false
                }

                is NetworkResult.Error -> {
                    mainViewModel.isApiHealthy = false
                    isStatusApiCallFinished = true
//                    Log.e("TAG", "get status error: ${it.message}")
                }
            }
        }

    }

    /**
     * if the first api is called and correct
     * we will get on with registering user
     */
    if (isStatusApiCallFinished) {
        RegisterUserSection(
            mainViewmodel = mainViewModel,
            dataStoreViewModel = dataStoreViewModel,
            context = context,
            isUserRegistrationComplete = {
                isUserRegistrationComplete = it
//                Log.w("TAG", "isUserRegistrationComplete from splash $it")
            },
            navController = navController,
            uuid = uuid
        )
    }


    /**
     * GetConfigurationsSection
     */
    GetConfigurationsSection(
        isUserRegistered = isUserRegistrationComplete,
        mainViewModel = mainViewModel,
        dataStoreViewModel = dataStoreViewModel,
        isConfigProcessComplete = {
            isConfigProcessComplete = it
        },
        context = context,
        uuid = uuid
    )


    /**
     * change if you have a better way
     * in this part we observe isStatusApiCallFinished,isUserRegistrationComplete,isConfigProcessComplete
     * if api has problems we will go to main screen and handle it
     * if api is healthy and first api is called and register is complete and configProcess is also complete
     * we will go to the next screen
     */
    LaunchedEffect(isStatusApiCallFinished, isUserRegistrationComplete, isConfigProcessComplete) {
        if (!mainViewModel.isApiHealthy) {
            navController.navigate(Screens.MainScreen.route) {
                popUpTo(Screens.SplashScreen.route) {
                    inclusive = true
                }
            }
        } else {
            if (isStatusApiCallFinished && isUserRegistrationComplete && !isConfigProcessComplete) {
//                Log.e("TAG", "There is a problem try again later")
//            context.showToast("There is a problem try again later")
            } else if (isStatusApiCallFinished && isUserRegistrationComplete && isConfigProcessComplete) {
//                Log.e("TAG", "go to main and user is legit")
                navController.navigate(Screens.MainScreen.route) {
                    popUpTo(Screens.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }


}

@Composable
private fun Splash(mainViewModel: MainViewModel) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackgroundColor),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(R.drawable.earth_opacity),
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomStart),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        LogoSection()

        LoadingText(
            loadingText = mainViewModel.loadingText,
            modifier = Modifier
                .padding(top = 340.dp)
                .align(Alignment.Center)

        )


    }


}

fun getAllApiCalls(mainViewModel: MainViewModel) {
    mainViewModel.getStatus()
}