package com.github.pedroscott.droidchat.util.extension

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.toRoute

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideInto(
    direction: AnimatedContentTransitionScope.SlideDirection
): EnterTransition =
    slideIntoContainer(
        towards = direction,
        animationSpec = tween(durationMillis = 500)
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutOf(
    direction: AnimatedContentTransitionScope.SlideDirection
): ExitTransition =
    slideOutOfContainer(
        towards = direction,
        animationSpec = tween(durationMillis = 500)
    )

inline fun <reified T : Any> NavGraphBuilder.composable(
    noinline enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        slideInto(AnimatedContentTransitionScope.SlideDirection.Left)
    },
    noinline exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        slideOutOf(AnimatedContentTransitionScope.SlideDirection.Left)
    },
    noinline popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        slideInto(AnimatedContentTransitionScope.SlideDirection.Right)
    },
    noinline popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        slideOutOf(AnimatedContentTransitionScope.SlideDirection.Right)
    },
    noinline content: @Composable T.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) { entry ->
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