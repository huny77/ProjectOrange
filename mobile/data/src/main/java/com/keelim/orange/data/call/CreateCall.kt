package com.keelim.orange.data.call


import com.google.gson.annotations.SerializedName

data class CreateCall(
  @SerializedName("articleContent")
  var articleContent: String,
  @SerializedName("challenge")
  var challenge: Int,
  @SerializedName("title")
  var title: String,
  @SerializedName("user")
  var user: Int,
  @SerializedName("verified")
  var verified: Boolean
)