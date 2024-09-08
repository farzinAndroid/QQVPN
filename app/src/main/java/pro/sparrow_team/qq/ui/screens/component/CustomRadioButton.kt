package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import pro.sparrow_team.qq.ui.theme.disconnectedColor
import pro.sparrow_team.qq.ui.theme.unselectedServerRadioButtonColor
import pro.sparrow_team.qq.ui.theme.white

@Composable
fun CustomRadioButton(isSelected: Boolean) {

    val radioButtonModifier = if (isSelected) {
        Modifier
            .padding(end = 24.dp)
            .size(25.dp)
            .clip(CircleShape)
            .border(1.dp, MaterialTheme.colorScheme.white, CircleShape)
            .background(MaterialTheme.colorScheme.disconnectedColor)
    } else {
        Modifier
            .padding(end = 24.dp)
            .size(25.dp)
            .clip(CircleShape)
            .border(
                1.dp,
                MaterialTheme.colorScheme.unselectedServerRadioButtonColor.copy(0.2f),
                CircleShape
            )
            .background(MaterialTheme.colorScheme.unselectedServerRadioButtonColor.copy(0.05f))
    }

    Box(
        modifier = radioButtonModifier
    )

}