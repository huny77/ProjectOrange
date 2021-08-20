package com.keelim.orange.data.repository

import com.keelim.orange.data.datasource.LoginDataSource
import com.keelim.orange.data.model.LoggedInUser
import com.keelim.orange.data.model.Result

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(
  private val dataSource: LoginDataSource
) {

  // in-memory cache of the loggedInUser object
  var user: LoggedInUser? = null
    private set

  val isLoggedIn: Boolean
    get() = user != null

  init {
    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    user = null
  }

  suspend fun logout() {
    user = null
    dataSource.logout()
  }

  suspend fun login(username: String, password: String): Result<LoggedInUser> {
    val result = dataSource.login(username, password)

    if (result is Result.Success) {
      setLoggedInUser(
        result.data,
      )
    }

    return result
  }

  suspend fun signup(username: String, password: String, nickname: String): Result<LoggedInUser> {
    // handle login
    val result = dataSource.signup(username, password, nickname)

    if (result is Result.Success) {
      setLoggedInUser(result.data)
    }

    return result
  }

  private fun setLoggedInUser(loggedInUser: LoggedInUser) {
    this.user = loggedInUser
    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
  }
}
