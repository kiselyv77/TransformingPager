package com.example.transforming_pager

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.abs


@OptIn(ExperimentalPagerApi::class)
internal fun Modifier.pageEffect(
    pagerType: PagerType,
    pagerScope: PagerScope,
    page: Int,
    constraints: Constraints,
    isVertical: Boolean
): Modifier {
    if (isVertical) {
        return when (pagerType) {
            is PagerType.Cube -> {
                graphicsLayer {
                    transformOrigin = TransformOrigin(
                        pivotFractionY = if (pagerScope.calculateCurrentOffsetForPage(page) > 0) {
                            1f
                        } else 0f,
                        pivotFractionX = 0.5f
                    )
                    rotationX = pagerScope.calculateCurrentOffsetForPage(page) * 90
                    cameraDistance = (scaleY * 25)
                }
            }
            is PagerType.Flip -> {
                graphicsLayer {
                    val rotation = 180f * pagerScope.calculateCurrentOffsetForPage(page)

                    translationY = pagerScope.calculateCurrentOffsetForPage(page) * constraints.maxHeight

                    alpha = if (rotation > 90f || rotation < -90f) 0f else 1f

                    rotationX = rotation

                }
            }
            is PagerType.Rotate -> {
                graphicsLayer {
                    val rotation = pagerType.rotationValue * pagerScope.calculateCurrentOffsetForPage(page)
                    translationY = -constraints.maxHeight / 3 * pagerScope.calculateCurrentOffsetForPage(page)
                    rotationZ = rotation
                }
            }
            is PagerType.Stack -> {
                graphicsLayer {
                    translationY = if (pagerScope.calculateCurrentOffsetForPage(page) < 0) {
                        0f
                    } else {
                        this@pageEffect.zIndex(1f)
                        constraints.maxHeight * pagerScope.calculateCurrentOffsetForPage(page)
                    }
                }
            }
            is PagerType.Zoom -> {
                graphicsLayer {
                    val scale =
                        if (pagerScope.calculateCurrentOffsetForPage(page) < 0) pagerScope.calculateCurrentOffsetForPage(
                            page
                        ) + 1f else abs(
                            1f - pagerScope.calculateCurrentOffsetForPage(page)
                        )
                    scaleX = scale
                    scaleY = scale
                    translationY = constraints.maxHeight * pagerScope.calculateCurrentOffsetForPage(page)
                    alpha =
                        if (pagerScope.calculateCurrentOffsetForPage(page) < -1f || pagerScope.calculateCurrentOffsetForPage(
                                page
                            ) > 1f
                        ) 0f else 1f - (scale - 1f)
                }
            }
            is PagerType.Drawer -> {
                graphicsLayer {
                    if (pagerScope.calculateCurrentOffsetForPage(page) <= 0) translationY = 0f
                    else if (pagerScope.calculateCurrentOffsetForPage(page) <= 1)
                        translationY = constraints.maxHeight / 2 * pagerScope.calculateCurrentOffsetForPage(page)
                }

            }
            is PagerType.FromDeep -> {
                graphicsLayer {
                    if (pagerScope.calculateCurrentOffsetForPage(page) >= 0f) {
                        translationY = 0f
                        scaleX = 1f
                        scaleY = 1f
                    } else if (pagerScope.calculateCurrentOffsetForPage(page) <= 1f) {
                        val scaleFactor = pagerType.scaleFactor + (1 - pagerType.scaleFactor) * (1 - abs(
                            pagerScope.calculateCurrentOffsetForPage(page)
                        ))
                        translationY = scaleX * -pagerScope.calculateCurrentOffsetForPage(page)
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                }
            }
            is PagerType.Compression -> {
                graphicsLayer {
                    transformOrigin = TransformOrigin(
                        pivotFractionY = if (pagerScope.calculateCurrentOffsetForPage(page) < 0) 0f else scaleY,
                        pivotFractionX = 0.5f

                    )
                    scaleY =
                        if (pagerScope.calculateCurrentOffsetForPage(page) < 0) 1f + pagerScope.calculateCurrentOffsetForPage(
                            page
                        ) else 1f - pagerScope.calculateCurrentOffsetForPage(page)
                }
            }
            is PagerType.Scale -> {
                graphicsLayer {
                    transformOrigin = TransformOrigin(
                        pivotFractionY = (if (pagerScope.calculateCurrentOffsetForPage(page) < 0) 0 else scaleY).toFloat(),
                        pivotFractionX = 0.5f
                    )

                    val scale =
                        if (pagerScope.calculateCurrentOffsetForPage(page) < 0) 1f + pagerScope.calculateCurrentOffsetForPage(
                            page
                        ) else 1f - pagerScope.calculateCurrentOffsetForPage(page)
                    scaleX = scale
                    scaleY = scale
                }
            }
        }
    } else {
        return when (pagerType) {
            is PagerType.Cube -> {
                graphicsLayer {
                    transformOrigin = TransformOrigin(
                        pivotFractionX = if (pagerScope.calculateCurrentOffsetForPage(page) > 0) {
                            1f
                        } else 0f,
                        pivotFractionY = 0.5f
                    )
                    rotationY = pagerScope.calculateCurrentOffsetForPage(page) * -90
                    cameraDistance = (scaleX * 25)
                }
            }
            is PagerType.Flip -> {
                graphicsLayer {
                    val rotation = -180f * pagerScope.calculateCurrentOffsetForPage(page)

                    translationX = pagerScope.calculateCurrentOffsetForPage(page) * constraints.maxWidth

                    alpha = if (rotation > 90f || rotation < -90f) 0f else 1f

                    rotationY = rotation

                }
            }
            is PagerType.Rotate -> {
                graphicsLayer {
                    val rotation = -45f * pagerScope.calculateCurrentOffsetForPage(page)
                    translationX = -constraints.maxWidth * pagerScope.calculateCurrentOffsetForPage(page)
                    rotationZ = rotation
                }
            }
            is PagerType.Stack -> {
                graphicsLayer {
                    translationX =
                        if (pagerScope.calculateCurrentOffsetForPage(page) < 0) {
                            0f
                        } else {
                            constraints.maxWidth * pagerScope.calculateCurrentOffsetForPage(page)
                        }
                }
            }
            is PagerType.Zoom -> {
                graphicsLayer {
                    val scale =
                        if (pagerScope.calculateCurrentOffsetForPage(page) < 0) pagerScope.calculateCurrentOffsetForPage(
                            page
                        ) + 1f else abs(
                            1f - pagerScope.calculateCurrentOffsetForPage(page)
                        )
                    scaleX = scale
                    scaleY = scale
                    translationX = constraints.maxWidth * pagerScope.calculateCurrentOffsetForPage(page)
                    alpha =
                        if (pagerScope.calculateCurrentOffsetForPage(page) < -1f || pagerScope.calculateCurrentOffsetForPage(
                                page
                            ) > 1f
                        ) 0f else 1f - (scale - 1f)
                }
            }
            is PagerType.Drawer -> {
                graphicsLayer {
                    if (pagerScope.calculateCurrentOffsetForPage(page) <= 0) translationX = 0f
                    else if (pagerScope.calculateCurrentOffsetForPage(page) <= 1)
                        translationX = constraints.maxWidth / 2 * pagerScope.calculateCurrentOffsetForPage(page)
                }
            }
            is PagerType.FromDeep -> {
                graphicsLayer {
                    if (pagerScope.calculateCurrentOffsetForPage(page) >= 0f) {
                        translationX = 0f
                        scaleX = 1f
                        scaleY = 1f
                    } else if (pagerScope.calculateCurrentOffsetForPage(page) <= 1f) {
                        val scaleFactor = pagerType.scaleFactor + (1 - pagerType.scaleFactor) * (1 - abs(
                            pagerScope.calculateCurrentOffsetForPage(page)
                        ))
                        translationX = scaleY * -pagerScope.calculateCurrentOffsetForPage(page)
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }

                }
            }
            is PagerType.Compression -> {
                graphicsLayer {
                    transformOrigin = TransformOrigin(
                        pivotFractionX = if (pagerScope.calculateCurrentOffsetForPage(page) < 0) 0f else scaleX,
                        pivotFractionY = 0.5f

                    )
                    scaleX =
                        if (pagerScope.calculateCurrentOffsetForPage(page) < 0) 1f + pagerScope.calculateCurrentOffsetForPage(
                            page
                        )
                        else 1f - pagerScope.calculateCurrentOffsetForPage(page)
                }
            }
            is PagerType.Scale -> {
                graphicsLayer {
                    transformOrigin = TransformOrigin(
                        pivotFractionX = (if (pagerScope.calculateCurrentOffsetForPage(page) < 0) 0 else scaleX).toFloat(),
                        pivotFractionY = 0.5f
                    )

                    val scale =
                        if (pagerScope.calculateCurrentOffsetForPage(page) < 0) 1f + pagerScope.calculateCurrentOffsetForPage(
                            page
                        ) else 1f - pagerScope.calculateCurrentOffsetForPage(page)
                    scaleX = scale
                    scaleY = scale
                }
            }
        }
    }
}











