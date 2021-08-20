package com.keelim.orange.data.repository.feed

import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.call.ChallengeCall
import com.keelim.orange.data.call.SignCall
import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.response.ResultResponse
import com.keelim.orange.data.response.feed.CategoryResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FeedRepositoryImpl(
  private val apiRequestFactory: ApiRequestFactory,
  private val dispatcher: CoroutineDispatcher,
) : FeedRepository {
  override suspend fun getDetail(uid: Int): Search2? = withContext(dispatcher) {
    val response = apiRequestFactory.retrofit.detail(uid)
    if (response.isSuccessful) {
      return@withContext response.body()?.let {
        Search2(
          it.categoryId,
          it.challengeDescribe,
          it.challengeId,
          it.challengeTitle,
          it.currentMembers,
          it.endDate,
          it.imagePath,
          it.managerId,
          it.maxMembers,
          it.minMembers,
          it.periodId,
          it.startDate,
          it.totalPoint
        )
      }
    } else {
      return@withContext null
    }
  }

  override suspend fun getCategory(): List<CategoryResponse> = withContext(dispatcher) {
    apiRequestFactory.retrofit.category().body() ?: listOf()
  }

  override suspend fun challengeList(): List<Search2> = withContext(dispatcher) {
    val response = apiRequestFactory.retrofit.challengeList()
    if (response.isSuccessful) {
      return@withContext response.body()?.map {
        Search2(
          it.categoryId,
          it.challengeDescribe,
          it.challengeId,
          it.challengeTitle,
          it.currentMembers,
          it.endDate,
          it.imagePath,
          it.managerId,
          it.maxMembers,
          it.minMembers,
          it.periodId,
          it.startDate,
          it.totalPoint
        )
      } ?: emptyList()
    } else {
      return@withContext emptyList()
    }
  }

  override suspend fun createChallenge(information: Any): ResultResponse = withContext(dispatcher) {
    return@withContext apiRequestFactory.retrofit.createChallengeList(ChallengeCall(
      "",
      "",
      "",
      "",
    )).body()!!
  }

  override suspend fun sign(challengeId: Int, userId: Int): Unit = withContext(dispatcher) {
    apiRequestFactory.retrofit.sign(
      SignCall(
        challengeId,
        userId
      )
    )
  }
}
