package com.github.pedroscott.droidchat.presentation.page.signup

import android.net.Uri
import androidx.compose.runtime.Immutable
import com.github.pedroscott.droidchat.presentation.model.StringResource

@Immutable
data class SignUpUiState(
    val isButtonLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val profileImageUri: Uri? = null,
    val profileImagePath: String? = null,
    val firstName: String = "",
    val firstNameError: StringResource? = null,
    val lastName: String = "",
    val lastNameError: StringResource? = null,
    val email: String = "",
    val emailError: StringResource? = null,
    val password: String = "",
    val passwordInfo: StringResource? = null,
    val passwordError: StringResource? = null,
    val confirmation: String = "",
    val confirmationInfo: StringResource? = null,
    val confirmationError: StringResource? = null,
    val showAddImageOptions: Boolean = false,
    val errorMessage: StringResource? = null
)