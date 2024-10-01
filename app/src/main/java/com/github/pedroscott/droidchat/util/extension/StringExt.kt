package com.github.pedroscott.droidchat.util.extension

import android.text.Html
import androidx.compose.ui.text.AnnotatedString
import com.github.pedroscott.droidchat.util.parser.HtmlParser

fun String.parseHtml(): AnnotatedString =
    HtmlParser.parse(
        spanned = Html.fromHtml(
            this,
            Html.FROM_HTML_MODE_LEGACY
        )
    )