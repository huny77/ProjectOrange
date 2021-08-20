package com.keelim.orange.data.call

import com.google.gson.annotations.SerializedName

data class LoginCall(
  @SerializedName("email")
  val email: String,
  @SerializedName("password")
  val password: String,
)
