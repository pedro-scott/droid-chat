package com.github.pedroscott.droidchat.presentation.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

class StringResource(
    @StringRes val resId: Int,
    vararg val args: Any
) {
    @Composable
    fun asString(): String = stringResource(resId, *mappedArgs())

    @Composable
    private fun mappedArgs(): Array<Any> = args.map {
        if (it is StringResource) {
            it.asString()
        } else it
    }.toTypedArray()
}