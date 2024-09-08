package pro.sparrow_team.qq.ui.screens.splash

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import pro.sparrow_team.qq.R

@Composable
fun LoadingSection(modifier: Modifier = Modifier) {

    val lottieComposition = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading)
    )

    LottieAnimation(
        composition = lottieComposition.value,
        iterations = Int.MAX_VALUE,
        modifier = modifier.size(100.dp),
        speed = 1f,
    )


}