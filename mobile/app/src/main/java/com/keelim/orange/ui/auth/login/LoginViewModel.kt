package com.keelim.orange.ui.auth.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.keelim.orange.R
import com.keelim.orange.data.model.Result
import com.keelim.orange.domain.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val authUseCase: AuthUseCase,
) : ViewModel() {

  private val _loginForm = MutableLiveData<LoginFormState>()
  val loginFormState: LiveData<LoginFormState> = _loginForm

  private val _loginResult = MutableLiveData<LoginResult>()
  val loginResult: LiveData<LoginResult> = _loginResult

  fun login(username: String, password: String) = viewModelScope.launch {
    // can be launched in a separate asynchronous job
    when (val result = authUseCase.login(username, password)) {
      is Result.Success -> {
        setLoginResult(
          LoginResult(
            success = LoggedInUserView(displayName = result.data.displayName),
            token = result.data.token,
            uid = result.data.userId.toInt()
          )
        )
      }
      is Result.Error -> {
        setLoginResult(
          LoginResult(error = R.string.login_failed)
        )
      }
    }
  }

  fun loginDataChanged(username: String, password: String) = viewModelScope.launch {
    when {
      !isUserNameValid(username) -> setLoginForm(LoginFormState(usernameError = R.string.invalid_username))
      !isPasswordValid(password) -> setLoginForm(LoginFormState(passwordError = R.string.invalid_password))
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

  // A placeholder username validation check
  private fun isUserNameValid(username: String): Boolean {
    return if (username.contains("@")) {
      Patterns.EMAIL_ADDRESS.matcher(username).matches()
    } else {
      username.isNotBlank()
    }
  }

  // A placeholder password validation check
  private fun isPasswordValid(password: String): Boolean {
    return password.length > 5
  }
}
