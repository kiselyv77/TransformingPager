package com.example.transforming_pager

sealed class PagerType() {
    object Cube : PagerType()
    object Flip : PagerType()
    object Stack : PagerType()
    object Zoom : PagerType()
    data class Rotate(val rotationValue: Float = -45f) : PagerType()
    object Drawer : PagerType()
    data class FromDeep(val scaleFactor: Float = 0.5f) : PagerType()
    object Compression : PagerType()
    object Scale : PagerType()
}