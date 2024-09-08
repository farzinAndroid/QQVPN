package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.theme.blackAndTransparentVertical
import pro.sparrow_team.qq.ui.theme.cancelRed
import pro.sparrow_team.qq.ui.theme.disconnectedColor
import pro.sparrow_team.qq.ui.theme.red
import pro.sparrow_team.qq.ui.theme.white

@Composable
fun UpdateButtonSection(
    onUpdateClicked:()->Unit,
    onCancelClicked:()->Unit,
    cancelEnabled:Boolean
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {

        CustomButton(
            text = stringResource(R.string.cancel),
            textColor =if (cancelEnabled) MaterialTheme.colorScheme.red else Color.Gray,
            onClick = onCancelClicked,
            shapes = RoundedCornerShape(8.dp),
            borderColor = if (cancelEnabled) MaterialTheme.colorScheme.red else Color.Gray,
            backgroundBrush = MaterialTheme.colorScheme.blackAndTransparentVertical,
            backgroundColor = if (cancelEnabled) MaterialTheme.colorScheme.cancelRed else Color.DarkGray,
            borderStroke = 1.dp,
            enabled = cancelEnabled,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        CustomButton(
            text = stringResource(R.string.update),
            textColor =MaterialTheme.colorScheme.white,
            onClick = onUpdateClicked,
            shapes = RoundedCornerShape(8.dp),
            borderColor = Color.Transparent,
            backgroundBrush = MaterialTheme.colorScheme.disconnectedColor,
            backgroundColor = Color.Transparent,
            borderStroke = 1.dp,
            modifier = Modifier.align(Alignment.CenterEnd)
        )





    }

}