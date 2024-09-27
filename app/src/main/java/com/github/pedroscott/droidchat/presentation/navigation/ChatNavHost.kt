package com.github.pedroscott.droidchat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.github.pedroscott.droidchat.presentation.page.splash.SplashRoute
import com.github.pedroscott.droidchat.util.extension.composable

@Composable
fun ChatNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: ChatRoute<*> = SplashRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<SplashRoute> {
            Page(handleAction = {})
        }
    }
}