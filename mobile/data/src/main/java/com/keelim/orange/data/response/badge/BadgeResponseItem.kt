package com.keelim.orange.data.response.badge

import com.google.gson.annotations.SerializedName

data class BadgeResponseItem(
  @SerializedName("badgeDescribe")
  val badgeDescribe: String,
  @SerializedName("badgeId")
  val badgeId: Int,
  @SerializedName("badgeImagePath")
  val badgeImagePath: String,
  @SerializedName("badgeTitle")
  val badgeTitle: String,
  @SerializedName("badgeType")
  val badgeType: BadgeType
)
