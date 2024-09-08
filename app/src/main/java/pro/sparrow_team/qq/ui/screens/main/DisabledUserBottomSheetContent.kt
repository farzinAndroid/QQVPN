package pro.sparrow_team.qq.ui.screens.main

import android.content.Context
import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.screens.component.CustomButton
import pro.sparrow_team.qq.ui.theme.disconnectedColor
import pro.sparrow_team.qq.ui.theme.updateCardBlue
import pro.sparrow_team.qq.ui.theme.white
import pro.sparrow_team.qq.utils.MySpacerHeight
import pro.sparrow_team.qq.viewmodel.MainViewModel


@Composable
fun DisabledUserBottomSheetContent(
    mainViewModel: MainViewModel,
    context: Context,
) {

    Card(
        modifier = Modifier
            .fillMaxHeight(0.36f)
            .clickable(
                enabled = false,
                onClick = {},
                indication = null,
                interactionSource = remember { MutableInteractionSource() }),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.updateCardBlue.copy(0.95f)
        ),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.disabled_user_reg_page),
                fontFamily = FontFamily(Font(R.font.almarai_bold)),
                color = MaterialTheme.colorScheme.white,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 24.dp)
            )

            MySpacerHeight(height = 22.dp)

            Text(
                text = mainViewModel.status.descriptions.reject ?: stringResource(R.string.disabled_user_reg_caption),
                fontFamily = FontFamily(Font(R.font.almarai_regular)),
                color = MaterialTheme.colorScheme.white,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )

            MySpacerHeight(height = 24.dp)

            CustomButton(
                text = stringResource(R.string.contact_us),
                textColor = MaterialTheme.colorScheme.white,
                onClick = {
                    mainViewModel.openGmail(mainViewModel.status.email ?: "info@sparrow-team.pro",context)
                },
                shapes = RoundedCornerShape(8.dp),
                borderColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                backgroundBrush = MaterialTheme.colorScheme.disconnectedColor
            )
        }
    }
}