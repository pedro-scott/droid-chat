package com.github.pedroscott.droidchat.util.parser

import android.graphics.Typeface
import android.text.ParcelableSpan
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.text.getSpans

object HtmlParser {

    fun parse(spanned: Spanned): AnnotatedString =
        buildAnnotatedString {
            append(spanned.toString())
            addSpanStyles<ForegroundColorSpan>(
                spanned = spanned,
                style = { getSpanStyle() }
            )
            addSpanStyles<StyleSpan>(
                spanned = spanned,
                style = { getSpanStyle() }
            )
            addSpanStyles<UnderlineSpan>(
                spanned = spanned,
                style = { getSpanStyle() }
            )
            addSpanStyles<StrikethroughSpan>(
                spanned = spanned,
                style = { getSpanStyle() }
            )
        }

    private inline fun <reified T : ParcelableSpan> AnnotatedString.Builder.addSpanStyles(
        spanned: Spanned,
        style: T.() -> SpanStyle
    ) {
        spanned.getSpans<T>().forEach { span ->
            addStyle(
                style = span.style(),
                start = spanned.getSpanStart(span),
                end = spanned.getSpanEnd(span)
            )
        }
    }

    private fun ForegroundColorSpan.getSpanStyle() = SpanStyle(color = Color(foregroundColor))

    private fun StyleSpan.getSpanStyle() =
        when (style) {
            Typeface.BOLD -> FontWeight.Bold to null
            Typeface.ITALIC -> null to FontStyle.Italic
            Typeface.BOLD_ITALIC -> FontWeight.Bold to FontStyle.Italic
            else -> null to null
        }.let { (weight, style) ->
            SpanStyle(
                fontWeight = weight,
                fontStyle = style
            )
        }

    private fun UnderlineSpan.getSpanStyle() = SpanStyle(textDecoration = TextDecoration.Underline)

    private fun StrikethroughSpan.getSpanStyle() = SpanStyle(textDecoration = TextDecoration.LineThrough)
}