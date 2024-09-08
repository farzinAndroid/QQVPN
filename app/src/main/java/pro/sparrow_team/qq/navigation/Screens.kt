package pro.sparrow_team.qq.navigation

sealed class Screens(val route:String) {

    data object MainScreen : Screens(route = "main_screen")
    data object LoginScreen : Screens(route = "login_screen")
    data object SplashScreen : Screens(route = "splash_screen")

}