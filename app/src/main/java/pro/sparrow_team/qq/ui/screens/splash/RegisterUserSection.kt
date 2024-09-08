package pro.sparrow_team.qq.ui.screens.splash

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.data.remote.NetworkResult
import pro.sparrow_team.qq.model.mainscreen.UpdateTokenModel
import pro.sparrow_team.qq.model.mainscreen.UserRegisterModel
import pro.sparrow_team.qq.utils.showToast
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import pro.sparrow_team.qq.viewmodel.MainViewModel

@Composable
fun RegisterUserSection(
    mainViewmodel: MainViewModel,
    dataStoreViewModel: DataStoreViewModel,
    context: Context,
    isUserRegistrationComplete:(Boolean)->Unit,
    navController: NavController,
    uuid:String
) {

    var registerResponse by remember {
        mutableStateOf("")
    }

    var isUserRegistered by remember {
        mutableStateOf(false)
    }

    val registerResponseResult by mainViewmodel.registerUser.collectAsState()
    when (registerResponseResult) {
        is NetworkResult.Success -> {
            registerResponse = registerResponseResult.data?.message ?: ""
            dataStoreViewModel.saveRegisterState(true)
            isUserRegistered = true
        }

        is NetworkResult.Loading -> {}
        is NetworkResult.Error -> {
            registerResponse = "Token is either used or not valid!"

        }
    }

    LaunchedEffect(registerResponse) {
        if (registerResponse != "") {
//            context.showToast(registerResponse)
            when (registerResponse) {
                "Token is either used or not valid!" -> {
                    dataStoreViewModel.saveRegisterState(false)
                    isUserRegistered = false
                }

                "registered successfully!" -> {
                    dataStoreViewModel.saveRegisterState(true)
                    isUserRegistered = true
                }
            }
        }
    }



    /**
     * section for updating the user token the results are in the
     *      logcat
     */
    val updateUserToken by mainViewmodel.updateUserToken.collectAsState()
    when (updateUserToken) {
        is NetworkResult.Success -> {
//            Log.e("TAG", "update token : ${updateUserToken.data?.message.toString()}")
            isUserRegistered = true
        }

        is NetworkResult.Error -> {
//            Log.e("TAG", "update token : ${updateUserToken.message.toString()}")
        }

        is NetworkResult.Loading -> {}
    }


    /**
     * observer on the UUID
     * get the FCM token from firebase and register the user
     */
    LaunchedEffect(uuid) {
        mainViewmodel.loadingText = context.getString(R.string.registering)
        mainViewmodel.getFCMToken(
            onSuccess = {token->
//                Log.e("TAG", "token is here : $token")
                if (uuid != "" && mainViewmodel.status.reject != true) {
                    if (!dataStoreViewModel.getRegisterState()) {
                        mainViewmodel.registerUser(
                            UserRegisterModel(
                                uuid,
                                token
                            )
                        )
                    } else {
                        mainViewmodel.updateUserToken(
                            uuid,
                            UpdateTokenModel(token)
                        )
                        isUserRegistered = true
                    }
                }
            },
            onError = {
//                Log.e("TAG", it.message.toString())
                context.showToast("There is a problem with Registering try again later")
            }
        )

    }


    /**
     * after registering the user or updating the token
     * we will check for the user's authenticity
     */
    if (isUserRegistered){
        LaunchedEffect(true) {
            mainViewmodel.loadingText = context.getString(R.string.authenticating)
//            Log.i("TAG","isUserRegistered from RegisterUserSection $isUserRegistered")
                mainViewmodel.getUserAuthenticity(uuid)
                mainViewmodel.userAuthenticity.collectLatest {
                    when (it) {
                        is NetworkResult.Success -> {
                            isUserRegistrationComplete(true)
//                            Log.e("TAG", "userAuthenticity OK")
                        }

                        is NetworkResult.Loading -> {
                            isUserRegistrationComplete(false)
                        }

                        is NetworkResult.Error -> {
                            isUserRegistrationComplete(false)
//                            Log.e("TAG", "userAuthenticity error")
                        }
                    }
                }

        }
    }

}