package com.github.pedroscott.droidchat.presentation.page.signup

import android.net.Uri
import com.github.pedroscott.droidchat.presentation.model.StringResource

data class SignUpUiState(
    val isButtonLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val profileImage: Uri? = null,
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
    val showImagePicker: Boolean = false,
    val showCamera: Boolean = false,
    val navAction: SignUpNavAction? = null
)