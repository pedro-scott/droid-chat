package com.github.pedroscott.droidchat.presentation.page.signin

import androidx.lifecycle.viewModelScope
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.domain.entity.validation.DefaultValidationResult
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidateEmailUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidationEmptinessUseCase
import com.github.pedroscott.droidchat.presentation.model.StringResource
import com.github.pedroscott.droidchat.presentation.page.common.ChatViewModel
import com.github.pedroscott.droidchat.presentation.util.extension.getEmailErrorMessage
import com.github.pedroscott.droidchat.presentation.util.extension.getEmptinessErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validateEmail: ValidateEmailUseCase,
    private val validateEmptiness: ValidationEmptinessUseCase
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
        emitAction(SignInNavAction.SignUp)
    }

    fun onButtonClick() {
        // TODO
        viewModelScope.launch {
            updateUiState { copy(isButtonLoading = true) }
            delay(2000L)
            updateUiState { copy(isButtonLoading = false) }
        }
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