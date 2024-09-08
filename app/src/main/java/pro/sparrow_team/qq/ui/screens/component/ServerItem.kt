package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blongho.country_data.World
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.model.mainscreen.DecodedConfig
import pro.sparrow_team.qq.ui.theme.unselectedServerRadioButtonColor
import pro.sparrow_team.qq.ui.theme.white
import pro.sparrow_team.qq.utils.MySpacerHeight
import pro.sparrow_team.qq.utils.MySpacerWidth

@Composable
fun ServerItem(
    config: DecodedConfig,
    lastItem:Boolean,
    isSelected:Boolean,
    delayText:String,
    onClick:()->Unit
) {


    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {


            Row(
                modifier = Modifier
                    .padding(start = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {

                Image(
                    painter = painterResource(World.getFlagOf(config.location)),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                MySpacerWidth(width = 10.dp)

                Text(
                    text = config.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        color = MaterialTheme.colorScheme.white,
                        fontWeight = FontWeight.Medium
                    )
                )

            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = delayText,
                    color = MaterialTheme.colorScheme.white,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.almarai_regular)),
                        fontSize = 12.sp
                    )
                )

                MySpacerWidth(width = 20.dp)

                CustomRadioButton(isSelected)
            }


        }

        MySpacerHeight(height = 4.dp)

        if (!lastItem){
            Spacer(modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.unselectedServerRadioButtonColor.copy(0.2f))
            )
        }



    }




}

