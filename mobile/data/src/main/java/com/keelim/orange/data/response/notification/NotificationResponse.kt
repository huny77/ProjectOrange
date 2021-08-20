package com.keelim.orange.data.response.notification

import com.google.gson.annotations.SerializedName

data class NotificationResponse(
  @SerializedName("notificationContent")
  val notificationContent: String,
  @SerializedName("notificationId")
  val notificationId: Int,
  @SerializedName("notificationTitle")
  val notificationTitle: String,
  @SerializedName("user")
  val user: Any,
)
