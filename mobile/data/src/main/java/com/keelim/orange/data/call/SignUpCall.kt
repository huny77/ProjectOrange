package com.keelim.orange.data.call

import com.google.gson.annotations.SerializedName

data class SignUpCall(
  @SerializedName("email")
  val email: String,
  @SerializedName("password")
  val password: String,
  @SerializedName("username")
  val username: String,
  @SerializedName("nickname")
  val nickname: String,
)
