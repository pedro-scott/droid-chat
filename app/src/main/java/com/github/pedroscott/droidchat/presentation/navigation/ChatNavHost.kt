package com.github.pedroscott.droidchat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.github.pedroscott.droidchat.presentation.page.signin.SignInRoute
import com.github.pedroscott.droidchat.presentation.page.splash.SplashAction
import com.github.pedroscott.droidchat.presentation.page.splash.SplashRoute
import com.github.pedroscott.droidchat.util.extension.composable
import com.github.pedroscott.droidchat.util.extension.navigateAndPopUp

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
            Page(
                handleAction = { action ->
                    when (action) {
                        is SplashAction.SignIn -> navController.navigateAndPopUp(
                            route = SignInRoute,
                            popUp = SplashRoute
                        )
                    }
                }
            )
        }

        composable<SignInRoute> {
            Page(handleAction = {})
        }
    }
}