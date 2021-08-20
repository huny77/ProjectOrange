package com.keelim.orange.ui.auth.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.keelim.orange.R
import com.keelim.orange.data.model.Result
import com.keelim.orange.domain.auth.AuthUseCase
import com.keelim.orange.ui.auth.login.LoggedInUserView
import com.keelim.orange.ui.auth.login.LoginFormState
import com.keelim.orange.ui.auth.login.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel @Inject constructor(
  private val authUseCase: AuthUseCase,
) : ViewModel() {

  private val _loginForm = MutableLiveData<LoginFormState>()
  val loginFormState: LiveData<LoginFormState> = _loginForm

  private val _loginResult = MutableLiveData<LoginResult>()
  val loginResult: LiveData<LoginResult> = _loginResult

  fun signup(username: String, password: String, nickname:String) = viewModelScope.launch {
    when (val result = authUseCase.signup(username, password, nickname)) {
      is Result.Success -> {
        setLoginResult(
          LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        )
      }
      is Result.Error -> {
        setLoginResult(
          LoginResult(error = R.string.login_failed)
        )
      }
    }
  }

  fun loginDataChanged(username: String, password: String, passwordConfirmation: String) =
    viewModelScope.launch {
      when {
        !isUserNameValid(username) -> setLoginForm(LoginFormState(usernameError = R.string.invalid_username))
//        !isPasswordValid(password) -> setLoginForm(LoginFormState(passwordError = R.string.invalid_password))
//        !isPasswordConfirmation(password, passwordConfirmation) -> setLoginForm(LoginFormState(passwordError = R.string.invalid_passwordConfirmation))
        else -> setLoginForm(LoginFormState(isDataValid = true))
      }
    }

  fun sendTokenToServer(token: OAuthToken) = viewModelScope.launch {
  }

  private fun setLoginResult(value: LoginResult) {
    _loginResult.value = value
  }

  private fun setLoginForm(value: LoginFormState) {
    _loginForm.value = value
  }

  private fun isUserNameValid(username: String): Boolean {
    return if (username.contains("@")) {
      Patterns.EMAIL_ADDRESS.matcher(username).matches()
    } else {
      username.isNotBlank()
    }
  }

  // A placeholder password validation check
  private fun isPasswordValid(password: String): Boolean {
    return password.length <= 5
  }

  private fun isPasswordConfirmation(password: String, passwordConfirmation: String): Boolean =
    password == passwordConfirmation
}
