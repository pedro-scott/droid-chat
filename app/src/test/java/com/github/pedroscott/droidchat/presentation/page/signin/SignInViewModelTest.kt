package com.github.pedroscott.droidchat.presentation.page.signin

import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.base.BaseCoroutineTest
import com.github.pedroscott.droidchat.domain.entity.error.AppError
import com.github.pedroscott.droidchat.domain.entity.validation.DefaultValidationResult
import com.github.pedroscott.droidchat.domain.usecase.signin.SignInUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidateEmailUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidationEmptinessUseCase
import com.github.pedroscott.droidchat.presentation.model.StringResource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SignInViewModelTest : BaseCoroutineTest() {

    private val validateEmail = mockk<ValidateEmailUseCase>()
    private val validateEmptiness = mockk<ValidationEmptinessUseCase>()
    private val signIn = mockk<SignInUseCase>()

    private lateinit var viewModel: SignInViewModel

    private val uiState: SignInUiState
        get() = viewModel.uiState.value

    @BeforeEach
    override fun setup() {
        viewModel = SignInViewModel(
            validateEmail = validateEmail,
            validateEmptiness = validateEmptiness,
            signIn = signIn
        )
    }

    @AfterEach
    override fun tearDown() = Unit

    @Nested
    @DisplayName("GIVEN email field value changes")
    inner class EmailChange {

        @Test
        @DisplayName("WHEN email and password values are valid THEN should update email and enable button")
        fun testValidInputs() = testScope.runTest {
            // Arrange
            val emailInput = "email@email.com"
            every { validateEmail(any()) } returns DefaultValidationResult.Valid
            every { validateEmptiness(any()) } returns DefaultValidationResult.Valid

            // Act
            viewModel.onEmailChange(emailInput)

            // Assert
            verify { validateEmail(emailInput) }
            verify { validateEmptiness(uiState.password) }
            Assertions.assertEquals(emailInput, uiState.email)
            Assertions.assertTrue(uiState.isButtonEnabled)
        }

        @Test
        @DisplayName("WHEN email and password values are invalid THEN should update email and disable button")
        fun testInvalidInputs() = testScope.runTest {
            // Arrange
            val emailInput = "email"
            every { validateEmail(any()) } returns DefaultValidationResult.Invalid
            every { validateEmptiness(any()) } returns DefaultValidationResult.Invalid

            // Act
            viewModel.onEmailChange(emailInput)

            // Assert
            verify { validateEmail(emailInput) }
            verify { validateEmptiness(uiState.password) }
            Assertions.assertEquals(emailInput, uiState.email)
            Assertions.assertFalse(uiState.isButtonEnabled)
        }
    }

    @Nested
    @DisplayName("GIVEN password field value changes")
    inner class PasswordChange {

        @Test
        @DisplayName("WHEN password and email values are valid THEN should update password and enable button")
        fun testValidInputs() = testScope.runTest {
            // Arrange
            val passwordInput = "senha123"
            every { validateEmail(any()) } returns DefaultValidationResult.Valid
            every { validateEmptiness(any()) } returns DefaultValidationResult.Valid

            // Act
            viewModel.onPasswordChange(passwordInput)

            // Assert
            verify { validateEmail(uiState.email) }
            verify { validateEmptiness(passwordInput) }
            Assertions.assertEquals(passwordInput, uiState.password)
            Assertions.assertTrue(uiState.isButtonEnabled)
        }

        @Test
        @DisplayName("WHEN password and email values are invalid THEN should update password and disable button")
        fun testInvalidInputs() = testScope.runTest {
            // Arrange
            val passwordInput = "senha123"
            every { validateEmail(any()) } returns DefaultValidationResult.Invalid
            every { validateEmptiness(any()) } returns DefaultValidationResult.Invalid

            // Act
            viewModel.onPasswordChange(passwordInput)

            // Assert
            verify { validateEmail(uiState.email) }
            verify { validateEmptiness(passwordInput) }
            Assertions.assertEquals(passwordInput, uiState.password)
            Assertions.assertFalse(uiState.isButtonEnabled)
        }
    }

    @Nested
    @DisplayName("GIVEN email field focus change")
    inner class EmailFocusChange {

        @Test
        @DisplayName("WHEN field gain focus THEN should clear field error")
        fun testFieldFocused() {
            // Arrange
            every { validateEmail(any()) } returns DefaultValidationResult.Valid

            // Act
            viewModel.onEmailFocusChange(true)

            // Assert
            verify(exactly = 0) { validateEmail(uiState.email) }
            Assertions.assertNull(uiState.emailError)
        }

        @Test
        @DisplayName("WHEN field lost focus and is valid THEN should clear field error")
        fun testFieldNotFocusedAndValid() {
            // Arrange
            every { validateEmail(any()) } returns DefaultValidationResult.Valid

            // Act
            viewModel.onEmailFocusChange(false)

            // Assert
            verify { validateEmail(uiState.email) }
            Assertions.assertNull(uiState.emailError)
        }

        @Test
        @DisplayName("WHEN field lost focus and is empty THEN should show invalid field error")
        fun testFieldNotFocusedAndInvalid() {
            // Arrange
            every { validateEmail(any()) } returns DefaultValidationResult.Invalid

            // Act
            viewModel.onEmailFocusChange(false)

            // Assert
            verify { validateEmail(uiState.email) }
            Assertions.assertEquals(
                R.string.error_message_email_invalid,
                uiState.emailError?.resId
            )
        }

        @Test
        @DisplayName("WHEN field lost focus and is empty THEN should show empty field error")
        fun testFieldNotFocusedAndEmpty() {
            // Arrange
            every { validateEmail(any()) } returns DefaultValidationResult.Empty

            // Act
            viewModel.onEmailFocusChange(false)

            // Assert
            verify { validateEmail(uiState.email) }
            Assertions.assertEquals(
                R.string.error_message_field_blank,
                uiState.emailError?.resId
            )
            Assertions.assertEquals(
                R.string.feature_login_email,
                (uiState.emailError?.args?.first() as? StringResource)?.resId
            )
        }
    }

    @Nested
    @DisplayName("GIVEN password field focus change")
    inner class PasswordFocusChange {

        @Test
        @DisplayName("WHEN field gain focus THEN should clear field error")
        fun testFieldFocused() {
            // Arrange
            every { validateEmptiness(any()) } returns DefaultValidationResult.Valid

            // Act
            viewModel.onPasswordFocusChange(true)

            // Assert
            verify(exactly = 0) { validateEmptiness(uiState.password) }
            Assertions.assertNull(uiState.passwordError)
        }

        @Test
        @DisplayName("WHEN field lost focus and is valid THEN should clear field error")
        fun testFieldNotFocusedAndValid() {
            // Arrange
            every { validateEmptiness(any()) } returns DefaultValidationResult.Valid

            // Act
            viewModel.onPasswordFocusChange(false)

            // Assert
            verify { validateEmptiness(uiState.password) }
            Assertions.assertNull(uiState.passwordError)
        }

        @Test
        @DisplayName("WHEN field lost focus and is empty THEN should show empty field error")
        fun testFieldNotFocusedAndEmpty() {
            // Arrange
            every { validateEmptiness(any()) } returns DefaultValidationResult.Empty

            // Act
            viewModel.onPasswordFocusChange(false)

            // Assert
            verify { validateEmptiness(uiState.password) }
            Assertions.assertEquals(
                R.string.error_message_field_blank,
                uiState.passwordError?.resId
            )
            Assertions.assertEquals(
                R.string.feature_login_password,
                (uiState.passwordError?.args?.first() as? StringResource)?.resId
            )
        }
    }

    @Nested
    @DisplayName("GIVEN fields are valid and button is enabled")
    inner class ButtonClick {

        @Test
        @DisplayName("WHEN button is clicked and an conflict error occurs THEN should show invalid credentials error")
        fun testConflictError() = testScope.runTest {
            // Arrange
            coEvery { signIn(any()) } returns Result.failure(AppError.Api.Conflict)

            // Act
            viewModel.onButtonClick()

            // Arrange
            coVerify {
                signIn(
                    SignInUseCase.Params(
                        email = uiState.email,
                        password = uiState.password
                    )
                )
            }
            Assertions.assertFalse(uiState.isButtonLoading)
            Assertions.assertEquals(
                R.string.error_message_invalid_username_or_password,
                uiState.errorMessage?.resId
            )
        }

        @Test
        @DisplayName("WHEN button is clicked and an error occurs THEN should show generic error")
        fun testGeneralError() = testScope.runTest {
            // Arrange
            coEvery { signIn(any()) } returns Result.failure(AppError.Common.Unknown(null))

            // Act
            viewModel.onButtonClick()

            // Arrange
            coVerify {
                signIn(
                    SignInUseCase.Params(
                        email = uiState.email,
                        password = uiState.password
                    )
                )
            }
            Assertions.assertFalse(uiState.isButtonLoading)
            Assertions.assertEquals(
                R.string.common_generic_error_message,
                uiState.errorMessage?.resId
            )
        }
    }

    @Test
    @DisplayName("GIVEN register text is clicked WHEN on link click is called THEN should send signup action")
    fun onLinkClick() = testScope.runTest {
        // Act
        viewModel.onLinkClick()

        // Arrange
        Assertions.assertEquals(SignInNavAction.SignUp, viewModel.action.first())
    }

    @Test
    @DisplayName("GIVEN error dialog is dismissed WHEN clear error is called THEN should update error message to null")
    fun clearError() = testScope.runTest {
        // Act
        viewModel.clearError()

        // Arrange
        Assertions.assertNull(uiState.errorMessage)
    }
}