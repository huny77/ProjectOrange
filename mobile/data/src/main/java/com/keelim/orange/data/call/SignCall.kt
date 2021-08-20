package com.keelim.orange.data.call

import com.google.gson.annotations.SerializedName

data class SignCall(
  @SerializedName("challengeId")
  val challengeId: Int,
  @SerializedName("userId")
  val userId: Int,
)
