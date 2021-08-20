package com.keelim.orange.data.call

import com.google.gson.annotations.SerializedName

data class WriteComment(
  @SerializedName("article")
  val article: Int,
  @SerializedName("commentContent")
  val commentContent: String,
  @SerializedName("user")
  val user: Int,
)
