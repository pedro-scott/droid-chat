package com.github.pedroscott.droidchat.presentation.util.extension

import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.domain.entity.validation.DefaultValidationResult
import com.github.pedroscott.droidchat.domain.entity.validation.PasswordValidationResult
import com.github.pedroscott.droidchat.presentation.model.StringResource

fun DefaultValidationResult.getEmptinessErrorMessage(fieldName: StringResource) =
    if  (this is DefaultValidationResult.Empty)
        StringResource(R.string.error_message_field_blank, fieldName)
    else null

fun DefaultValidationResult.getEmailErrorMessage() =
    when (this) {
        is DefaultValidationResult.Valid -> null
        is DefaultValidationResult.Invalid -> StringResource(R.string.error_message_email_invalid)
        is DefaultValidationResult.Empty -> StringResource(
            R.string.error_message_field_blank,
            StringResource(R.string.feature_login_email)
        )
    }

fun PasswordValidationResult.getPasswordErrorMessage(fieldName: StringResource) =
    when (this) {
        is PasswordValidationResult.Valid -> null
        is PasswordValidationResult.InvalidLength -> StringResource(R.string.error_message_password_less_than_eight_chars)
        is PasswordValidationResult.InvalidFormat -> StringResource(R.string.error_message_password_invalid)
        is PasswordValidationResult.Empty -> StringResource(R.string.error_message_field_blank, fieldName)
    }