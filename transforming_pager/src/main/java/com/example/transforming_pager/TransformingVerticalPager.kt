package com.example.transforming_pager

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerDefaults
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import dev.chrisbanes.snapper.ExperimentalSnapperApi


@OptIn(ExperimentalSnapperApi::class, ExperimentalPagerApi::class)
@Composable
fun TransformingVerticalPager(
    count: Int,
    modifier: Modifier = Modifier,
    state: PagerState = rememberPagerState(),
    pagerType: PagerType,
    reverseLayout: Boolean = false,
    itemSpacing: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    flingBehavior: FlingBehavior = PagerDefaults.flingBehavior(
        state = state,
        endContentPadding = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
    ),
    key: ((page: Int) -> Any)? = null,
    content: @Composable PagerScope.(page: Int) -> Unit,
) {

    VerticalPager(
        count = count,
        modifier = modifier,
        state = state,
        reverseLayout = reverseLayout,
        itemSpacing = itemSpacing,
        contentPadding = contentPadding,
        horizontalAlignment = verticalAlignment,
        flingBehavior = flingBehavior,
        key = key
    ) { page ->
        BoxWithConstraints() {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pageEffect(
                        pagerType = pagerType,
                        pagerScope = this@VerticalPager,
                        constraints = constraints,
                        page = page,
                        isVertical = true
                    ),
                contentAlignment = Alignment.Center
            ) {
                content(page)
            }
        }
    }
}

