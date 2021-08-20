package com.keelim.orange.data.response.season


import com.google.gson.annotations.SerializedName

data class CommentResponse(
  @SerializedName("article")
  var article: Int,
  @SerializedName("commentContent")
  var commentContent: String,
  @SerializedName("commentId")
  var commentId: Int,
  @SerializedName("commentWritetime")
  var commentWritetime: String,
  @SerializedName("user")
  var user: Int
)