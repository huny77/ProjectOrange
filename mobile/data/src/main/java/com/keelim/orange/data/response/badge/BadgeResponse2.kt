package com.keelim.orange.data.response.badge


import com.google.gson.annotations.SerializedName

data class BadgeResponse2(
  @SerializedName("badge")
  var badge: Int,
  @SerializedName("badgeCount")
  var badgeCount: Int,
  @SerializedName("id")
  var id: Int,
  @SerializedName("representBadge")
  var representBadge: Boolean,
  @SerializedName("user")
  var user: Int
)