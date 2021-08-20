package com.keelim.orange.data.response.ranking

import com.google.gson.annotations.SerializedName

data class RankingResponse(
  @SerializedName("categoryId")
  val categoryId: Int,
  @SerializedName("challengeDescribe")
  val challengeDescribe: String,
  @SerializedName("challengeId")
  val challengeId: Int,
  @SerializedName("challengeTitle")
  val challengeTitle: String,
  @SerializedName("currentMembers")
  val currentMembers: Int,
  @SerializedName("endDate")
  val endDate: String,
  @SerializedName("imagePath")
  val imagePath: String?,
  @SerializedName("managerId")
  val managerId: Int,
  @SerializedName("maxMembers")
  val maxMembers: Int,
  @SerializedName("minMembers")
  val minMembers: Int,
  @SerializedName("periodId")
  val periodId: Int,
  @SerializedName("startDate")
  val startDate: String,
  @SerializedName("totalPoint")
  val totalPoint: Int,
)
