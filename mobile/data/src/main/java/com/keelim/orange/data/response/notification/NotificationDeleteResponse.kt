package com.keelim.orange.data.response.notification

import com.google.gson.annotations.SerializedName

data class NotificationDeleteResponse(
  @SerializedName("result")
  val result: String
)
