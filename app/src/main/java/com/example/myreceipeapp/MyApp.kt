package com.example.myreceipeapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myreceipeapp.model.Category
import com.example.myreceipeapp.view.CategoryListScreen
import com.example.myreceipeapp.view.CategoryScreen
import com.example.myreceipeapp.view.DetailScreen

@Composable
fun MyApp(navController: NavHostController, modifier: Modifier){
    NavHost(navController = navController, startDestination = Route.CategoryScreen.routeName) {
        composable(route = Route.CategoryScreen.routeName){
            CategoryListScreen(modifier = modifier, navigateToDetailScreen = { category ->
                navController.currentBackStackEntry?.savedStateHandle?.set("category", category)
                navController.navigate(Route.DetailScreen.routeName)
            })
        }
        composable(route = Route.DetailScreen.routeName){
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category") ?: Category(idCategory = "", strCategory = "", strCategoryThumb = "", strCategoryDescription = "")
            DetailScreen(category = category)
        }
    }
}