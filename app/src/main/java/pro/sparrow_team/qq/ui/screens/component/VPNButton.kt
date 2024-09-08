package pro.sparrow_team.qq.ui.screens.component

import android.annotation.SuppressLint
import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import pro.sparrow_team.qq.viewmodel.ConnectionState
import pro.sparrow_team.qq.ui.theme.green
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun VPNButton(
    connectedColor:Brush,
    borderColor:Brush,
    switchIcon:Painter,
    icon:ImageBitmap,
    connectionState: ConnectionState,
    onClick:()->Unit,
    modifier: Modifier = Modifier,
    connecting:Boolean,
    isButtonEnabled:Boolean
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    val radius = 70.dp


    BoxWithConstraints(modifier = modifier.size(radius * 2), contentAlignment = Alignment.Center) {


        Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                if (isButtonEnabled){
                    detectTapGestures {
                        val canvasCenter = Offset(
                            constraints.maxWidth / 2f,
                            constraints.maxHeight / 2f
                        ) // Store the center for convenience
                        val tapX = it.x
                        val tapY = it.y
                        val distance = distanceTo(
                            canvasCenter,
                            tapX,
                            tapY
                        ) // Calculate distance using helper function
                        if (distance <= radius.toPx()) {
                            onClick()
                        }
                    }
                }

            }
        ) {
            val centerX = size.width / 2
            val centerY = size.height / 2

            // Draw the circular path
            drawCircle(
                if (connectionState == ConnectionState.Connected) connectedColor else borderColor,
                style = Stroke(
                    width = 2.dp.toPx()
                ),
                radius = radius.toPx(),
                center = Offset(centerX, centerY)
            )


            // Calculate the position of the icon on the circle's border
            val angleInRadians = Math.toRadians(rotation.toDouble())
            val iconX =
                centerX + radius.toPx() * cos(angleInRadians).toFloat() - icon.width / 4
            val iconY =
                centerY + radius.toPx() * sin(angleInRadians).toFloat() - icon.height / 4

            // Draw the icon at the calculated position
            val a = Offset(iconX,iconY)
            if (connecting){
                drawImage(
                    image = icon,
                    dstSize = IntSize(24.dp.toPx().toInt(),24.dp.toPx().toInt()),
                    dstOffset = IntOffset(a.x.toInt(),a.y.toInt())
                )
            }


        }



        Image(
            painter = switchIcon,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(44.dp),
            colorFilter = if (connectionState == ConnectionState.Connected) ColorFilter.tint(MaterialTheme.colorScheme.green) else null
        )




    }

}

// Helper function to calculate distance
fun distanceTo(center: Offset, x: Float, y: Float): Float {
    val dx = x - center.x
    val dy = y - center.y
    return kotlin.math.sqrt((dx.pow(2)) + (dy.pow(2)))
}
fun Float.toRadians() = (this * Math.PI) / 180




fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0f.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + spreadPixel)
            val bottomPixel = (this.size.height + spreadPixel)

            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)



