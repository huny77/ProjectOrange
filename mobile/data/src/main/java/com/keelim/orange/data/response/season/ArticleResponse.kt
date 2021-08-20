package com.keelim.orange.data.response.season


import com.google.gson.annotations.SerializedName

data class ArticleResponse(
  @SerializedName("articleContent")
  var articleContent: String,
  @SerializedName("articleId")
  var articleId: Int,
  @SerializedName("articleWritetime")
  var articleWritetime: String,
  @SerializedName("challenge")
  var challenge: Int,
  @SerializedName("title")
  var title: String,
  @SerializedName("user")
  var user: Int,
  @SerializedName("verified")
  var verified: Boolean
)