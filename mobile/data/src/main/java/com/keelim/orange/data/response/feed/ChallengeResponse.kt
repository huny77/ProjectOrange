package com.keelim.orange.data.response.feed


import com.google.gson.annotations.SerializedName

data class ChallengeResponse(
  @SerializedName("categoryId")
  var categoryId: Int,
  @SerializedName("challengeDescribe")
  var challengeDescribe: String,
  @SerializedName("challengeId")
  var challengeId: Int,
  @SerializedName("challengeTitle")
  var challengeTitle: String,
  @SerializedName("currentMembers")
  var currentMembers: Int,
  @SerializedName("endDate")
  var endDate: String,
  @SerializedName("imagePath")
  var imagePath: String,
  @SerializedName("managerId")
  var managerId: Int,
  @SerializedName("maxMembers")
  var maxMembers: Int,
  @SerializedName("minMembers")
  var minMembers: Int,
  @SerializedName("periodId")
  var periodId: Int,
  @SerializedName("startDate")
  var startDate: String,
  @SerializedName("totalPoint")
  var totalPoint: Int
)