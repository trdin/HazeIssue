package com.example.hazesample

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hazesample.ui.theme.HazeSampleTheme
import dev.chrisbanes.haze.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HazeSampleTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val hazeState = remember { HazeState() }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.hazeEffect(
                    state = hazeState,
                    style = getHazeStyle()
                ) {
                    progressive =
                        HazeProgressive.verticalGradient(startIntensity = 1f, endIntensity = 0f)
                },
                title = { Text("Top Bar") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.hazeEffect(
                    state = hazeState,
                    style = getHazeStyle()
                ) {
                    progressive =
                        HazeProgressive.verticalGradient(startIntensity = 0f, endIntensity = 1f)
                },
                containerColor = Color.Transparent
            ) {
                Text("Bottom Bar", modifier = Modifier.padding(16.dp))
            }
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier

                .hazeSource(state = hazeState, zIndex = 0f)
        ) {
            val imageSections = listOf(
                "Nature" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Architecture" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Technology" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Technology" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Technology" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Technology" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                )
            )

            imageSections.forEach { (title, images) ->
                item {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        images.forEach { imageUrl ->
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = null,
                                modifier = Modifier.size(150.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getHazeStyle(tint: Color = Color.Black.copy(0.01f)): HazeStyle {
    return HazeStyle(
        backgroundColor = Color.Black,
        tint = getBarTint(tint),
        blurRadius = 40.dp,
        fallbackTint = getBarTint(tint),
    )
}

fun getBarTint(currentColor: Color): HazeTint {
    return HazeTint(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            currentColor
        } else {
            Color.Black.copy(alpha = 0.5f)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HazeSampleTheme {
        MainScreen()
    }
}
