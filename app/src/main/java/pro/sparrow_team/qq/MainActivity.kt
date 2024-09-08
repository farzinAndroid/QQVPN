package pro.sparrow_team.qq

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.blongho.country_data.World
import dagger.hilt.android.AndroidEntryPoint
import dev.dev7.lib.v2ray.V2rayController
import pro.sparrow_team.qq.navigation.NavGraph
import pro.sparrow_team.qq.navigation.Screens
import pro.sparrow_team.qq.ui.theme.SparrowTheme
import pro.sparrow_team.qq.utils.ChangeStatusBarAndNavigationBarColor2
import pro.sparrow_team.qq.utils.Constants
import pro.sparrow_team.qq.viewmodel.DataStoreViewModel
import pro.sparrow_team.qq.viewmodel.MainViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val dataStoreViewmodel: DataStoreViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        V2rayController.init(
            this@MainActivity,
            R.drawable.app_logo,
            this.getString(R.string.app_name)
        )
        World.init(applicationContext)
        setContent {
            val isDarkMode = isSystemInDarkTheme()
            SparrowTheme {
                ChangeStatusBarAndNavigationBarColor2(
                    isDarkMode = isDarkMode,
                    context = this
                )

//                Log.e("TAG","from main activity ${V2rayController.getCoreVersion()}")
                CompositionLocalProvider(LocalLayoutDirection.provides(LayoutDirection.Ltr)) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                        val navController = rememberNavController()

                        NavGraph(
                            navHostController = navController,
                            startDestination = Screens.SplashScreen.route,
                            paddingValues = innerPadding,
                            mainViewModel = mainViewModel,
                            dataStoreViewModel = dataStoreViewmodel
                        )
                    }
                }

            }


        }
    }

    /*override fun onDestroy() {
        super.onDestroy()
        application.unregisterReceiver(viewModel.receiver)
    }*/
}