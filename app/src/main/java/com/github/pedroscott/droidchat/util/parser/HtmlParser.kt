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
                style = { span ->
                    SpanStyle(color = Color((span as ForegroundColorSpan).foregroundColor))
                }
            )
            addSpanStyles<StyleSpan>(
                spanned = spanned,
                style = { span ->
                    when ((span as StyleSpan).style) {
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
                }
            )
            addSpanStyles<UnderlineSpan>(
                spanned = spanned,
                style = {
                    SpanStyle(textDecoration = TextDecoration.Underline)
                }
            )
            addSpanStyles<StrikethroughSpan>(
                spanned = spanned,
                style = {
                    SpanStyle(textDecoration = TextDecoration.LineThrough)
                }
            )
        }

    private inline fun <reified T : ParcelableSpan> AnnotatedString.Builder.addSpanStyles(
        spanned: Spanned,
        style: (ParcelableSpan) -> SpanStyle
    ) {
        spanned.getSpans<T>().forEach { span ->
            addStyle(
                style = style(span),
                start = spanned.getSpanStart(span),
                end = spanned.getSpanEnd(span)
            )
        }
    }
}