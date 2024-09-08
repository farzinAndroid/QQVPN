package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.viewmodel.MainViewModel

@Composable
fun EarthAnimation(
    mainViewModel: MainViewModel
) {

    /*val lottieComposition = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.earth_animation2)
    )
    val connecting = mainViewModel.connecting
    LottieAnimation(
        composition = lottieComposition.value,
        iterations = Int.MAX_VALUE,
        modifier = Modifier
            .fillMaxSize(),
        speed = 1f,
        contentScale = ContentScale.Fit,
        isPlaying = connecting.value
    )*/

}