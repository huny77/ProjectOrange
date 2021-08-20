package com.keelim.orange.ui.auth.login

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null,
    val token: String? = null,
    val uid: Int? = null,
)
