package com.github.pedroscott.droidchat.presentation.page.signup

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.domain.entity.validation.DefaultValidationResult
import com.github.pedroscott.droidchat.domain.entity.validation.PasswordValidationResult
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidateEmailUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidatePasswordUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidationEmptinessUseCase
import com.github.pedroscott.droidchat.presentation.model.AddImageOption
import com.github.pedroscott.droidchat.presentation.model.StringResource
import com.github.pedroscott.droidchat.presentation.page.common.ChatViewModel
import com.github.pedroscott.droidchat.presentation.util.extension.getEmailErrorMessage
import com.github.pedroscott.droidchat.presentation.util.extension.getEmptinessErrorMessage
import com.github.pedroscott.droidchat.presentation.util.extension.getPasswordErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateEmptiness: ValidationEmptinessUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase
) : ChatViewModel<SignUpUiState, SignUpAction>(
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
        checkPasswordMatch()
        updateButtonState()
    }

    fun onConfirmationChange(value: String) {
        updateUiState { copy(confirmation = value) }
        checkPasswordMatch()
        updateButtonState()
    }

    fun onFirstNameFocusChange(isFocused: Boolean) {
        updateUiState {
            copy(
                firstNameError = if (!isFocused)
                    validateEmptiness(uiState.value.firstName).getEmptinessErrorMessage(
                        fieldName = StringResource(R.string.feature_sign_up_first_name)
                    )
                else null
            )
        }
    }

    fun onLastNameFocusChange(isFocused: Boolean) {
        updateUiState {
            copy(
                lastNameError = if (!isFocused)
                    validateEmptiness(uiState.value.lastName).getEmptinessErrorMessage(
                        fieldName = StringResource(R.string.feature_sign_up_last_name)
                    )
                else null
            )
        }
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
                    validatePassword(uiState.value.password).getPasswordErrorMessage(
                        fieldName = StringResource(R.string.feature_sign_up_password)
                    )
                else null
            )
        }
    }

    fun onConfirmationFocusChange(isFocused: Boolean) {
        updateUiState {
            copy(
                confirmationError = if (!isFocused)
                    validatePassword(uiState.value.confirmation).getPasswordErrorMessage(
                        fieldName = StringResource(R.string.feature_sign_up_password_confirmation)
                    )
                else null
            )
        }
    }

    fun onLinkClick() {
        emitAction(SignUpAction.Nav.Back)
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
        updateUiState { copy(showAddImageOptions = true) }
    }

    fun onAddImageOptionSelect(option: AddImageOption) {
        emitAction(
            when (option) {
                AddImageOption.TAKE -> SignUpAction.Camera
                AddImageOption.UPLOAD -> SignUpAction.Gallery
            }
        )
        clearAddImageOptions()
    }

    fun clearAddImageOptions() {
        updateUiState { copy(showAddImageOptions = false) }
    }

    fun setProfileImage(uri: Uri?) {
        updateUiState {
            copy(
                profileImage = if (profileImage != null && uri == null) profileImage else uri
            )
        }
    }

    private fun updateButtonState() {
        updateUiState {
            val validations = listOf(
                validateEmptiness(firstName) is DefaultValidationResult.Valid,
                validateEmptiness(lastName) is DefaultValidationResult.Valid,
                validateEmail(email) is DefaultValidationResult.Valid,
                validatePassword(password) is PasswordValidationResult.Valid,
                validatePassword(confirmation) is PasswordValidationResult.Valid,
                passwordInfo != null && confirmationInfo != null
            )

            copy(isButtonEnabled = validations.all { it })
        }
    }

    private fun checkPasswordMatch() {
        updateUiState {
            val passwordsMatch = password == confirmation && validatePassword(password) is PasswordValidationResult.Valid
            val matchMessage = StringResource(R.string.feature_sign_up_passwords_match)

            copy(
                passwordInfo = if (passwordsMatch) matchMessage else null,
                confirmationInfo = if (passwordsMatch) matchMessage else null
            )
        }
    }
}