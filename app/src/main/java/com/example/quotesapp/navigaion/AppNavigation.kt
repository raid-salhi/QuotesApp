package com.example.quotesapp.navigaion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quotesapp.screens.mainSceen.MainScreen
import com.example.quotesapp.screens.savedScreen.SavedScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreensRoute.MainScreen.name ){
        composable(route = ScreensRoute.MainScreen.name){
            MainScreen(navController)
        }
        composable(route = ScreensRoute.SavedScreen.name)
        {
            SavedScreen(navController)
        }
    }
}