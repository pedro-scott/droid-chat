package com.github.pedroscott.droidchat.util.extension

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.toRoute

inline fun <reified T : Any> NavGraphBuilder.composable(
    noinline content: @Composable T.(NavBackStackEntry) -> Unit
) {
    composable<T> { entry ->
        entry.toRoute<T>().content(entry)
    }
}

fun NavController.navigateAndPopUp(
    route: Any,
    popUp: Any,
    isInclusive: Boolean = true
) {
    navigate(
        route = route,
        navOptions = navOptions {
            popUpTo(popUp) {
                inclusive = isInclusive
            }
        }
    )
}