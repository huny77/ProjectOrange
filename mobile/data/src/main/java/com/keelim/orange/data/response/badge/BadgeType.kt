package com.keelim.orange.data.response.badge

import com.google.gson.annotations.SerializedName

data class BadgeType(
  @SerializedName("badgeTypeId")
  val badgeTypeId: Int,
  @SerializedName("badgeTypeName")
  val badgeTypeName: String
)
