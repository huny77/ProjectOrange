package com.keelim.orange.data.datasource

import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.call.LoginCall
import com.keelim.orange.data.call.SignUpCall
import com.keelim.orange.data.model.LoggedInUser
import com.keelim.orange.data.model.Result
import com.keelim.orange.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(
  @IoDispatcher private val dispatcher: CoroutineDispatcher,
  private val apiRequestFactory: ApiRequestFactory,
) {

  suspend fun login(username: String, password: String): Result<LoggedInUser> = withContext(dispatcher) {
    val response = apiRequestFactory.retrofit.login(
      LoginCall(
        username,
        password
      )
    )
    if (response.isSuccessful && response.body()!!.response == "success") {
      val token = response.body()!!.data1
      return@withContext Result.Success(LoggedInUser(response.body()!!.data2.toString(), username, token))
    } else {
      return@withContext Result.Error(IOException("Error logging in"))
    }
  }

  suspend fun logout() {
    // TODO: revoke authentication
  }

  suspend fun signup(username: String, password: String, nickname: String): Result<LoggedInUser> = withContext(dispatcher) {
    val response = apiRequestFactory.retrofit.signup(
      SignUpCall(
        username,
        nickname,
        password,
        username
      )
    )
    if (response.isSuccessful && response.body()!!.response == "success") {
      return@withContext Result.Success(LoggedInUser(username, username, null))
    } else {
      return@withContext Result.Error(IOException("Error logging in"))
    }
  }
}
