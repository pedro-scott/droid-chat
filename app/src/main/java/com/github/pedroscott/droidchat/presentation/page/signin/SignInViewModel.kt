package com.github.pedroscott.droidchat.presentation.page.signin

import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.domain.entity.error.AppError
import com.github.pedroscott.droidchat.domain.entity.validation.DefaultValidationResult
import com.github.pedroscott.droidchat.domain.usecase.signin.SignInUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidateEmailUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidationEmptinessUseCase
import com.github.pedroscott.droidchat.presentation.model.StringResource
import com.github.pedroscott.droidchat.presentation.page.common.ChatViewModel
import com.github.pedroscott.droidchat.presentation.util.extension.getEmailErrorMessage
import com.github.pedroscott.droidchat.presentation.util.extension.getEmptinessErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validateEmail: ValidateEmailUseCase,
    private val validateEmptiness: ValidationEmptinessUseCase,
    private val signIn: SignInUseCase
) : ChatViewModel<SignInUiState, SignInNavAction>(
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
                    validateEmail(uiState.value.email).getEmailErrorMessage()
                else null
            )
        }
    }

    fun onPasswordFocusChange(isFocused: Boolean) {
        updateUiState {
            copy(
                passwordError = if (!isFocused)
                    validateEmptiness(uiState.value.password).getEmptinessErrorMessage(
                        fieldName = StringResource(R.string.feature_login_password)
                    )
                else null
            )
        }
    }

    fun onLinkClick() {
        sendAction(SignInNavAction.SignUp)
    }

    fun onButtonClick() {
        executeAsync(
            block = {
                signIn(
                    SignInUseCase.Params(
                        email = uiState.value.email,
                        password = uiState.value.password
                    )
                )
            },
            onLoading = { updateUiState { copy(isButtonLoading = it) } },
            onSuccess = { /* TODO */ },
            onError = { error ->
                updateUiState {
                    copy(
                        errorMessage = StringResource(
                            if (error is AppError.Api.Conflict)
                                R.string.error_message_invalid_username_or_password
                            else R.string.common_generic_error_message
                        )
                    )
                }
            }
        )
    }

    fun clearError() {
        updateUiState { copy(errorMessage = null) }
    }

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