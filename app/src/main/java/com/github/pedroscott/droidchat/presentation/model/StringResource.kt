package com.github.pedroscott.droidchat.presentation.model

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

class StringResource(
    @StringRes val resId: Int,
    vararg val args: Any
) {
    @Composable
    fun asString(): String = stringResource(resId, *mappedArgs())

    fun asString(context: Context): String = context.getString(resId, *mappedArgs(context))

    @Composable
    private fun mappedArgs(): Array<Any> = args.map {
        if (it is StringResource) {
            it.asString()
        } else it
    }.toTypedArray()

    private fun mappedArgs(context: Context): Array<Any> = args.map {
        if (it is StringResource) {
            it.asString(context)
        } else it
    }.toTypedArray()
}