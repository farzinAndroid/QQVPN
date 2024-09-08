package pro.sparrow_team.qq.ui.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.qq.R
import pro.sparrow_team.qq.ui.theme.disconnectedColor
import pro.sparrow_team.qq.ui.theme.white
import pro.sparrow_team.qq.utils.MySpacerHeight
import pro.sparrow_team.qq.viewmodel.MainViewModel

@Composable
fun LogoSection() {

            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Logo()

                MySpacerHeight(height = 16.dp)

                Text(
                    text = stringResource(R.string.qq_vpn),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.almarai_regular)),
                    color = MaterialTheme.colorScheme.white
                )


                MySpacerHeight(height = 12.dp)


                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                brush = MaterialTheme.colorScheme.disconnectedColor,
                            )
                        ) {
                            append(stringResource(R.string.quality))
                        }

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.white,
                            )
                        ) {
                            append(" ${stringResource(R.string.and)} ")
                        }

                        withStyle(
                            style = SpanStyle(
                                brush = MaterialTheme.colorScheme.disconnectedColor,
                            )
                        ) {
                            append(stringResource(R.string.quality))
                        }
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.almarai_regular)),
                )

                LoadingSection(modifier = Modifier)


            }

}