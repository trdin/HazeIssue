package com.example.hazesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.hazesample.ui.theme.HazeSampleTheme
import dev.chrisbanes.haze.*
import dev.chrisbanes.haze.materials.HazeMaterials

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HazeSampleTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable("main",exitTransition = { getExitTransition() }, enterTransition = { getEnterTransition() }) { MainScreen(navController) }
                    composable(
                        route = "details/{itemId}",
                        arguments = listOf(navArgument("itemId") { type = NavType.StringType }),
                        enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up,
                                animationSpec = tween(1000, easing = FastOutSlowInEasing)
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Down,
                                animationSpec = tween(1000, easing = FastOutSlowInEasing)
                            )
                        }
                    ) { backStackEntry ->
                        Box(
                            modifier = Modifier
                                .background(Color.Black)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center

                        ) {
                            val itemId = backStackEntry.arguments?.getString("itemId")
                            Column {
                                Text(
                                    text = "Details for item: $itemId",
                                    color = Color.White,
                                    modifier = Modifier

                                        .padding(16.dp)
                                )
                                Button(
                                    onClick = { navController.navigateUp() },
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .height(50.dp)
                                        .width(100.dp)
                                ) {
                                    Text("Back")
                                }
                            }
                            // Add your details screen content here
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    val hazeState = remember { HazeState() }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .hazeEffect(
                        state = hazeState,
                        style = getHazeStyle(Color.Transparent),
                    ) {
                        inputScale = HazeInputScale.Fixed(0.33f)
                        //progressive = HazeProgressive.verticalGradient(startIntensity = 1f, endIntensity = 1f)
                    }
                    .fillMaxWidth()
                    .height(100.dp)
            )
            { Text("Top Bar", modifier = Modifier.padding(16.dp).clickable {navController.navigate("details/${123}"){
                launchSingleTop = true
            }  }.height(100.dp). background(Color.Yellow).padding(top = 10.dp)) }
        },
        bottomBar = {
            Box(

                modifier = Modifier
                    .hazeEffect(
                        state = hazeState,
                        style = getHazeStyle()
                    ) {
                        inputScale = HazeInputScale.Fixed(0.33f)
                        //   progressive = HazeProgressive.verticalGradient(startIntensity = 1f, endIntensity = 1f)
                    }
                    .fillMaxWidth()
                    .height(100.dp),
                //containerColor = Color.Transparent
            ) {
                Text("Bottom Bar", modifier = Modifier.padding(16.dp))
            }
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                bottom = innerPadding.calculateBottomPadding(),
                top = innerPadding.calculateTopPadding()
            ),
            modifier = Modifier
                .background(Color.Black)
                .hazeSource(state = hazeState, zIndex = 1f)
                .padding(vertical = 10.dp)
        ) {
            val imageSections = listOf(
                "Nature" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Architecture" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Technology" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Technology" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Technology" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Technology" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Technology" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Technology" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDZNZmOEhtCU5tCROzZpFuBmhWU-fBZSvJg&s"
                ),
                "Technology" to listOf(
                    "https://img.freepik.com/free-vector/realistic-test-paper-composition-with-pencil-stack-students-paperwork-with-marks-correct-answers_1284-54249.jpg?semt=ais_hybrid",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA8MOd8rpat-PJsJQPEc4kHaFru9yIwsJu9Q&s",
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
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(images) { imageUrl ->
                            Card(
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(8.dp),
                                shape = MaterialTheme.shapes.medium,
                                onClick = {
                                    navController.navigate("details/${imageUrl.hashCode()}"){
                                        launchSingleTop = true
                                    }
                                },
                            ) {
                                val hazeState1 = remember { HazeState() }
                                Box {
                                    AsyncImage(
                                        model = imageUrl,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .hazeSource(state = hazeState1, zIndex = 0f),
                                        contentScale = ContentScale.Crop
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp)
                                            .align(Alignment.BottomCenter)
                                            .hazeEffect(
                                                state = hazeState1,
                                                style = HazeMaterials.ultraThin()
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getHazeStyle(tint: Color = Color.Transparent): HazeStyle {
    return HazeStyle(
        backgroundColor = Color.Black,
        tint = getBarTint(tint),
        noiseFactor = 0.02f,
        blurRadius = 35.dp,
        fallbackTint = getBarTint(tint),
    )
}

fun getBarTint(currentColor: Color): HazeTint {
    return HazeTint(
        color =
        Color.Transparent,
    )
}


fun getExitTransition(transitionDuration: Int = 1000): ExitTransition {
    return fadeOut(
        animationSpec = tween(transitionDuration),
        targetAlpha = 0f
    )
}


fun getEnterTransition(transitionDuration: Int = 1000): EnterTransition {
    return fadeIn(
        animationSpec = tween(transitionDuration),
        initialAlpha = 0f
    )
}


