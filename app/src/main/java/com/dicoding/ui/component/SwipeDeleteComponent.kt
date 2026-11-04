package com.dicoding.ui.component

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateToWithDecay
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.overscroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.dicoding.R
import com.dicoding.ui.theme.SubmissionComposeTheme

enum class DragPosition { FULL, HALF, GONE }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeDelete(
    modifier: Modifier = Modifier,
    onDeleted: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val swipeState = remember {
        AnchoredDraggableState(
            initialValue = DragPosition.FULL,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            snapAnimationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            ),
            decayAnimationSpec = exponentialDecay(
                frictionMultiplier = 2.1f
            ),
        )
    }
    val anchors = remember(density) {
        DraggableAnchors {
            DragPosition.FULL at 0f
            DragPosition.HALF at 250f
            DragPosition.GONE at 350f
        }
    }

    val swipeOverScroll = ScrollableDefaults.overscrollEffect()

    val swipeProgress = swipeState.offset
    var alpha = 1f - (swipeProgress / 500f).coerceIn(0f, 1f)

    SideEffect {
        swipeState.updateAnchors(anchors)
    }

    LaunchedEffect(swipeState) {
        snapshotFlow { swipeState.settledValue }
            .collect {
                if (it == DragPosition.GONE) onDeleted()
                swipeState.animateToWithDecay(DragPosition.FULL, 1.2f)
            }
    }
    Box(
        modifier = modifier
            .testTag("SwipeDelete")
            .alpha(alpha)
            .anchoredDraggable(
                state = swipeState,
                orientation = androidx.compose.foundation.gestures.Orientation.Horizontal,
            )
            .overscroll(swipeOverScroll)
            .offset {
                IntOffset(
                    x = swipeState
                        .requireOffset()
                        .toInt(), 0
                )
            }
    ) {
        content()
    }

}

@Preview
@Composable
private fun SwipeDeletePreview() {
    var counter by remember { mutableIntStateOf(0) }
    SubmissionComposeTheme {
        Column {
            SwipeDelete(
                onDeleted = {
                    counter++
                }) {
                FlagCard(
                    painter = painterResource(R.drawable.indonesia),
                    countryName = "Indonesia $counter",
                )
            }
        }
    }
}
