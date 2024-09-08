package pro.sparrow_team.qq.navigation

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pro.sparrow_team.qq.ui.screens.login.LoginScreen
import pro.sparrow_team.qq.ui.screens.main.MainScreen
import pro.sparrow_team.qq.ui.screens.splash.SplashScreen
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import pro.sparrow_team.qq.viewmodel.MainViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    startDestination: String,
    paddingValues: PaddingValues,
    mainViewModel: MainViewModel,
    dataStoreViewModel: DataStoreViewModel
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = Modifier
    ) {

        composable(Screens.MainScreen.route) {
            MainScreen(navController = navHostController,mainViewModel,dataStoreViewModel)
        }

        composable(Screens.LoginScreen.route, exitTransition = {
            fadeOut(animationSpec = tween(500))
        }) {
            LoginScreen(mainViewModel)
        }

        composable(Screens.SplashScreen.route){
            SplashScreen(navController =navHostController,mainViewModel,dataStoreViewModel)
        }

    }

}