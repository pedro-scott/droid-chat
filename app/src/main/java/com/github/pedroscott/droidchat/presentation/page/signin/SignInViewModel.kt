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

    fun onEmailFocusChange(isFocused: Boolean, fieldName: String) {
        updateUiState {
            copy(
                emailError = if (!isFocused)
                    getEmailErrorMessage(
                        validationResult = validateEmail(uiState.value.email),
                        fieldName = fieldName
                    )
                else null
            )
        }
    }

    fun onPasswordFocusChange(isFocused: Boolean, fieldName: String) {
        updateUiState {
            copy(
                passwordError = if (!isFocused)
                    getPasswordErrorMessage(
                        validationResult = validateEmptiness(uiState.value.password),
                        fieldName = fieldName
                    )
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

    private fun getEmailErrorMessage(validationResult: DefaultValidationResult, fieldName: String) =
        when (validationResult) {
            is DefaultValidationResult.Valid -> null
            is DefaultValidationResult.Invalid -> StringResource(R.string.error_message_email_invalid)
            is DefaultValidationResult.Empty -> StringResource(R.string.error_message_field_blank, fieldName)
        }

    private fun getPasswordErrorMessage(validationResult: DefaultValidationResult, fieldName: String) =
        if  (validationResult is DefaultValidationResult.Empty)
            StringResource(R.string.error_message_field_blank, fieldName)
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