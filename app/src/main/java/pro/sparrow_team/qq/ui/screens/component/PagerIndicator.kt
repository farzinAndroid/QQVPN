package pro.sparrow_team.qq.ui.screens.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    count: Int,
    circleSpacing: Dp,
    width: Dp,
    height: Dp,
    activeLineWidth: Dp,
    pagerState: PagerState,
) {

    // Calculate the total width required for the indicators
    val totalWidth = with(LocalDensity.current) {
        (count * width.toPx() + (count - 1) * circleSpacing.toPx()).toDp()
    }

    Canvas(modifier = Modifier
        .width(totalWidth)
        .height(6.dp)
    ) {
        val spacing = circleSpacing.toPx()
        val dotWidth = width.toPx()
        val dotHeight = height.toPx()

        val activeDotWidth = activeLineWidth.toPx()
        var x = 0f
        val y = center.y

        repeat(count) { i ->
            val posOffset = pagerState.pageOffset
            val dotOffset = posOffset % 1
            val current = posOffset.toInt()

            val factor = (dotOffset * (activeDotWidth - dotWidth))

            val calculatedWidth = when {
                i == current -> activeDotWidth - factor
                i - 1 == current || (i == 0 && posOffset > count - 1) -> dotWidth + factor
                else -> dotWidth
            }

            drawIndicator(x, y, calculatedWidth, dotHeight, CornerRadius(10f, 10f))
            x += calculatedWidth + spacing
        }
    }

}

private fun DrawScope.drawIndicator(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    radius: CornerRadius,
) {
    val rect = RoundRect(
        x,
        y - height / 2,
        x + width,
        y + height / 2,
        radius
    )
    val path = Path().apply { addRoundRect(rect) }
    drawPath(path = path, color = Color.White)
}

// To get scroll offset
@OptIn(ExperimentalFoundationApi::class)
val PagerState.pageOffset: Float
    get() = this.currentPage + this.currentPageOffsetFraction


// To get scrolled offset from snap position
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}
