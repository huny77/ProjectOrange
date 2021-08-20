package com.keelim.orange.data.response.feed

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
  @SerializedName("categoryId")
  val categoryId: Int,
  @SerializedName("categoryName")
  val categoryName: String
)
