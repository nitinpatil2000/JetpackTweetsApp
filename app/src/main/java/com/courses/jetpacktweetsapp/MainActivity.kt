package com.courses.jetpacktweetsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.courses.jetpacktweetsapp.api.TweetsApi
import com.courses.jetpacktweetsapp.screens.CategoryScreen
import com.courses.jetpacktweetsapp.screens.DetailScreen
import com.courses.jetpacktweetsapp.ui.theme.JetpackTweetsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackTweetsAppTheme {
                Scaffold(
                   topBar = {
                       TopAppBar(title = {
                           Text(text = "Tweetsy")
                       }, colors = TopAppBarDefaults.topAppBarColors(
                           containerColor = Color.Black,
                           titleContentColor = Color.White
                       ))
                   }
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category") {
        composable(route = "category") {
            CategoryScreen(onClick = {
                navController.navigate("detail/${it}")
            })
        }

        composable("detail/{category}", arguments = listOf(
            navArgument("category") {
                type = NavType.StringType
            }
        )
        ) {
            DetailScreen()
        }
    }
}

