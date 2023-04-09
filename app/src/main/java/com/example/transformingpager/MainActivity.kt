package com.example.transformingpager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.transforming_pager.PagerType
import com.example.transforming_pager.TransformingHorizontalPager
import com.example.transforming_pager.TransformingVerticalPager
import com.example.transformingpager.ui.theme.TransformingPagerTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sample()
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Sample() {
    val expanded = remember { mutableStateOf(false) }

    val currentPager = remember {
        mutableStateOf<PagerType>(PagerType.Cube)
    }

    TransformingHorizontalPager(
        count = 100,
        pagerType = currentPager.value
    ) { page ->
        // CONTENT
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(randomColor()), contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier, text = page.toString(), fontSize = 300.sp
            )
        }
    }
    Box {
        IconButton(onClick = { expanded.value = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Показать меню")
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            listOf(
                PagerType.Cube,
                PagerType.Stack,
                PagerType.Flip,
                PagerType.Rotate(),
                PagerType.Zoom,
                PagerType.Drawer,
                PagerType.FromDeep(),
                PagerType.Compression,
                PagerType.Scale
            ).forEach {
                Text(
                    text = it.javaClass.simpleName,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 16.dp)
                        .clickable(onClick = {
                            currentPager.value = it
                            expanded.value = false
                        })
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TransformingPagerTheme {
        Sample()
    }
}

fun randomColor(): Color {
    val random = Random.Default
    return Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))
}