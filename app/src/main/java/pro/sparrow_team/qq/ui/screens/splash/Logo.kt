package pro.sparrow_team.qq.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.screens.component.ShadowCircle
import pro.sparrow_team.qq.ui.theme.vpnButtonColor2

@Composable
fun Logo() {

    Box{
        ShadowCircle(
            modifier = Modifier.align(Alignment.Center),
            blurRadius = 300.dp,
            spread = 100.dp,
            shadowColor = MaterialTheme.colorScheme.vpnButtonColor2.copy(0.1f)
        )
       /* Image(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = "",
            modifier = Modifier
                .size(215.dp)
        )*/

         Image(
            painter = painterResource(R.drawable.logo_border),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
        )

        Image(
            painter = painterResource(R.drawable.logo_rocket),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
        )

    }
}