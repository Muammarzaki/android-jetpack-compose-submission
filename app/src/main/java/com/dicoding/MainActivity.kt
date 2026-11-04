package com.dicoding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.ui.author.AuthorScreen
import com.dicoding.ui.detail.DetailScreen
import com.dicoding.ui.home.HomeScreen
import com.dicoding.ui.home.HomeViewModel
import com.dicoding.ui.create.CreateNewScreen
import com.dicoding.ui.theme.SubmissionComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SubmissionComposeTheme {
                Route()
            }
        }
    }
}


@Composable
fun Route() {
    val controller = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel()

    NavHost(navController = controller, startDestination = "home") {
        composable("home") {
            HomeScreen(
                navigation = controller,
                viewModel = homeViewModel
            )
        }
        composable("detail/{url}", arguments = listOf(
            navArgument("url") {
                type = NavType.StringType
                nullable = false
            }
        )) { backStackEntry ->
            backStackEntry.arguments?.getString("url")
                ?.let { DetailScreen(url = it, navigation = controller) }
        }
        composable("about") {
            AuthorScreen(navigation = controller)
        }
        composable("new") {
            CreateNewScreen(
                navigation = controller,
                homeViewModel = homeViewModel
            )
        }
    }

}
