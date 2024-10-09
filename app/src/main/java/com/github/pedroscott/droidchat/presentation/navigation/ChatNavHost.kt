package com.github.pedroscott.droidchat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.github.pedroscott.droidchat.presentation.page.signin.SignInNavAction
import com.github.pedroscott.droidchat.presentation.page.signin.SignInRoute
import com.github.pedroscott.droidchat.presentation.page.signup.SignUpAction
import com.github.pedroscott.droidchat.presentation.page.signup.SignUpRoute
import com.github.pedroscott.droidchat.presentation.page.splash.SplashNavAction
import com.github.pedroscott.droidchat.presentation.page.splash.SplashRoute
import com.github.pedroscott.droidchat.presentation.util.extension.composable
import com.github.pedroscott.droidchat.presentation.util.extension.navigateAndPopUp

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
                handleNavAction = { action ->
                    when (action) {
                        is SplashNavAction.SignIn -> navController.navigateAndPopUp(
                            route = SignInRoute,
                            popUp = SplashRoute
                        )
                    }
                }
            )
        }

        composable<SignInRoute> {
            Page(
                handleNavAction = { navAction ->
                    when (navAction) {
                        is SignInNavAction.SignUp -> navController.navigate(SignUpRoute)
                    }
                }
            )
        }

        composable<SignUpRoute> {
            Page(
                handleNavAction = { navAction ->
                    when (navAction) {
                        is SignUpAction.Nav.Back -> navController.navigateUp()
                    }
                }
            )
        }
    }
}