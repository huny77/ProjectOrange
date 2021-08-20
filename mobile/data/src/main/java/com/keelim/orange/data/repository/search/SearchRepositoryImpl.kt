package com.keelim.orange.data.repository.search

import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.model.Search2
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SearchRepositoryImpl(
  private val dispatcher: CoroutineDispatcher,
  private val apiRequestFactory: ApiRequestFactory,
) : SearchRepository {
  override suspend fun search(query: String): List<Search2> = withContext(dispatcher) {
    val response = apiRequestFactory.retrofit.search(query)
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
}
