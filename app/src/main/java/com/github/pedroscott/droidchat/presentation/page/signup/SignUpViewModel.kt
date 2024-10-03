package com.github.pedroscott.droidchat.presentation.page.signup

import androidx.lifecycle.viewModelScope
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.domain.entity.validation.DefaultValidationResult
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidateEmailUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidationEmptinessUseCase
import com.github.pedroscott.droidchat.presentation.model.StringResource
import com.github.pedroscott.droidchat.presentation.model.AddImageOption
import com.github.pedroscott.droidchat.presentation.page.common.ChatViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateEmail: ValidateEmailUseCase,
    private val validateEmptiness: ValidationEmptinessUseCase
) : ChatViewModel<SignUpUiState>(
    initUiState = SignUpUiState()
) {

    fun onFirstNameChange(value: String) {
        updateUiState { copy(firstName = value) }
        updateButtonState()
    }

    fun onLastNameChange(value: String) {
        updateUiState { copy(lastName = value) }
        updateButtonState()
    }

    fun onEmailChange(value: String) {
        updateUiState { copy(email = value) }
        updateButtonState()
    }

    fun onPasswordChange(value: String) {
        updateUiState { copy(password = value) }
        updateButtonState()
    }

    fun onConfirmationChange(value: String) {
        updateUiState { copy(confirmation = value) }
        updateButtonState()
    }

    fun onFirstNameFocusChange(isFocused: Boolean, fieldName: String) {
        updateUiState {
            copy(
                firstNameError = if (!isFocused)
                    getEmptinessErrorMessage(
                        validationResult = validateEmptiness(uiState.value.firstName),
                        fieldName = fieldName
                    )
                else null
            )
        }
    }

    fun onLastNameFocusChange(isFocused: Boolean, fieldName: String) {
        updateUiState {
            copy(
                lastNameError = if (!isFocused)
                    getEmptinessErrorMessage(
                        validationResult = validateEmptiness(uiState.value.lastName),
                        fieldName = fieldName
                    )
                else null
            )
        }
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
                    getEmptinessErrorMessage(
                        validationResult = validateEmptiness(uiState.value.password),
                        fieldName = fieldName
                    )
                else null
            )
        }
    }

    fun onConfirmationFocusChange(isFocused: Boolean, fieldName: String) {
        updateUiState {
            copy(
                confirmationError = if (!isFocused)
                    getEmptinessErrorMessage(
                        validationResult = validateEmptiness(uiState.value.confirmation),
                        fieldName = fieldName
                    )
                else null
            )
        }
    }

    fun onLinkClick() {
        updateUiState { copy(navAction = SignUpNavAction.Back) }
    }

    fun onButtonClick() {
        // TODO
        viewModelScope.launch {
            updateUiState { copy(isButtonLoading = true) }
            delay(2000L)
            updateUiState { copy(isButtonLoading = false) }
        }
    }

    fun onAddImageClick() {
        updateUiState { copy(showUploadImageOptions = true) }
    }

    fun onUploadImageOptionSelect(option: AddImageOption) {
        // TODO
        clearUploadImageOptions()
    }

    fun clearUploadImageOptions() {
        updateUiState { copy(showUploadImageOptions = false) }
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

    private fun getEmptinessErrorMessage(validationResult: DefaultValidationResult, fieldName: String) =
        if  (validationResult is DefaultValidationResult.Empty)
            StringResource(R.string.error_message_field_blank, fieldName)
        else null

    private fun updateButtonState() {
        val validations = listOf(
            validateEmptiness(uiState.value.firstName),
            validateEmptiness(uiState.value.lastName),
            validateEmail(uiState.value.email),
            validateEmptiness(uiState.value.password),
            validateEmptiness(uiState.value.confirmation)
        )

        updateUiState {
            copy(isButtonEnabled = validations.all { it is DefaultValidationResult.Valid })
        }
    }
}