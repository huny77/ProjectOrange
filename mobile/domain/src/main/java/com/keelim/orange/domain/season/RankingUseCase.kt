package com.keelim.orange.domain.season

import com.keelim.orange.data.model.ranking.Ranking
import com.keelim.orange.data.repository.season.ranking.RankingRepository

class RankingUseCase(
  private val rankingRepository: RankingRepository
) {
  suspend fun point(): List<Ranking> {
    return rankingRepository.point()
  }

  suspend fun start(): List<Ranking> {
    return rankingRepository.start()
  }
}
