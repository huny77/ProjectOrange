package com.keelim.orange.data.repository.feed

import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.response.DetailResponse
import com.keelim.orange.data.response.ResultResponse
import com.keelim.orange.data.response.feed.CategoryResponse
import com.keelim.orange.data.response.feed.ChallengeResponse

interface FeedRepository {
  suspend fun getDetail(uid: Int): Search2?
  suspend fun getCategory(): List<CategoryResponse>
  suspend fun challengeList(): List<Search2>
  suspend fun createChallenge(information: Any): ResultResponse
  suspend fun sign(challengeId:Int, userId:Int)
}
