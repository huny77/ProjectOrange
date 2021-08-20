package com.keelim.orange.ui.feed

import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.response.feed.CategoryResponse
import com.keelim.orange.data.response.feed.ChallengeResponse

sealed class FeedState {
  object UnInitialized : FeedState()
  object Loading : FeedState()
  data class Success(
    val data1: List<CategoryResponse>,
    val data2: List<Search2>
  ) : FeedState()
  object Error : FeedState()
}
