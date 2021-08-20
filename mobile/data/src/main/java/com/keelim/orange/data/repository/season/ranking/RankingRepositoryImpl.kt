package com.keelim.orange.data.repository.season.ranking

import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.model.ranking.Ranking
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RankingRepositoryImpl(
  private val dispatcher: CoroutineDispatcher,
  private val apiRequestFactory: ApiRequestFactory,
) : RankingRepository {

  override suspend fun point(): List<Ranking> = withContext(dispatcher) {
    val response = apiRequestFactory.retrofit.getPointRanking()
    if (response.isSuccessful) {
      return@withContext response.body()?.mapIndexed { index, rankingResponse ->
        Ranking(
          ranking_title = rankingResponse.challengeTitle,
          ranking_description = rankingResponse.challengeDescribe,
          rank = index+1,
          image_address = rankingResponse.imagePath ?: ""
        )
      } ?: emptyList()
    } else {
      return@withContext emptyList()
    }
  }

  override suspend fun start(): List<Ranking> = withContext(dispatcher) {
    val response = apiRequestFactory.retrofit.getStartRanking()
    if (response.isSuccessful) {
      return@withContext response.body()?.mapIndexed { index, rankingResponse ->
        Ranking(
          ranking_title = rankingResponse.challengeTitle,
          ranking_description = rankingResponse.challengeDescribe,
          rank = index+1,
          image_address = rankingResponse.imagePath ?: ""
        )
      } ?: emptyList()
    } else {
      return@withContext emptyList()
    }
  }
}
