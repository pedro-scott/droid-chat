package com.github.pedroscott.droidchat.presentation.page.signin

import androidx.lifecycle.viewModelScope
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.domain.entity.validation.DefaultValidationResult
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidateEmailUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidationEmptinessUseCase
import com.github.pedroscott.droidchat.presentation.model.StringResource
import com.github.pedroscott.droidchat.presentation.page.common.ChatViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validateEmail: ValidateEmailUseCase,
    private val validateEmptiness: ValidationEmptinessUseCase
) : ChatViewModel<SignInUiState>(
    initUiState = SignInUiState()
) {

    fun onEmailChange(value: String) {
        updateUiState { copy(email = value) }
        updateButtonState()
    }

    fun onPasswordChange(value: String) {
        updateUiState { copy(password = value) }
        updateButtonState()
    }

    fun onEmailFocusChange(isFocused: Boolean) {
        updateUiState {
            copy(
                emailError = if (!isFocused)
                    getEmailErrorMessage(validateEmail(uiState.value.email))
                else null
            )
        }
    }

    fun onPasswordFocusChange(isFocused: Boolean) {
        updateUiState {
            copy(
                passwordError = if (!isFocused)
                    getPasswordErrorMessage(validateEmptiness(uiState.value.password))
                else null
            )
        }
    }

    fun onLinkClick() {
        updateUiState { copy(navAction = SignInNavAction.SignUp) }
    }

    fun onButtonClick() {
        // TODO
        viewModelScope.launch {
            updateUiState { copy(isButtonLoading = true) }
            delay(2000L)
            updateUiState { copy(isButtonLoading = false) }
        }
    }

    fun clearNavAction() {
        updateUiState { copy(navAction = null) }
    }

    private fun getEmailErrorMessage(validationResult: DefaultValidationResult) =
        when (validationResult) {
            is DefaultValidationResult.Valid -> null
            is DefaultValidationResult.Invalid -> StringResource(R.string.error_message_email_invalid)
            is DefaultValidationResult.Empty -> StringResource(
                R.string.error_message_field_blank,
                StringResource(R.string.feature_login_email)
            )
        }

    private fun getPasswordErrorMessage(validationResult: DefaultValidationResult) =
        if  (validationResult is DefaultValidationResult.Empty)
            StringResource(
                R.string.error_message_field_blank,
                StringResource(R.string.feature_login_password)
            )
        else null

    private fun updateButtonState() {
        val validations = listOf(
            validateEmail(uiState.value.email),
            validateEmptiness(uiState.value.password)
        )

        updateUiState {
            copy(isButtonEnabled = validations.all { it is DefaultValidationResult.Valid })
        }
    }
}